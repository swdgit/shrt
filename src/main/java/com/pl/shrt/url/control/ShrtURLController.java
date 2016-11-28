package com.pl.shrt.url.control;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pl.shrt.url.model.ShrtURL;
import com.pl.shrt.url.repos.SecurityRepository;
import com.pl.shrt.url.repos.ShrtURLRepository;

@RestController
@RequestMapping("admin")
public class ShrtURLController {

    private final Logger log = LoggerFactory.getLogger(ShrtURLController.class);
    
    /** connection to the db for short URLs  */
    @Autowired
    private ShrtURLRepository shrtURLRepo;
    
    /** connection to the db for security checks */
    @Autowired
    private SecurityRepository securityRepo;
    
    @Value("${shrt.url.default.length}")
    private int defaultShrtURLLength;
    
    /**
     * create or return the shrt code
     * @param uId security user id
     * @param encodedURL the url that is to be re-directed to
     * @return a unique short code 
     */
    @RequestMapping(value = {"/c/{uId}","/c/{uId}/{urlLength}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String createShrtURL(@PathVariable String uId, 
                                @PathVariable Optional<Integer> urlLength,
                                @RequestParam("url") String encodedURL) {

        String newURLId = "Invalid Request"; 
        
        if (securityRepo.exists(uId)) {
            
            int shrtLength = defaultShrtURLLength;

            if (urlLength.isPresent()) {
               shrtLength = urlLength.get();
            }

            ShrtURL shrtURL = new ShrtURL();
            
            shrtURL.setShortId(getNewShrtURLId(shrtLength));
            shrtURL.setURL(encodedURL);
            shrtURL.setSecurityId(uId);
            shrtURL.setActive(true);
            shrtURL.setCreated(new Date(System.currentTimeMillis()));

            shrtURLRepo.save(shrtURL);
            
            log.debug("created : {} ", shrtURL);
            
            newURLId = shrtURL.getShortId();
        }
        return newURLId;
    }
    
    /**
     * If need be update the given shrt code with the new URL.
     * @param uId security user id
     * @param urlId unique short code
     * @param encodedURL new redirect url for this urlId
     */
    @RequestMapping(value= "/u/{uId}/{urlId}", method = RequestMethod.POST) 
    public void updateURL(@PathVariable String uId, @PathVariable String urlId, @RequestParam("url") String encodedURL) {

        ShrtURL shrtURL = shrtURLRepo.findByShortId(urlId);
        
        if (shrtURL != null) {

            shrtURL = new ShrtURL(urlId, encodedURL);
            shrtURL.setURL(encodedURL);
            shrtURL.setUpdated(new Date(System.currentTimeMillis()));
            
            shrtURLRepo.save(shrtURL);
        }
    }
    
    @RequestMapping(value = "/e/{uId}/{urlId}", method = {RequestMethod.GET, RequestMethod.POST})
    public String setURLStatus(@PathVariable String uId, @PathVariable String urlId, @PathVariable Boolean enable) {
        
        return null;
    }
    /**
     * get the URL from the id. can be used to validate your submission.
     * @param urlId unique short code
     * @return 'not found' or the url to be re-directed to for this urlId
     */
    @RequestMapping(value = "/v/{urlId}", method = RequestMethod.GET) 
    public String getURL(@PathVariable String urlId) {

        ShrtURL shrtURL = shrtURLRepo.findByShortId(urlId);

        String url = "not found";

        if (shrtURL != null) {
            url = shrtURL.getURL();
        }

        return url;
    }    

    /**
     * @param shrtURLLength
     * @return a unique short url id
     */
    private String getNewShrtURLId(int shrtURLLength) {
        
        String shrtURLId = null;
        
        String temp = null;
        
        while (shrtURLId == null) {
            temp = RandomStringUtils.randomAlphanumeric(shrtURLLength); 
            if (shrtURLRepo.findByShortId(shrtURLId) == null) {
                shrtURLId = temp;
            }
        }
        
        return shrtURLId;
    }
}