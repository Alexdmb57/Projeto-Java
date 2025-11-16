package br.com.desafio.backend;

import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable // 1. Indica que esta classe será "embutida" em outra (no Cliente)
@Data       // 2. Lombok para getters/setters
public class Endereco {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf; // Unidade Federativa (Estado)
}