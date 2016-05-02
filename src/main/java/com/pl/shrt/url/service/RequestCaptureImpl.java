/**
 * 
 */
package com.pl.shrt.url.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.pl.shrt.url.model.Request;
import com.pl.shrt.url.repos.RequestRepository;

/**
 * @author stacydecker
 *
 */
@Service
public class RequestCaptureImpl implements RequestCapture {

    /** db hook to store off the request object */
    @Autowired
    private RequestRepository requestRepo;
    
    /* (non-Javadoc)
     * @see com.pl.shrt.url.service.RequestCapture#saveRequestAttributes(javax.servlet.http.HttpServletRequest)
     */
    @Override
    @Async("reqCaptureExecutor")
    public void saveRequestAttributes(Request request) {
        
        requestRepo.save(request);
    }

}
