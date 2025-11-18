package br.com.desafio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity // indica que a classe é uma entidade JPA
@Table(name = "endereco") // Nome da tabela no banco de dados
public class Endereco {

    @Id // indica que o campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Id gerado automaticamente pelo banco de dados (auto-increment)
    private Long id;

    @JsonIgnore
    @OneToOne // Relacionamento 1:1 com Cliente
    @JoinColumn(name = "cliente_id", nullable = false, unique = true) // chave estrangeira
    private Cliente cliente;

    @Column(nullable = false, length = 8) // CEP sem máscara tem 8 caracteres
    private String cep;

    @Column(nullable = false, length = 150) // Logradouro (rua, avenida, etc.)
    private String logradouro;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 100) 
    private String cidade;

    @Column(nullable = false, length = 2) // UF tem 2 caracteres (DF, SP, RJ, etc.)
    private String uf;

    @Column(length = 150) // Informação opcional: complemento do endereço
    private String complemento;

    // getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
	
}
