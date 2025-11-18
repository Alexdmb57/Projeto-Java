package br.com.desafio.dto;

public class TelefoneResponseDTO {

    private String tipo; // Exemplo: "celular", "residencial", "comercial"
    private String numero; // com máscara

    // Getters e setters

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}

