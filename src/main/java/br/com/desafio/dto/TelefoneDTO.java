package br.com.desafio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import br.com.desafio.domain.TipoTelefone;

public class TelefoneDTO {

    @NotNull(message = "Tipo de telefone é obrigatório")
    private TipoTelefone tipo;

    @NotBlank(message = "Número de telefone é obrigatório")
    @Size(min = 10, max = 15, message = "Número de telefone deve ter pelo menos DDD + número")
    private String numero;

    // Getters e setters

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}

