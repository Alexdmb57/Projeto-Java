package br.com.desafio.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity // Indica que esta classe é uma entidade JPA
@Table(name = "telefone") // Mapeia a entidade para a tabela "telefone" no banco de dados
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID ferado automaticamente pelo banco de dados (auto-increment)
    private Long id;

    @JsonIgnore
    @ManyToOne // Muitos telefones podem pertencer a um cliente
    @JoinColumn(name = "cliente_id", nullable = false) //Cria a coluna "cliente_id" na tabela "telefone", FK para a tabela "cliente" e nullable = false -> Todo telefone deve estar associado a um cliente.
    private Cliente cliente;

    @Enumerated(EnumType.STRING) // Armazena o enum como String no banco de dados
    @Column(nullable = false, length = 20) 
    private TipoTelefone tipo;

    @Column(nullable = false, length = 11) // Máximo de 11 dígitos (incluindo DDD)
    private String numero; // só dígitos

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
