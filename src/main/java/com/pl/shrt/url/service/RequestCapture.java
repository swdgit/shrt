/**
 * 
 */
package com.pl.shrt.url.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Async;

/**
 * @author stacydecker
 *
 */
public interface RequestCapture {

    /**
     * save off the request attributes that we get for a given redirect.
     * @param request the provided http request object
     */
    @Async
    public void saveRequestAttributes(HttpServletRequest request);
}
