package br.com.desafio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

// Configuração de segurança da aplicação
public class SecurityConfig {

    // Definição dos usuários em memória
    @Bean
    public UserDetailsService userDetailsService() {
        var admin = User.withUsername("admin")
                .password("123qwe!@#")  // senha do desafio
                .roles("ADMIN")
                .build();

        var usuario = User.withUsername("usuario")
                .password("123qwe123")  // senha do desafio
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, usuario);
    }

    // ATENÇÃO: apenas para estudo. Em produção, use BCrypt ou outro encoder forte.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // não encripta, aceita senha "crua"
    }

    
    // Security Filter Chain: O "porteiro" da aplicação.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
	        // 👉 1) HABILITA CORS usando a CorsConfig
	        .cors(Customizer.withDefaults())
	        
            // desabilita CSRF para facilitar testes via Postman (só API)
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth // regras de autorização
            		
            		 // 👉 2) LIBERA OPTIONS (pré-flight CORS) SEM AUTENTICAÇÃO
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // libera o /api/hello para qualquer um (sem login)
            		 // 🔓 exemplo: endpoint hello liberado só pra teste
                    // (se quiser proteger também, pode mudar)
                    .requestMatchers(HttpMethod.GET, "/api/hello").permitAll()
                // Libera swagger
                .requestMatchers(
					"/v3/api-docs/**",
					"/swagger-ui.html",
					"/swagger-ui/**",
					"/v2/api-docs/**",
					"/webjars/**",
					"/swagger-resources/**"
				).permitAll()

                // GET /api/clientes/** → ADMIN e USER podem
                .requestMatchers(HttpMethod.GET, "/api/clientes/**")
                    .hasAnyRole("ADMIN", "USER")

                // POST, PUT, DELETE /api/clientes/** → só ADMIN
                .requestMatchers(HttpMethod.POST, "/api/clientes/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/clientes/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/clientes/**").hasRole("ADMIN")

                // qualquer outra requisição precisa estar autenticada
                .anyRequest().authenticated()
            )

            // autenticação HTTP Basic (usuário/senha no header)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
