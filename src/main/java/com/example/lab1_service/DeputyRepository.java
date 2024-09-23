package com.example.lab1_service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeputyRepository extends MongoRepository <DeputyEntity, String > {

}