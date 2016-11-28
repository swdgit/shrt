/**
 * 
 */
package com.pl.shrt.url.control;

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

import com.pl.shrt.url.model.Request;
import com.pl.shrt.url.model.ShrtURL;
import com.pl.shrt.url.repos.ShrtURLRepository;
import com.pl.shrt.url.service.RequestCapture;

/**
 * @author stacydecker
 *
 */
@RestController
public class RedirectController {

    private final Logger log = LoggerFactory.getLogger(RedirectController.class);

    /** connection to the db for short URLs  */
    @Autowired
    private ShrtURLRepository shrtURLRepo;

    /** async link to store off request objects. */
    @Autowired
    private RequestCapture requestCapture;
    
    /** the Response object that we can redirect with...  */
    @Autowired
    private HttpServletResponse response;
    
    /** the requesting service whom we can track */
    @Autowired 
    private HttpServletRequest request;
    
    /**
     * do the actual re-direct here.
     * @param urlId unique id provided that will be used to lookup the redirect.
     */
    @RequestMapping(value = "/{urlId}", method = RequestMethod.GET) 
    public void redirectToURL(@PathVariable String urlId) {
    
        int status = HttpServletResponse.SC_OK;
        
        log.debug("Looking for : {} ", urlId);

        try {
        
            ShrtURL shrtURL = shrtURLRepo.findByShortId(urlId);
            
            if (shrtURL != null) {
                log.debug("Sending to : {} ", URLDecoder.decode(shrtURL.getURL(), "UTF-8"));

                // async call that will not slow us down in the re-direct.
                Request r = new Request(request, shrtURL);
                requestCapture.saveRequestAttributes(r);
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
    
}
