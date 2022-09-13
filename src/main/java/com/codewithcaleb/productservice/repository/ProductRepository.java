package com.codewithcaleb.productservice.repository;

import com.codewithcaleb.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductRepository extends MongoRepository<Product,String> {

}
