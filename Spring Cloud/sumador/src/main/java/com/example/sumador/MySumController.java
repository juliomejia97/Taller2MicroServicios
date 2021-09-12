package com.example.sumador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySumController {
    @Autowired
    Environment environment;

    @GetMapping("/suma")
    public String suma(@RequestParam int a, @RequestParam int b){
        String serverPort = environment.getProperty("local.server.port");
        int result= a + b;
        return "Result: "+result+" Answered from the server in port: "+serverPort;
    }
    @GetMapping("/sumaInteger")
    public Integer sumaInteger(@RequestParam int a, @RequestParam int b){
        String serverPort = environment.getProperty("local.server.port");
        System.out.println("Ejecutando el ms en el puerto"+serverPort);
        Integer response = a+b;
        return response;
    }
}
