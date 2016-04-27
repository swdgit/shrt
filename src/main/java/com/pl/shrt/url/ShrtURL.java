/**
 * 
 */
package com.pl.shrt.url;

import org.springframework.data.annotation.Id;


/**
 * @author stacydecker
 *
 */
public class ShrtURL {

    @Id
    private String id;

    /** provided encoded URL */
    private String encodedURL;
    
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
     * @param encodedURL the encodedURL to set
     */
    public void setURL(String url) {
        this.encodedURL = url;
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
}
