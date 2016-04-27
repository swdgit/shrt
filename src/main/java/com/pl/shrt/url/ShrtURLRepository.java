package com.pl.shrt.url;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShrtURLRepository extends MongoRepository<ShrtURL, String> {

    public List<ShrtURL> findByURL(String encodedURL);
    
    public String findById(String id);

}
