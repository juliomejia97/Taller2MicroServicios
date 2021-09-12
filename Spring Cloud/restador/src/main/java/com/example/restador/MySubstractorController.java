package com.example.restador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySubstractorController {
    @Autowired
    Environment environment;

    @GetMapping("/resta")
    public String division(@RequestParam int a, @RequestParam int b){
        String serverPort = environment.getProperty("local.server.port");
        int result= a - b;
        return "Result: "+result+" Answered from the server in port: "+serverPort;
    }
}
