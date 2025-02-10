package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BaseballCoach implements Coach{

    public BaseballCoach() {
        System.out.println("Constructor in "+ getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice 30 minutes batting in the ground !";
    }
}
