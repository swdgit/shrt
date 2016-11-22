package com.pl.shrt.url.repos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pl.shrt.url.model.ShrtURL;

public interface ShrtURLRepository extends MongoRepository<ShrtURL, String> {

    public List<ShrtURL> findByURL(String encodedURL);
    
    public ShrtURL findById(String id);
    
    public ShrtURL findByShortId(String id);

}
