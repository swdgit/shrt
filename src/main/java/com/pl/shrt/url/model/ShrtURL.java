/**
 * 
 */
package com.pl.shrt.url.model;

import java.util.Date;

import org.springframework.data.annotation.Id;


/**
 * @author stacydecker
 *
 */
public class ShrtURL {

    @Id
    private String id;

    /** who created this shrt */
    private String securityId;
    
    /** provided encoded URL */
    private String encodedURL;
    
    private boolean active;

    private Date created;
    
    private Date updated;
    
    /**
     * 
     */
    public ShrtURL() {}


    /**
     * @param urlId unique id to lookup
     * @param encodedURL re-direct to url
     */
    public ShrtURL(String urlId, String encodedURL) {
        this.id = urlId;
        this.encodedURL = encodedURL;
    }


    /**
     * @return the urlId
     */
    public String getUrlId() {
        return id;
    }

    /**
     * @param urlId the urlId to set
     */
    public void setUrlId(String urlId) {
        this.id = urlId;
    }

    /**
     * @return the encodedURL
     */
    public String getURL() {
        return encodedURL;
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
     * @param url the encodedURL to set
     */
    public void setURL(String url) {
        this.encodedURL = url;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * @param securityId who created this shrt
     */
    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    /**
     * @return the associated security id
     */
    public String getSecurityId() {
        return this.securityId;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ShrtURL [");
        if (id != null) {
            builder.append("urlId=");
            builder.append(id);
            builder.append(", ");
        }
        if (encodedURL != null) {
            builder.append("encodedURL=");
            builder.append(encodedURL);
        }
        builder.append("]");
        return builder.toString();
    }

    public boolean isValid() {
        boolean valid = false;
        if (encodedURL != null && encodedURL.length() >= 1) {
            valid = true; // maybe.
        }
        return valid;
    }


}
