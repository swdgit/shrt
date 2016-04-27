package com.pl.shrt.url;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShrtURLController {

    private final Logger log = LoggerFactory.getLogger(ShrtURLController.class);
    
    @Autowired
    private ShrtURLRepository shrtURLRepository;
    
    @Autowired
    private HttpServletResponse response;
    
    @Autowired 
    private HttpServletRequest request;
    
    public ShrtURLController() {
        shrtURLRepository.deleteAll();
    }

    @RequestMapping(value = "/{urlId}", method = RequestMethod.GET) 
    public void getURL(@PathVariable String urlId) {
        
        try {
            response.sendRedirect("");
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    
    @RequestMapping(value = "/c/{encodedURL}", method = RequestMethod.GET)
    public String createShrtURL(@PathVariable String encodedURL) {
        ShrtURL shrtURL = null;

        // in case someone has already given us this URL
        List<ShrtURL> urls = shrtURLRepository.findByURL(encodedURL);
        
        for (ShrtURL url : urls) {
            shrtURL = url;
        }
        
        if (shrtURL == null) {
            // create new url shortented and save it.
        }
        return null;
    }
    
    @RequestMapping(value= "/u/{urlId}/{url}", method = RequestMethod.POST) 
    public void updateURL(@PathVariable String urlId, @PathVariable String url) {
        
        if (shrtURLRepository.exists(urlId)) {
            ShrtURL shrtURL = new ShrtURL(urlId, url);
            shrtURLRepository.save(shrtURL);
        }
    }
    
}
