package com.example.calculadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RestController
public class CalculadoraController {
    @Autowired
    RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @GetMapping("/calculadora/suma")
    public String calculadoraSuma(@RequestParam int a,@RequestParam int b, @RequestParam String user){
        String response = restTemplate.getForObject("http://sumador/suma?a={a}&b={b}",String.class,a,b);
        createLog("suma",user,response);
        return response;
    }
    @GetMapping("/calculadora/multiplicacion")
    public String calculadoraMultip(@RequestParam int a,@RequestParam int b,@RequestParam String user){
        String response = restTemplate.getForObject("http://multip/multiplicacion?a={a}&b={b}",String.class,a,b);
        createLog("multiplicacion",user,response);
        return response;
    }
    @GetMapping("/calculadora/division")
    public String calculadoraDiv(@RequestParam int a,@RequestParam int b,@RequestParam String user){
        String response = restTemplate.getForObject("http://divisor/division?a={a}&b={b}",String.class,a,b);
        createLog("division",user,response);
        return response;
    }
    @GetMapping("/calculadora/resta")
    public String calculadoraSubs(@RequestParam int a,@RequestParam int b,@RequestParam String user){
        String response = restTemplate.getForObject("http://restador/resta?a={a}&b={b}",String.class,a,b);
        createLog("resta",user,response);
        return response;
    }

    @GetMapping("calculadora/logs")
    @Produces(MediaType.APPLICATION_JSON)
    public List<LogMessage> calculadoraLogs(){
        List<LogMessage> logs = null;
        try {
            Connection con = DatabaseUtil.getDatabaseConnection();
            logs = DatabaseUtil.getLogs(con);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return logs;
    }
    private void createLog(String operation, String userName, String result){
        try {
            Connection con = DatabaseUtil.getDatabaseConnection();
            DatabaseUtil.createLog(con,operation,userName,result);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
