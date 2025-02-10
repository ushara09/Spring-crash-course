package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

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
