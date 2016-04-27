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
    private String urlId;

    /** provided encoded URL */
    private String url;
    
    /**
     * 
     */
    public ShrtURL() {}


    /**
     * @param urlId
     * @param url
     */
    public ShrtURL(String urlId, String encodedURL) {
        this.urlId = urlId;
        this.url = encodedURL;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ShrtURL [");
        if (urlId != null) {
            builder.append("urlId=");
            builder.append(urlId);
            builder.append(", ");
        }
        if (url != null) {
            builder.append("url=");
            builder.append(url);
        }
        builder.append("]");
        return builder.toString();
    }
}
