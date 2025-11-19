package br.com.desafio.exception;

import java.util.HashMap;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

// Classe para tratar exceções globais na API e controlar as respostas de erro
@RestControllerAdvice
public class ApiExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);
	
    // Erros de validação dos DTOs (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(erro -> {
            String campo = ((FieldError) erro).getField();
            String mensagem = erro.getDefaultMessage();
            erros.put(campo, mensagem);
        });

        // log opcional (útil pra debug de validação)
        logger.warn("Erro de validação nos campos: {}", erros);
        
        return erros;
    }
    
    // ...

    // Erro de CEP inválido
    @ExceptionHandler(CepInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleCepInvalido(CepInvalidoException ex) {
    	  logger.warn("CEP inválido: {}", ex.getMessage());
    	  
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        return erro;
    }

    // (mantém os outros handlers já existentes)


    // Erro de recurso já existente (ex: CPF duplicado)
    @ExceptionHandler(RecursoJaExisteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleRecursoJaExiste(RecursoJaExisteException ex) {
    	 logger.warn("Recurso já existe: {}", ex.getMessage());
    	 
    	Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        return erro;
    }

    // (Opcional) Qualquer outro erro genérico não tratado
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGeral(Exception ex) {
    	// 👉 AQUI é onde vamos ver o stacktrace real no console
        logger.error("Erro inesperado na API", ex);
        
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", "Ocorreu um erro inesperado. Tente novamente mais tarde.");
        
        return erro;
    }
}