package br.com.desafio.exception;

// Execeção personalizada para CEP inválido.
public class CepInvalidoException extends RuntimeException {
	
	// Construtor que aceita uma mensagem de erro.
    public CepInvalidoException(String mensagem) {
        super(mensagem);
    }
}