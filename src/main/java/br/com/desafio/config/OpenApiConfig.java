package br.com.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Classe principal usada para configurar a documentação OpenAPI (Swagger).
import io.swagger.v3.oas.models.OpenAPI;
//Classe usada para configurar informações como título, descrição e versão da API.
import io.swagger.v3.oas.models.info.Info;

// Anotação que indica que esta classe é uma configuração do Spring.
@Configuration
// Declaração da classe de configuração OpenApiConfig.
public class OpenApiConfig {

	// Método que cria e configura o bean OpenAPI.
    @Bean
    
    // Método que define as informações da API, como título, descrição e versão.
    public OpenAPI desafioOpenAPI() {
    	
        return new OpenAPI()
            .info(new Info() // O objeto Info contém os metadados da documentação visualizada pelo Swagger UI.
                .title("API de Clientes - Desafio Backend") // Título que aparecerá no Swagger.
                .description("API para cadastro e consulta de clientes, com autenticação, máscaras (CPF/CEP/telefone) e integração com ViaCEP.") // Descrição detalhada sobreo que a API faz.
                .version("v1.0.0")); // Versão da API.
    }
}

