package com.pl.shrt.url;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ShrtURLApplication.class)
@WebIntegrationTest
public class ShrtUrlApplicationTest {

    private final Logger log = LoggerFactory.getLogger(ShrtUrlApplicationTest.class);
    
    private RestTemplate template = new TestRestTemplate();
    
    private String generatedId = null;
    
    @Test
    public void addShortURL() {
        
        String url = "http://localhost/alive";

        try {
            // we now need to add a user for security reasons first.

            String userId = template.postForObject("http://localhost:8080/sec/add?username=createurl&password=password&company=mine", null, String.class);

            String encodedURL = URLEncoder.encode(url, "UTF-8");
            String getForEntity = "http://localhost:8080/admin/c/"+userId+"?url=" + encodedURL;
            
            log.info("calling : {} ", getForEntity);
            ResponseEntity<String> urlId = template.getForEntity(getForEntity, String.class);
            
            log.info("Response urlId : {} ", urlId.getBody());
            
            assertNotNull(urlId.getBody());
            
            generatedId = urlId.getBody();
            
            ResponseEntity<String> foundURL = template.getForEntity("http://localhost:8080/admin/v/" + generatedId, String.class);

            log.debug("What we got back {} ", foundURL);
            
            assertEquals(encodedURL, foundURL.getBody());
            
        } catch (UnsupportedEncodingException e) {
            log.error("failed to encode : {} ", e.getMessage() );
        }
    }
}