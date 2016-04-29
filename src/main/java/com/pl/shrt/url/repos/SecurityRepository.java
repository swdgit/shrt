package com.pl.shrt.url.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pl.shrt.url.model.Security;


public interface SecurityRepository extends MongoRepository<Security, String> {
    
    public Security findByUsername(String username);

    public Security findById(String id);

}
