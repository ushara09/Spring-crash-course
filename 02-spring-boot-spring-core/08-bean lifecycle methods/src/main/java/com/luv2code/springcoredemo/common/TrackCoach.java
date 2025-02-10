package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach{

    public TrackCoach() {
        System.out.println("Constructor in "+ getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Run 10Km with in 30 minutes";
    }
}
