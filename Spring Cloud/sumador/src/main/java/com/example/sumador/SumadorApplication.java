package com.example.sumador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

//Comando para correr otra app en otro puerto
//mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=PUERTO
public class SumadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SumadorApplication.class, args);
	}

}
