package com.pl.shrt.url.control;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pl.shrt.url.model.Security;
import com.pl.shrt.url.repos.SecurityRepository;

@RestController
@RequestMapping("/sec")
public class SecurityController {
    
    private final Logger log = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    SecurityRepository securityRepo;

    /**
     * if the username exist you will not be able to add a new one. 
     * 
     * @param username account access username
     * @param password account access password
     * @param company  account company name
     * @return generated account access user id.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("company")  String company) {
        
        Security existing = securityRepo.findByUsername(username);
        
        String userId = "user " + username + " already exists";
        
        if (existing == null) {
            Security newUser = new Security(username, password, company);
            newUser.setActive(true);
            securityRepo.save(newUser);

            userId = newUser.getId();
        } 
        
        return userId;
        
    }
    
    /** 
     * Alter the users status based on the provided.
     * @param userId account access user id
     * @param status turn on/off access to the 
     * @return text relating to the update of user status. 
     */
    @RequestMapping(value = "/setStatus/{userId}/{status}", method = RequestMethod.POST)
    public String setUserStatus(@PathVariable String userId, @PathVariable boolean status) {

        String response = "User not found";
        
        log.debug("Looking up userId : {} ", userId);
        
        if (securityRepo.exists(userId)) {
            Security security = securityRepo.findById(userId);
            security.setActive(!security.isActive());
            securityRepo.save(security);
            response = "User status updated: " + status;
        }
        
        return response;
    }
    
    /** 
     * change an existing users password.
     * @param userId       account access user id.
     * @param password     existing account access password
     * @param newPassword  new account access password
     * @return text indicating the update status
     */
    @RequestMapping(value = "/changePassword/{userId}", method = RequestMethod.POST)
    public String setNewPassword(@PathVariable String userId,
                                 @RequestParam("password") String password,
                                 @RequestParam("newPassword") String newPassword) {
        String status = "User not found";
        
        if (securityRepo.exists(userId)) {
            Security security = securityRepo.findById(userId);
            if (StringUtils.equals(password, security.getPassword())) {
                security.setPassword(newPassword);
                security.setUpdated(new Date(System.currentTimeMillis()));
                securityRepo.save(security);
                
                status = "Password updated for " + userId;
            } else {
                status = "Current Password does not match";
            }
        }
        
        return status;
    }
    
    /**
     * update a users company name.
     * @param userId  account access user id.
     * @param company updated company name
     * @return text indicating the update status.
     */
    @RequestMapping(value = "/setCompany/{userId}", method = RequestMethod.POST)
    public String setCompanyName(@PathVariable String userId, @RequestParam("company") String company) {

        String status = "User not found";
        if (securityRepo.exists(userId)) {
            Security security = securityRepo.findById(userId);
            security.setCompany(company);
            security.setUpdated(new Date(System.currentTimeMillis()));
            status = "Company name updated";
        }
        return status;
    }
}
