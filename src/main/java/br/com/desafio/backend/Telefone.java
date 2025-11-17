package br.com.desafio.backend;

import javax.persistence.Embeddable;
import lombok.Data;

@Embeddable // 1. Assim como o Endereco, esta classe pode ser "embutida"
@Data
public class Telefone {

    private TipoTelefone tipo; // Ex: "Celular", "Residencial"
    private String numero; // Ex: "11999998888"
}