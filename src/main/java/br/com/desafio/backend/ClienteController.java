package br.com.desafio.backend;

import javax.validation.Valid; // IMPORTE O @Valid


import br.com.desafio.backend.TipoTelefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController //  Marca a classe como um Controller de API REST
@RequestMapping("/clientes") // 2. Qualquer requisição para o http://localhost:8080/clientes vai cair aqui
public class ClienteController {

  @Autowired 
    private ClienteService service;

  @PostMapping //  Define que este método responde a requisições POST (criação)
  @ResponseStatus(HttpStatus.CREATED) // 5. Define que a resposta HTTP de sucessso será 201 (Created)
  public Cliente cadastrar(@RequestBody @Valid Cliente cliente) { //  @RequestBody: Converte o JSON enviado no corpo da requisição para um objeto Cliente

    // lógica para verificar se existe pelo menos um celular
    boolean temCelular = cliente.getTelefones() .stream()
      .anyMatch(t -> t.getTipo() == TipoTelefone.CELULAR);

      if (!temCelular) {
        // Se não tiver celular, lançamos um erro 400 (Bad Request) com a mensagem
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Obrigatório haver pelo menos um telefone do tipo CELULAR.");
      }
      
    // Se a validação do celular passar, salvamos:
    return service.cadastrar(cliente);
  }
}