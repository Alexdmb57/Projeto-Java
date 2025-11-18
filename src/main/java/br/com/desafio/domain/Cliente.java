package br.com.desafio.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity // indica que a classe é uma entidade JPA
@Table(name = "cliente") // Nome da tabela no banco de dados
public class Cliente {
	
	// Campos Simples
    @Id // indica que o campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    //Id gerado automaticamente pelo banco de dados (auto-increment)
    private Long id;

    @Column(nullable = false, length = 100)
    //Campo obrigatório (não pode ser null) com tamanho máximo de 100 caracteres
    private String nome;

    @Column(nullable = false, length = 11, unique = true)
    // CPF sem máscara, exatamente 11 caracteres, e NÂO pode repetir no banco
    private String cpf; // sem máscara

    @Column(name = "data_criacao", nullable = false)
    // Data em que o cliente foi criado (preenchido automaticamente)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    // Data da última atualização do cliente (preenchido automaticamente)
    private LocalDateTime dataAtualizacao;
    
    // Relacionamento 1:1

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Endereco endereco; // Um cliente tem um endereço

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Telefone> telefones = new ArrayList<>(); // Um cliente pode ter vários telefones

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Email> emails = new ArrayList<>(); // Um cliente pode ter vários emails

    // CALLBAKS JPA

    @PrePersist
    // Executado antes de salvar no banco pela primeira vez
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    // Executado antes de atualizar um registro existente no banco
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    // getters e setters padrão abaixo

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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    // Setteer ESPECIAL para manter o relacionamento 2 vias
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
        // Garantir que o endereço aponte para este cliente
        if (endereco != null) {
            endereco.setCliente(this);
        }
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }
    
}
