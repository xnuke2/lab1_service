package com.example.lab1_service;



import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class VoteRepository {

    @Autowired
    private DeputyRepository deputyRepository;

    public List<DeputyEntity>  getAll(){
        return deputyRepository.findAll();
    }

    public DeputyEntity getById(String name){
        return deputyRepository.findById(name).get();
    }
    public boolean addNew( String name, int age, String party, int numberOfVotes){
        for (DeputyEntity deputy: deputyRepository.findAll()) {
            if(deputy.name.equals(name)){
                return false;
            }
        }
        if(age<20)return false;
        if(party ==null || party =="")return false;
        if (numberOfVotes<0)return false;
        deputyRepository.save(new DeputyEntity(name, age, party, numberOfVotes));
        return true;

    }
    public boolean deleteByName(String name){
        DeputyEntity deputy = deputyRepository.findById(name).get();
        if(deputy.equals(null))return false;
        else{
            deputyRepository.delete(deputy);
            return true;
        }

    }
    public boolean changeDeputy(String oldName,String name, int age, String party, int numberOfVotes){
        DeputyEntity olddeputy = deputyRepository.findById(oldName).get();
        DeputyEntity deputy = deputyRepository.findById(name).get();

        if((olddeputy.equals(null))||(!(deputy.equals(null))&&(!oldName.equals(name))))
            return false;

        if(age<20)return false;
        if(party ==null || party =="")return false;
        if (numberOfVotes<0)return false;
        deleteByName(oldName);
        deputyRepository.save(new DeputyEntity(name, age, party, numberOfVotes));
        return true;
    }
}
