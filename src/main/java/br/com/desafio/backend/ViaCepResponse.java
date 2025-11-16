package br.com.desafio.backend;

import lombok.Data;

// Esta classe mapeia a resposta JSON que o ViaCEP envia
@Data
public class ViaCepResponse {

  private String cep;
  private String logradouro;
  private String bairro;
  private String localidade; // Mapeia para "Cidada"
  private String uf;
  private boolean erro; // O ViaCEP retorna "true" se o CEP não for encontrado
}