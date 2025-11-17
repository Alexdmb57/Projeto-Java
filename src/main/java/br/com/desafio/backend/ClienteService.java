package br.com.desafio.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

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
                }
            } catch (Exception e) {
                // Em caso de falha na requisição
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Falha ao consultar o CEP. Verifique o formato ou a conexão.");
            }
        } else if (cep != null && !cep.isEmpty()) {
            // Se o CEP não for 8 dígitos, mas foi preenchido, lançamos erro.
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP deve ter 8 dígitos.");
        }

        // Salvamos o cliente no repositório.
        return repository.save(cliente);
    }
}