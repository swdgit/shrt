/**
 * 
 */
package com.pl.shrt.url.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pl.shrt.url.model.Request;

/**
 * @author stacydecker
 *
 */
public interface RequestRepository extends MongoRepository<Request, String> {
    
}
