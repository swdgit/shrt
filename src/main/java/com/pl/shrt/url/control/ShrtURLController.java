package com.pl.shrt.url.control;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pl.shrt.url.model.ShrtURL;
import com.pl.shrt.url.repos.SecurityRepository;
import com.pl.shrt.url.repos.ShrtURLRepository;
import com.pl.shrt.url.service.RequestCapture;

@RestController
public class ShrtURLController {

    private final Logger log = LoggerFactory.getLogger(ShrtURLController.class);
    
    @Autowired
    private ShrtURLRepository shrtURLRepo;
    
    @Autowired
    private SecurityRepository securityRepo;
    
    @Autowired
    private HttpServletResponse response;
    
    @Autowired 
    private HttpServletRequest request;
    
    @Autowired
    private RequestCapture requestCapture;
    
    /**
     * do the actual re-direct here.
     * @param urlId unique id provided that will be used to lookup the redirect.
     */
    @RequestMapping(value = "/{urlId}", method = RequestMethod.GET) 
    public void redirectToURL(@PathVariable String urlId) {
    
        // async call that will not slow us down in the re-direct.
        requestCapture.saveRequestAttributes(request);
        
        int status = HttpServletResponse.SC_OK;
        log.debug("Looking for : {} ", urlId);
        try {
        
            // TODO think about the URL and when/where should it be validated.
            if (shrtURLRepo.exists(urlId)) {
                ShrtURL shrtURL = shrtURLRepo.findById(urlId);
                log.debug("Sending to : {} ", URLDecoder.decode(shrtURL.getURL(), "UTF-8"));
                
                response.sendRedirect(URLDecoder.decode(shrtURL.getURL(), "UTF-8"));
            } else {
                status = HttpServletResponse.SC_NOT_FOUND;
            }
        } catch (IOException e) {
            status = HttpServletResponse.SC_NOT_FOUND;
            log.error("was not able to redirect : {}", e.getMessage());
        }
        
        response.setStatus(status);
    }
    
    /**
     * create or return the shrt code
     * @param uId security user id
     * @param encodedURL the url that is to be re-directed to
     * @return a unique short code 
     */
    @RequestMapping(value = "/c/{uId}", method = RequestMethod.GET)
    public String createShrtURL(@PathVariable String uId, @RequestParam("url") String encodedURL) {

        String newURLId = "Invalid Request"; 
        if (securityRepo.exists(uId)) {
        
            ShrtURL shrtURL = new ShrtURL();
    
            shrtURL.setURL(encodedURL);
            shrtURL.setActive(true);
            shrtURL.setCreated(new Date(System.currentTimeMillis()));
            // TODO Defensive code a check for the existing URL existing (Do we enforce unique URLs in the datastore?)
            // TODO Shorten the Id value... MongoDB ID generator maybe... 
            
            shrtURLRepo.save(shrtURL);
            
            log.debug("created : {} ", shrtURL);
            
            newURLId = shrtURL.getUrlId();
        }
        return newURLId;
    }
    
    /**
        // TODO need to hide this behind some security

     * If need be update the given shrt code with the new URL.
     * @param uId security user id
     * @param urlId unique short code
     * @param encodedURL new redirect url for this urlId
     */
    @RequestMapping(value= "/u/{uId}/{urlId}", method = RequestMethod.POST) 
    public void updateURL(@PathVariable String uId, @PathVariable String urlId, @RequestParam("url") String encodedURL) {
        
        if (shrtURLRepo.exists(urlId)) {
            ShrtURL shrtURL = new ShrtURL(urlId, encodedURL);
            shrtURL.setUpdated(new Date(System.currentTimeMillis()));
            
            shrtURLRepo.save(shrtURL);
        }
    }
    
    //TODO add the url activate/deactivate  method.
    
    /**
     * get the URL from the id. can be used to validate your submission.
     * @param urlId unique short code
     * @return 'not found' or the url to be re-directed to for this urlId
     */
    @RequestMapping(value = "/v/{urlId}", method = RequestMethod.GET) 
    public String getURL(@PathVariable String urlId) {

        String url = "not found";

        if (shrtURLRepo.exists(urlId)) {
            ShrtURL shrtURL = shrtURLRepo.findById(urlId);
            url = shrtURL.getURL();
        }

        return url;
    }
    
}
