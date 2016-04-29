package com.pl.shrt.url.control;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pl.shrt.url.model.Security;
import com.pl.shrt.url.repos.SecurityRepository;

@Controller
@RequestMapping("/sec/*")
public class SecurityController {
    
    @Autowired
    SecurityRepository securityRepo;

    /**
     * if the username exist you will not be able to add a new one. 
     * 
     * @param username
     * @param password
     * @param company
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("company") String company) {
        
        Security existing = securityRepo.findByUsername(username);
        
        String userId = "user " + username + " already exists";
        
        if (existing != null) {
            Security newUser = new Security(username, password, company);
            securityRepo.save(newUser);

            userId = newUser.getId();
        } 
        
        return userId;
        
    }
    
    /** 
     * Alter the users status based on the provided.
     * @param userId
     * @param status
     * @return
     */
    @RequestMapping(value = "setStatus", method = RequestMethod.POST)
    public String setUserStatus(@PathVariable String userId, @PathVariable boolean status) {

        String response = "User not found";
        
        if (securityRepo.exists(userId)) {
            Security security = securityRepo.findById(userId);
            security.setActive(!security.isActive());
            securityRepo.save(security);
            response += security.isActive();
        }
        
        return response;
    }
    
    /**
     * change an existing users password.
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public String setNewPassword(@PathVariable String userId,
                          @RequestParam("password") String password,
                          @RequestParam("newPassword") String newPassword) {
        String status = "User not found";
        
        if (securityRepo.exists(userId)) {
            Security security = securityRepo.findById(userId);
            if (StringUtils.equals(password, security.getPassword())) {
                security.setPassword(newPassword);
                security.setUpdated(new Timestamp(System.currentTimeMillis()));
                securityRepo.save(security);
                
                status = "Password updated for ";
            } else {
                status = "Current Password does not match";
            }
        }
        
        return status;
    }
    
    /**
     * update a users company name.
     * @param userId
     * @param company
     * @return
     */
    @RequestMapping(value = "setCompany", method = RequestMethod.POST)
    public String setCompanyName(@PathVariable String userId, @RequestParam("company") String company) {

        String status = "User not found";
        if (securityRepo.exists(userId)) {
            Security security = securityRepo.findById(userId);
            security.setCompany(company);
            security.setUpdated(new Timestamp(System.currentTimeMillis()));
            status = "Company name updated";
        }
        return status;
    }
}
