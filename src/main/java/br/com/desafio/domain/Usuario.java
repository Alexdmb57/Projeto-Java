package br.com.desafio.domain;


import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, unique = true, length = 50)
	    private String username;

	    @Column(nullable = false, length = 255)
	    private String senha;

	    @Column(nullable = false, length = 20)
	    private String role; // ADMIN ou USER

	    // Getters e setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getSenha() {
	        return senha;
	    }

	    public void setSenha(String senha) {
	        this.senha = senha;
	    }

	    public String getRole() {
	        return role;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }
	    
}