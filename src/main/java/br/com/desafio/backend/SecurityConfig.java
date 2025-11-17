package br.com.desafio.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  // Configuração das URLs e Permissões (Amin vs Padrão)
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf().disable() // Desabilita proteção CSRF (comum para APIs REST)
      .authorizeRequests()
        // Usuário ADMIN (oermissão total para POST e GET)
        .antMatchers(org.springframework.http.HttpMethod.POST, "/clientes").hasRole("ADMIN")
        .antMatchers(org.springframework.http.HttpMethod.PUT, "/clientes/**").hasRole("ADMIN")
        .antMatchers(org.springframework.http.HttpMethod.DELETE, "/clientes/**").hasRole("ADMIN")
        // Usuário padrão (somente permissão de GET)
        .antMatchers(org.springframework.http.HttpMethod.GET, "/clientes/**").hasAnyRole("ADMIN", "PADRAO")
        .anyRequest().authenticated()
        .and()
      .httpBasic(); // Usa autenticação HTTP Basic (usuário/senha no header)
    return http.build();
  }

  // Criação dos Usuários em Memória (Admin e Padrão)
  @Bean
  public UserDetailsService userDetailsService() {
    // Senhas: 123@#@# e 123@#123
    UserDetails admin = User.withDefaultPasswordEncoder()
      .username("admin")
      .password("123qwe!@#")
      .roles("ADMIN", "PADRAO")
      .build();

    UserDetails padrao = User.withDefaultPasswordEncoder()
      .username("padrao")
      .password("123qwe123")
      .roles("PADRAO")
      .build();

    return new InMemoryUserDetailsManager(admin, padrao);
  }  
}