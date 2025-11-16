package br.com.desafio.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // 1. Diz ao Spring que esta é uma interface de Repositório
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  //2. É so isso!
}