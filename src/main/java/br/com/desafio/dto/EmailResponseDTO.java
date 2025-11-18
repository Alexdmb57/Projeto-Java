package br.com.desafio.dto;


public class EmailResponseDTO {

    private String enderecoEmail; // E-mail já salvo e validado pelo backend.
    
    // Getters e setters

    public String getEnderecoEmail() {
        return enderecoEmail;
    }

    public void setEnderecoEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }
}
