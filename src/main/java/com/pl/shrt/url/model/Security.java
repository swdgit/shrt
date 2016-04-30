/**
 * 
 */
package com.pl.shrt.url.model;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

/**
 * @author stacydecker
 *
 */
public class Security {
    
    @Id
    private String id;
    
    /** user to associate this security with */
    private String username;
    
    // TODO work out encoding and decoding the password.
    private String password;

    /** who do you work for. */
    private String company;
    
    private boolean active;

    private Timestamp created;
    
    private Timestamp updated;
    
    /**
     * @param username account access username
     * @param password account access password
     * @param company  account company name
     */
    public Security(String username, String password, String company) {
        this.username = username;
        this.password = password;
        this.company = company;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the created
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * @return the updated
     */
    public Timestamp getUpdated() {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

}