package br.com.desafio.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.desafio.dto.ViaCepResponse;
import br.com.desafio.exception.CepInvalidoException;

// Serviço para buscar endereço via CEP usando a API ViaCEP
@Service // Anotação para marcar como serviço do Spring
public class CepService {

	// URL base do ViaCEP com placeholder para o CEP
    private static final String URL_VIA_CEP = "https://viacep.com.br/ws/{cep}/json";

    public ViaCepResponse buscarEnderecoPorCep(String cepSemMascara) {
        RestTemplate restTemplate = new RestTemplate();

        ViaCepResponse response = restTemplate.getForObject(
                URL_VIA_CEP,
                ViaCepResponse.class,
                cepSemMascara
        );

        // Se não veio nada ou veio erro=true, consideramos CEP inválido
        if (response == null || Boolean.TRUE.equals(response.getErro())) {
            throw new CepInvalidoException("CEP informado é inválido ou não encontrado.");
        }

        return response;
    }
}

