package com.example.lab1_service.Services;

import com.example.lab1_service.Entity.DeputyEntity;
import com.example.lab1_service.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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


    public Boolean addNewDeputy(
            String name,
             int age,
             String party,
             int numOfVotes,String user){

        return voteRepository.addNew(name, age, party, numOfVotes,user);
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
             int numOfVotes,String user){

        return voteRepository.changeDeputy(oldName, name, age, party, numOfVotes,user);
    }
}
