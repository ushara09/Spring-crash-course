package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define a private field for dependency
    private Coach myCoach;
    private Coach anotherCoach;

    @Autowired
    public void DemoController(@Qualifier("baseballCoach") Coach coach,
                               @Qualifier("baseballCoach") Coach secondCoach) {
        System.out.println("Constructor in " + getClass().getSimpleName());
        myCoach = coach;
        anotherCoach = secondCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check(){
        return "Comparing beans, myCoach == anotherCoach : " + (myCoach == anotherCoach);
    }


}
/*
* if you have one constructor the @Autowired annotation is optional
* */
