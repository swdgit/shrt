/**
 * 
 */
package com.pl.shrt.url.service;

import org.springframework.scheduling.annotation.Async;

import com.pl.shrt.url.model.Request;

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
    public void saveRequestAttributes(Request request);
}
