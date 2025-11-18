package br.com.desafio.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailDTO { // 

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String enderecoEmail;

    // Getters e setters

    public String getEnderecoEmail() {
        return enderecoEmail;
    }

    public void setEnderecoEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }
}
