package com.pl.shrt.url;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pl.shrt.url.service.RequestCapture;

@RestController
public class ShrtURLController {

    private final Logger log = LoggerFactory.getLogger(ShrtURLController.class);
    
    @Autowired
    private ShrtURLRepository shrtURLRepository;
    
    @Autowired
    private HttpServletResponse response;
    
    @Autowired 
    private HttpServletRequest request;
    
    @Autowired
    private RequestCapture requestCapture;
    
    /**
     * do the actual re-direct here.
     * @param urlId
     */
    @RequestMapping(value = "/{urlId}", method = RequestMethod.GET) 
    public void getURL(@PathVariable String urlId) {
    
        // async call that will not slow us down in the re-direct.
        requestCapture.saveRequestAttributes(request);
        
        int status = HttpServletResponse.SC_OK;
        
        try {
        
            // TODO think about the URL and when/where should it be validated.
            ShrtURL shrtURL = shrtURLRepository.findById(urlId);
            
            log.debug("Sending to : {} ", URLDecoder.decode(shrtURL.getURL(), "UTF-8"));
            response.sendRedirect(URLDecoder.decode(shrtURL.getURL(), "UTF-8"));

        } catch (IOException e) {
            status = HttpServletResponse.SC_NOT_FOUND;
            log.error("was not able to redirect : {}", e.getMessage());
        }
        
        response.setStatus(status);
    }
    
    /**
     * create or return the shrt code
     * @param encodedURL
     * @return shrt form of the given url
     */
    @RequestMapping(value = "/c/{encodedURL}", method = RequestMethod.GET)
    public String createShrtURL(@PathVariable String encodedURL) {
        ShrtURL shrtURL = new ShrtURL();

        shrtURL.setURL(encodedURL);
        
        // TODO Defensive code a check for the existing URL existing (Do we enforce unique URLs in the datastore?)
        // TODO Shorten the Id value... MongoDB ID generator maybe... 
        
        shrtURLRepository.save(shrtURL);
        
        return shrtURL.getUrlId();
    }
    
    /**
        // TODO need to hide this behind some security

     * If need be update the given shrt code with the new URL.
     * @param urlId
     * @param url
     */
    @RequestMapping(value= "/u/{urlId}/{url}", method = RequestMethod.POST) 
    public void updateURL(@PathVariable String urlId, @PathVariable String url) {
        
        
        if (shrtURLRepository.exists(urlId)) {
            ShrtURL shrtURL = new ShrtURL(urlId, url);
            shrtURLRepository.save(shrtURL);
        }
    }
    
}
