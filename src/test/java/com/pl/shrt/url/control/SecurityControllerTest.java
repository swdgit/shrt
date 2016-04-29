package com.pl.shrt.url.control;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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

        Map<String, String> map = new HashMap<  >();
        map.put("username", "swdtemp");
        map.put("password", "password");
        map.put("company",  "mine");

        String userId = template.postForObject("http://localhost:8080/sec/add?username=swdtemp&password=password&company=mine", map, String.class);

        log.debug("new userId : {} ", userId);
        assertNotNull(userId);

    }

    @Test
    public void testSetUserStatus() {
//        fail("Not yet implemented");
    }

    @Test
    public void testSetNewPassword() {
//        fail("Not yet implemented");
    }

    @Test
    public void testSetCompanyName() {
//        fail("Not yet implemented");
    }

}
