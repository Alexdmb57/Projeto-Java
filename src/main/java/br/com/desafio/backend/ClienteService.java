package br.com.desafio.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service // Marca esta classe como um Serviço de Negócio
public class ClienteService {

  @Autowired
  private ClienteRepository repository;

  // Ferramenta para fazer requisições HTTP (busca o CEP)
  private final RestTemplate restTemplate = new RestTemplate();
  
  public Cliente cadastrar(Cliente cliente) {
    
    String cep = cliente.getEndereco().getCep();

    // Lógica de integração com o ViaCEP
    if (cep != null && cep.length() == 8){
      String url = "https://viacep.com.br/ws/" + cep + "/json/";

      try {
        // Faz a requisição e mapeia a resposta para a nossa classe ViaCepResponse
        ViaCepResponse response = restTemplate.getForObject(url, ViaCepResponse.class);

        if (response != null && !response.isErro()) {
          // Preenche os campos do Endereco do Cliente com a resposta do ViaCEP
          cliente.getEndereco().setLogradouro(response.getLogradouro());
          cliente.getEndereco().setBairro(response.getBairro());
          cliente.getEndereco().setCidade(response.getLocalidade());
          cliente.getEndereco().setUf(response.getUf());
          // O complemento é mantido (pois o usuário pode ter digitado antes)
        }
      } catch (Exception e) {
        // Em caso de falha na requisição, laçamos um erro 400
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falha ao consultar o CEP. Verifique o formato ou a conexão.");
      }
    } else if (cep != null && !cep.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP deve ter 8 dígitos.");
    }

    // Salvamos o cliente no repositório.
    return repository.save(cliente);
  }
}