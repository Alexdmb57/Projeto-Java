package br.com.desafio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Hello", description = "Endpoint de verificação da API")
@RestController                       // Diz que essa classe responde requisições REST (HTTP)
@RequestMapping("/api")               // Prefixo para os endpoints dessa classe
public class HelloController {

    @GetMapping("/hello")             // Responde a GET /api/hello
    public String hello() {
        return "API do desafio está rodando com sucesso! 🚀";
    }
}
