/**
 * 
 */
package com.pl.shrt.url.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author stacydecker
 *
 */
@Service
public class RequestCaptureImpl implements RequestCapture {

    /* (non-Javadoc)
     * @see com.pl.shrt.url.service.RequestCapture#saveRequestAttributes(javax.servlet.http.HttpServletRequest)
     */
    @Override
    @Async("reqCaptureExecutor")
    public void saveRequestAttributes(HttpServletRequest request) {
        // stub for now.
    }

}
