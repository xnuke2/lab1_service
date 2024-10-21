package com.example.lab1_service.Repository;



import com.example.lab1_service.Entity.DeputyEntity;
import com.example.lab1_service.Entity.Kafka.ConfirmDeputyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Component
public class VoteRepository {

    @Autowired
    private DeputyRepository deputyRepository;

    @Autowired
    private KafkaTemplate<String, ConfirmDeputyMessage> kafkaSender;

    public List<DeputyEntity>  getAll(){
        return deputyRepository.findAll();
    }

    public DeputyEntity getById(String name){
        return deputyRepository.findById(name).get();
    }
    public boolean addNew( String name, int age, String party, int numberOfVotes,String user){
        for (DeputyEntity deputy: deputyRepository.findAll()) {
            if(deputy.getName().equals(name)){
                return false;
            }
        }
        if(age<20)return false;
        if(party ==null || party =="")return false;
        if (numberOfVotes<0)return false;
        kafkaSender.send("topic-1", new ConfirmDeputyMessage(name,user));
        deputyRepository.save(new DeputyEntity(name, age, party, numberOfVotes, user));
        return true;

    }
    @KafkaListener(topics = "topic-2")
    public void listener(ConfirmDeputyMessage incMessageInput) {
        String deputName = incMessageInput.getValue1();
        DeputyEntity deputy = deputyRepository.findById(deputName).get();
        deputyRepository.deleteById(deputName);
        deputy.setConfirm(incMessageInput.getValue2());
        deputyRepository.save(deputy);
    }
    public boolean deleteByName(String name){
        DeputyEntity deputy = deputyRepository.findById(name).get();
        if(deputy.equals(null))return false;
        else{
            deputyRepository.delete(deputy);
            return true;
        }

    }
    public boolean changeDeputy(String oldName,String name, int age, String party, int numberOfVotes,String user){
        if((deputyRepository.existsById(oldName))||(!(deputyRepository.existsById(name))&&(!oldName.equals(name))))
            return false;
        if(age<20)return false;
        if(party ==null || party =="")return false;
        if (numberOfVotes<0)return false;
        deleteByName(oldName);
        deputyRepository.save(new DeputyEntity(name, age, party, numberOfVotes,user));
        return true;
    }
}
