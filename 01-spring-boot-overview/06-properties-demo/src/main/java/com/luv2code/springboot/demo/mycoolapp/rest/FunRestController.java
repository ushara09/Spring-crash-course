package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    //injecting values from property file
    @Value("${coach.name}")
    private String couchName;
    @Value("${team.name}")
    private String teamName;

    @GetMapping("/team-info")
    public String getTeamInfo(){
        String text = "Coach name is " + couchName + ". And the team name is "+teamName;
        return text;
    }


    @GetMapping("/")
    public String sayHello(){
        return "Hello Thimal !";
    }

    @GetMapping("/workouts")
    public String dailyWorkout(){
        return "50 push ups, pull ups and dips!";
    }

    @GetMapping("/fortune")
    public String dailyFortune(){
        return "Today is your best day !!!";
    }

}
