package com.pl.shrt.url;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShrtURLRepository extends MongoRepository<ShrtURL, String> {

    public List<ShrtURL> findByURL(String url);
    
    public String findById(String urlId);

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
     */
    @Override
    void delete(ShrtURL url);

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#exists(java.io.Serializable)
     */
    @Override
    boolean exists(String urlId);

    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#findOne(java.io.Serializable)
     */
    @Override
    ShrtURL findOne(String urlId);

    /* (non-Javadoc)
     * @see org.springframework.data.mongodb.repository.MongoRepository#insert(java.lang.Object)
     */
    @Override
    <S extends ShrtURL> S insert(S shrtURL);
}
