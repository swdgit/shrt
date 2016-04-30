package com.pl.shrt.url.control;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.pl.shrt.url.ShrtURLApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ShrtURLApplication.class)
@WebIntegrationTest
public class SecurityControllerTest {

    private final Logger log = LoggerFactory.getLogger(SecurityControllerTest.class);
    
    private RestTemplate template = new TestRestTemplate();
    
    @Test
    public void testAddUser() {
        String userId = getUserId("adduser");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();        
        map.add("username", "adduser");
        map.add("password", "password");
        map.add("company",  "mine");

        String response = template.postForObject("http://localhost:8080/sec/add", map, String.class);
        
        log.debug("Add User response ", response);
        
        assertEquals("user adduser already exists", response);
    }

    @Test
    public void testSetUserStatus() {
        String userId = getUserId("setUserStatus");
        String response = template.postForObject("http://localhost:8080/sec/setStatus/" + userId + "/false", null, String.class);
        
        log.debug("Setting UserId: {} Status to false" );
        
        assertEquals("User status updated: false", response);
    }

    @Test
    public void testSetNewPassword() {
        String userId = getUserId("setNewPassword");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();        
        map.add("password",   "password");
        map.add("newPassword","newPassword");

        String response = template.postForObject("http://localhost:8080/sec/changePassword/" + userId, map, String.class);

        log.debug("set new password : {} ", response);

        assertEquals("Password updated for " + userId, response);
    }

    @Test
    public void testSetCompanyName() {
        String userId = getUserId("setCompanyName");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();        
        map.add("company",  "mine");

        String response = template.postForObject("http://localhost:8080/sec/setCompany/" + userId, map, String.class);

        log.debug("company name update : {} ", response);

        assertEquals("Company name updated", response);
    }

    private String getUserId(String username) {
        template.getMessageConverters().add(new FormHttpMessageConverter());
        template.getMessageConverters().add(new StringHttpMessageConverter());

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();        
        map.add("username", username);
        map.add("password", "password");
        map.add("company",  "mine");

        return template.postForObject("http://localhost:8080/sec/add", map, String.class);

    }
}
