package com.example.lab1_service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
public class VoteService {
    @Autowired
    VoteRepository voteRepository;

    public List<DeputyEntity> getListDeputes(){

        List<DeputyEntity> deputy = voteRepository.getAll();
        return deputy;
    }

    @Cacheable(value = "deputs", key = "#name")
    public DeputyEntity getDeputeById( String name){

        DeputyEntity deputy = voteRepository.getById(name);
        return deputy;
    }

    @CachePut(value = "deputs", key = "#name")
    public Boolean addNewDeputy(
            String name,
             int age,
             String party,
             int numOfVotes){
        return voteRepository.addNew(name, age, party, numOfVotes);
    }
    @CacheEvict(value = "deputs", key = "#name")
    public Boolean deleteByName(
             String name){
        return voteRepository.deleteByName(name);
    }

    @CacheEvict(value = "deputs", key = "#oldname")
    public Boolean putDeputy(
             String oldName,
             String name,
             int age,
             String party,
             int numOfVotes){
        return voteRepository.changeDeputy(oldName, name, age, party, numOfVotes);
    }
}
