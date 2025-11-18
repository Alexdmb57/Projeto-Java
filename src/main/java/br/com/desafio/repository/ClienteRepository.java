package br.com.desafio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.domain.Cliente;

//  Repository responsável por ACESSAR o banco de dados.

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf); // Busca um cliente pelo CPF fornecido.
    
    boolean existsByCpf(String cpf); // Verifica se um cliente com o CPF fornecido já existe no banco de dados.
    
}
