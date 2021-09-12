package com.example.multip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyMultipController {
    @Autowired
    Environment environment;

    @Autowired
    RestTemplate restTemplate;
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @GetMapping("/multiplicacion")
    public String multip(@RequestParam int a,@RequestParam int b){
        String serverPort = environment.getProperty("local.server.port");
        Integer response = 0;
        for (int i=0; i < a; i++){
            int aux = response;
            response = suma(b,aux);
        }
        return "Resultado: "+response+" desde el ms en el puerto: "+serverPort;
    }

    private Integer suma(int a, int b){
        Integer response = restTemplate.getForObject("http://sumador/sumaInteger?a={b}&b={b}",Integer.class,a,b);
        return response;
    }
}
