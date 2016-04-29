/**
 * 
 */
package com.pl.shrt.url.model;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;


/**
 * @author stacydecker
 *
 */
public class ShrtURL {

    @Id
    private String id;

    @DBRef
    private Security security;
    
    /** provided encoded URL */
    private String encodedURL;
    
    private boolean active;

    private Timestamp created;
    
    private Timestamp updated;
    
    /**
     * 
     */
    public ShrtURL() {}


    /**
     * @param urlId
     * @param encodedURL
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
     * @param encodedURL the encodedURL to set
     */
    public void setURL(String url) {
        this.encodedURL = url;
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
