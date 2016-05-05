/**
 * 
 */
package com.pl.shrt.url.model;

import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author stacydecker
 *
 */
public class Request {

    private Cookie[] cookies;
    
    private Enumeration<String> headerNames;
    
    private String localAddr;
    
    private Enumeration<Locale> locales;
    
    private String requestURI;
    
    private String remoteUser;
    
    private String remoteHost;
    
    private String remoteAddr;
    
    private String shrtURLId;
    
    public Request(HttpServletRequest request, ShrtURL shrtURL) {
        
        this.cookies     = request.getCookies();
        this.headerNames = request.getHeaderNames();
        this.localAddr   = request.getLocalAddr();
        this.locales     = request.getLocales();
        this.requestURI  = request.getRequestURI();
        this.remoteUser  = request.getRemoteUser();
        this.remoteHost  = request.getRemoteHost();
        this.remoteAddr  = request.getRemoteAddr();
        this.shrtURLId   = shrtURL.getUrlId();
    }

    /**
     * @return the cookies
     */
    public Cookie[] getCookies() {
        return cookies;
    }

    /**
     * @param cookies the cookies to set
     */
    public void setCookies(Cookie[] cookies) {
        this.cookies = cookies;
    }

    /**
     * @return the headerNames
     */
    public Enumeration<String> getHeaderNames() {
        return headerNames;
    }

    /**
     * @param headerNames the headerNames to set
     */
    public void setHeaderNames(Enumeration<String> headerNames) {
        this.headerNames = headerNames;
    }

    /**
     * @return the localAddr
     */
    public String getLocalAddr() {
        return localAddr;
    }

    /**
     * @param localAddr the localAddr to set
     */
    public void setLocalAddr(String localAddr) {
        this.localAddr = localAddr;
    }

    /**
     * @return the locales
     */
    public Enumeration<Locale> getLocales() {
        return locales;
    }

    /**
     * @param locales the locales to set
     */
    public void setLocales(Enumeration<Locale> locales) {
        this.locales = locales;
    }

    /**
     * @return the requestURI
     */
    public String getRequestURI() {
        return requestURI;
    }

    /**
     * @param requestURI the requestURI to set
     */
    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    /**
     * @return the remoteUser
     */
    public String getRemoteUser() {
        return remoteUser;
    }

    /**
     * @param remoteUser the remoteUser to set
     */
    public void setRemoteUser(String remoteUser) {
        this.remoteUser = remoteUser;
    }

    /**
     * @return the remoteHost
     */
    public String getRemoteHost() {
        return remoteHost;
    }

    /**
     * @param remoteHost the remoteHost to set
     */
    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    /**
     * @return the remoteAddr
     */
    public String getRemoteAddr() {
        return remoteAddr;
    }

    /**
     * @param remoteAddr the remoteAddr to set
     */
    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    /**
     * @return the shrtURLId
     */
    public String getShrtURLId() {
        return shrtURLId;
    }

    /**
     * @param shrtURLId the shrtURLId to set
     */
    public void setShrtURLId(String shrtURLId) {
        this.shrtURLId = shrtURLId;
    }
}