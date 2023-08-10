package com.neueda.muskaan1.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Customer  extends MongoRepository < Customer, String> {
}