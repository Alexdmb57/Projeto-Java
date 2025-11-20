package br.com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Classe principal da aplicação Spring Boot
@SpringBootApplication(scanBasePackages = "br.com.desafio")
public class DesafioBackendApplication {

    public static void main(String[] args) {
    	// Inicia a aplicação Spring Boot
        SpringApplication.run(DesafioBackendApplication.class, args);
    }
}