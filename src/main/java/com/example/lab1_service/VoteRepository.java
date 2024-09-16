package com.example.lab1_service;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VoteRepository {
    static List<DeputyEntity> db = new ArrayList<>();

    public List<DeputyEntity>  getAll(){
        return new ArrayList<DeputyEntity>(db);
    }

    public DeputyEntity getById(String name){
        for (DeputyEntity deputy:
             db) {
            if(deputy.name.equals(name) ){
                return deputy;
            }
        }
        return null;
    }
    public boolean addNew( String name, int age, String party, int numberOfVotes){
        for (DeputyEntity deputy:db) {
            if(deputy.name.equals(name)){
                return false;
            }
        }
        if(age<20)return false;
        if(party ==null || party =="")return false;
        if (numberOfVotes<0)return false;
        db.add(new DeputyEntity(name,age,party,numberOfVotes));
        return true;

    }
    public boolean deleteByName(String name){
        for (DeputyEntity deputy:
             db) {
            if(deputy.name.equals(name)){
                db.remove(deputy);
                return true;
            }
        }
        return false;
    }
    public boolean changeDeputy(String oldName,String name, int age, String party, int numberOfVotes){
        for (DeputyEntity deputy:db) {
            if(!(deputy.name.equals(oldName))||((deputy.name.equals(name))&&(!oldName.equals(name))))
                return false;
        }
        if(age<20)return false;
        if(party ==null || party =="")return false;
        if (numberOfVotes<0)return false;
        deleteByName(oldName);
        db.add(new DeputyEntity(name,age,party,numberOfVotes));
        return true;
    }
}
