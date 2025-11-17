package br.com.desafio.backend;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.Embedded; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity //  Diz ao JPA que esta classe é uma "entidade" (tabela do banco)
@Data //  Lombok: Cria getters, setters, toString, etc. automaticamente
public class Cliente {

  @Id //  Marca este campo como a Chave Primária (ID)
  @GeneratedValue(strategy = GenerationType.IDENTITY) // Define o ID como auto-incremento
  private Long id;

  // --- Campos pedidos no Desafio ---

  @NotBlank // Não pode ser nulo nem vazio
  @CPFValido // Valida o CPF com a nossa anotação personalizada
  private String nome;

  @NotBlank // Não pode ser nulo nem vazio
  @Size(min = 11, max = 11) // Deve ter exatamente 11 caracteres
  private String cpf;

  @NotBlank
  @Email // Deve ter um formato de email válido
  private String email;

  @Embedded // Diz ao JPA para "embutir" a classe Endereco aqui
  private Endereco endereco;

  @ElementCollection // Diz ao JPA que este é um conjunto de elementos
  @CollectionTable(name = "CLIENTE_TELEFONES", joinColumns = @JoinColumn(name = "cliente_id")) // Configura a tabela
  private Set<Telefone> telefones; // Um "Set" (conjunto) de Telefones
}