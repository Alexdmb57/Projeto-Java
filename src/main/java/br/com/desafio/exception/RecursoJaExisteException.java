package br.com.desafio.exception;

// Exceção personalizada para indicar que um recurso já existe.
public class RecursoJaExisteException extends RuntimeException {

	// Construtor que aceita uma mensagem de erro.
    public RecursoJaExisteException(String mensagem) {
        super(mensagem); 
    }
}