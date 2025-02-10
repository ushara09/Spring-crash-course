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

    @Override
    public String getDailyWorkout() {
        return "Practice 30 minutes batting in the ground !";
    }
}
