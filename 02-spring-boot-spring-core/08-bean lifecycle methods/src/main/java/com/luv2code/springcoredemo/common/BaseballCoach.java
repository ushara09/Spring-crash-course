package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{

    public BaseballCoach() {
        System.out.println("Constructor in "+ getClass().getSimpleName());
    }

    //define init method
    @PostConstruct
    public void doMyStartUpStuff(){
        System.out.println("In doMyStartUpStuff() : "+getClass().getSimpleName());
    }

    //define destroy method
    @PreDestroy
    public void doMyCleanUpStuff(){
        System.out.println("In doMyCleanUpStuff() : "+getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice 30 minutes batting in the ground !";
    }
}
