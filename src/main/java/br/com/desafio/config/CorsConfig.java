// Define o pacote onde essa classe está organizada dentro do projeto.
package br.com.desafio.config;

// Importa a anotação Configuration do Spring Framework.
import org.springframework.context.annotation.Configuration;
//Importa as classes necessárias para configurar o CORS.
import org.springframework.web.servlet.config.annotation.CorsRegistry;
// Importa a interface que permite sobrescrever configurações do Spring MVC,como CORS.
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Indica para o Spring que esta classe contém configurações.
// e deve ser carregada quando a aplicação iniciar.
@Configuration

// Define a classe CorsConfig que implementa a interface WebMvcConfigurer.
public class CorsConfig implements WebMvcConfigurer {

	
	//Indica que estamos sobrescrevendo um método que existe na interface WebMvcConfigurer.
    @Override
    
    // Configura as regras de CORS para a aplicação.
    public void addCorsMappings(CorsRegistry registry) {
    	
    	
        registry.addMapping("/api/**")// Aplica as regras de CORS para qualquer rota que comece com  API
        .allowedOrigins(
                "http://localhost:5173",
                "http://localhost:5174"
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permitir métodos HTTP específicos
            .allowedHeaders("*")// Permitir todos os cabeçalhos
            .allowCredentials(true); // Permitir envio de credenciais (cookies, autenticação)
    }
}