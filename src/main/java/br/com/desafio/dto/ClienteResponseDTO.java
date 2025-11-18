package br.com.desafio.dto;

import java.util.List;

public class ClienteResponseDTO { // DTO usado para DEVOLVER dados ao front-end

    private Long id; // ID do cliente no banco de dados.
    private String nome; // Nome já tratado pelo service (se necessário).
    private String cpf; // com máscara	
    private EnderecoResponseDTO endereco; // DTO do endereço associado ao cliente
    private List<TelefoneResponseDTO> telefones; // Lista de DTOs de telefones associados ao cliente
    private List<EmailResponseDTO> emails; // Lista de DTOs de emails associados ao cliente

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public EnderecoResponseDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponseDTO endereco) {
        this.endereco = endereco;
    }

    public List<TelefoneResponseDTO> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneResponseDTO> telefones) {
        this.telefones = telefones;
    }

    public List<EmailResponseDTO> getEmails() {
        return emails;
    }

    public void setEmails(List<EmailResponseDTO> emails) {
        this.emails = emails;
    }
}
