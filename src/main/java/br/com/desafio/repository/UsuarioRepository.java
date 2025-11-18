package br.com.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.domain.Usuario;

//Repository responsável por ACESSAR o banco de dados.

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	// Busca um usuário pelo nome de usuário fornecido.

    Optional<Usuario> findByUsername(String username);
}
