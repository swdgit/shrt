/**
 * 
 */
package com.pl.shrt.url;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author stacydecker
 *
 */
@SpringBootApplication
@EnableAsync
@EnableAutoConfiguration
public class ShrtURLApplication implements AsyncConfigurer {

    private static Logger log = LoggerFactory.getLogger(ShrtURLApplication.class);

    /**
     * This provides a background task process for the deletion of temp files created in the process.
     */
    @Override
    @Bean(name = "reqCaptureExecutor")
    public Executor getAsyncExecutor() {
        log.debug("Starting up the Async request data capture threadpool");
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setThreadNamePrefix("RequestCapture");
        taskExecutor.initialize();
        
        return taskExecutor;
    }
    
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    /**
     * Just what starts everything for Spring Boot
     * @param args passed in arguments.
     */
    public static void main(String[] args) {
        
        log.info("Starting up the App.");
        
        SpringApplication.run(ShrtURLApplication.class, args);
    }
}