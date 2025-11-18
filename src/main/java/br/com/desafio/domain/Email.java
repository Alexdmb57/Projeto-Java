
package br.com.desafio.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity // Indica que essa classe vira uma tabela no banco de dados
@Table(name = "email") // Define o nome da tabela no banco de dados
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ID gerado automaticamente pelo banco (auto-increment)
    private Long id;

    @JsonIgnore
    @ManyToOne // Muitos emails podem pertencer a um cliente
    @JoinColumn(name = "cliente_id", nullable = false) // Define a coluna clente_id como a FK (chave estrangeira)
    private Cliente cliente;

    @Column(name = "endereco_email", nullable = false, length = 255) // Define a coluna endereco_email. Campo obrigatório com tamanho máximo de 255 caracteres.
    private String enderecoEmail;

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

    public String getEnderecoEmail() {
        return enderecoEmail;
    }

    public void setEnderecoEmail(String enderecoEmail) {
        this.enderecoEmail = enderecoEmail;
    }
	
}