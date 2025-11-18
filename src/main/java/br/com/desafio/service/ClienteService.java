package br.com.desafio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.domain.Cliente;
import br.com.desafio.domain.Email;
import br.com.desafio.domain.Endereco;
import br.com.desafio.domain.Telefone;
import br.com.desafio.dto.ClienteDTO;
import br.com.desafio.dto.ClienteResponseDTO;
import br.com.desafio.dto.EmailDTO;
import br.com.desafio.dto.EmailResponseDTO;
import br.com.desafio.dto.EnderecoDTO;
import br.com.desafio.dto.EnderecoResponseDTO;
import br.com.desafio.dto.TelefoneDTO;
import br.com.desafio.dto.TelefoneResponseDTO;
import br.com.desafio.exception.RecursoJaExisteException;
import br.com.desafio.repository.ClienteRepository;
import br.com.desafio.util.MascaraUtil;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final CepService cepService;

    public ClienteService(ClienteRepository clienteRepository, CepService cepService) {
        this.clienteRepository = clienteRepository;
        this.cepService = cepService;
    }

    // ============ CRUD ============

    @Transactional
    public ClienteResponseDTO criarCliente(ClienteDTO dto) {
        // Limpa máscara do CPF (só dígitos)
        String cpfLimpo = limparMascara(dto.getCpf());

        // Validação de unicidade antes de salvar
        if (clienteRepository.existsByCpf(cpfLimpo)) {
            throw new RecursoJaExisteException("Já existe um cliente cadastrado com o CPF informado.");
        }

        // Monta entidade a partir do DTO
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(cpfLimpo);

        // Endereço (com ViaCEP)
        if (dto.getEndereco() != null) {
            Endereco endereco = montarEnderecoComViaCep(dto.getEndereco());
            cliente.setEndereco(endereco);
        }

        // Telefones
        if (dto.getTelefones() != null) {
            for (TelefoneDTO telDTO : dto.getTelefones()) {
                Telefone telefone = new Telefone();
                telefone.setCliente(cliente);
                telefone.setTipo(telDTO.getTipo());
                telefone.setNumero(limparMascara(telDTO.getNumero())); // persiste sem máscara
                cliente.getTelefones().add(telefone);
            }
        }

        // E-mails
        if (dto.getEmails() != null) {
            for (EmailDTO emailDTO : dto.getEmails()) {
                Email email = new Email();
                email.setCliente(cliente);
                email.setEnderecoEmail(emailDTO.getEnderecoEmail());
                cliente.getEmails().add(email);
            }
        }

        Cliente salvo = clienteRepository.save(cliente);

        // Retorna DTO de resposta com máscaras
        return toDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> toDTO(cliente))   // evita bug chato do this::toDTO
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO buscar(Long id) {
    	// Busca o cliente ou lança exceção se não encontrar
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        // Converte Entity -> ResponseDTO (com máscaras e formatações)
        return toDTO(cliente);
    }
    
    @Transactional
    public ClienteResponseDTO atualizarCliente(Long id, ClienteDTO dto) {
    	// Busca o cliente ou lança exceção se não encontrar
		Cliente cliente = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		// Atualiza campos
		cliente.setNome(dto.getNome());

		//CPF não pode ser alterado
		String cpfLimpo = limparMascara(dto.getCpf());
		if (!cliente.getCpf().equals(cpfLimpo)) {
			throw new IllegalArgumentException("O CPF do cliente não pode ser alterado.");
		}
		
		// Atualiza endereço se fornecido
		if (dto.getEndereco() != null) {
			Endereco endereco = montarEnderecoComViaCep(dto.getEndereco());
			cliente.setEndereco(endereco);
		}

		// Atualiza telefones
		cliente.getTelefones().clear();
		if (dto.getTelefones() != null) {
			for (TelefoneDTO telDTO : dto.getTelefones()) {
				Telefone telefone = new Telefone();
				telefone.setCliente(cliente);
				telefone.setTipo(telDTO.getTipo());
				telefone.setNumero(limparMascara(telDTO.getNumero()));
				cliente.getTelefones().add(telefone);
			}
		}

		// Atualiza e-mails
		cliente.getEmails().clear();
		if (dto.getEmails() != null) {
			for (EmailDTO emailDTO : dto.getEmails()) {
				Email email = new Email();
				email.setCliente(cliente);
				email.setEnderecoEmail(emailDTO.getEnderecoEmail());
				cliente.getEmails().add(email);
			}
		}

		// Salva as alterações no banco
		Cliente atualizado = clienteRepository.save(cliente);
		
		// Converte para ResponseDTO (com máscaras)
		return toDTO(atualizado);
	}
    
    
    @Transactional
    public void deletarCliente(Long id) {
    	// Busca o cliente ou lança exceção se não encontrar
    	Cliente cliente = clienteRepository.findById(id)
    								.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    	// Deleta o cliente
    	clienteRepository.delete(cliente);
    }

    // ============ MAPEAMENTOS ============

    private Endereco montarEnderecoComViaCep(EnderecoDTO dto) {
    	// Limpa máscara do CEP (só dígitos)
        String cepLimpo = limparMascara(dto.getCep());

        // Chama ViaCEP para buscar os dados do endereço
        var viaCep = cepService.buscarEnderecoPorCep(cepLimpo);

        // Cria entidade Endereco
        Endereco endereco = new Endereco();
        endereco.setCep(cepLimpo);

        // Preenche com dados do ViaCEP
        endereco.setLogradouro(viaCep.getLogradouro());
        endereco.setBairro(viaCep.getBairro());
        endereco.setCidade(viaCep.getCidade());
        endereco.setUf(viaCep.getUf());

        // Complemento: se o usuário informou, usamos o do DTO,
        // senão podemos usar o do ViaCEP
        if (dto.getComplemento() != null && !dto.getComplemento().isBlank()) {
            endereco.setComplemento(dto.getComplemento());
        } else {
            endereco.setComplemento(viaCep.getComplemento());
        }

        // Permitir que o usuário sobrescreva os campos
        if (dto.getLogradouro() != null && !dto.getLogradouro().isBlank()) {
            endereco.setLogradouro(dto.getLogradouro());
        }
        if (dto.getBairro() != null && !dto.getBairro().isBlank()) {
            endereco.setBairro(dto.getBairro());
        }
        if (dto.getCidade() != null && !dto.getCidade().isBlank()) {
            endereco.setCidade(dto.getCidade());
        }
        if (dto.getUf() != null && !dto.getUf().isBlank()) {
            endereco.setUf(dto.getUf());
        }

        // Retorna um Endereco COMPLETO, validado e pronto para persistência
        return endereco;
    }

    // 👉 ESTE é o método que o Eclipse estava “pedindo”
    public ClienteResponseDTO toDTO(Cliente cliente) {
    	// Cria objeto de resposta
        ClienteResponseDTO dto = new ClienteResponseDTO();
        
        // Id, nome (copiados diretamente)
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        // CPF com máscara -> banco guarda sem, front recebe com máscara
        dto.setCpf(MascaraUtil.cpf(cliente.getCpf()));

        // Endereço
        if (cliente.getEndereco() != null) {
            EnderecoResponseDTO e = new EnderecoResponseDTO();
            // CEP com máscara
            e.setCep(MascaraUtil.cep(cliente.getEndereco().getCep()));
            // Copia campos do endereço
            e.setLogradouro(cliente.getEndereco().getLogradouro());
            e.setBairro(cliente.getEndereco().getBairro());
            e.setCidade(cliente.getEndereco().getCidade());
            e.setUf(cliente.getEndereco().getUf());
            e.setComplemento(cliente.getEndereco().getComplemento());
            
            dto.setEndereco(e);
        }

        // Telefones
        dto.setTelefones(
            cliente.getTelefones().stream().map(t -> {
                TelefoneResponseDTO td = new TelefoneResponseDTO();
                // tipo como String (ex.: "CELULAR")
                String tipoStr = t.getTipo().name();
                td.setTipo(tipoStr);
                td.setNumero(MascaraUtil.telefone(tipoStr, t.getNumero())); // com máscara
                return td;
            }).toList()
        );

        // E-mails
        dto.setEmails(
            cliente.getEmails().stream().map(e1 -> {
                EmailResponseDTO ed = new EmailResponseDTO();
                ed.setEnderecoEmail(e1.getEnderecoEmail());
                return ed;
            }).toList()
        );

        return dto;
    }

    // ============ UTIL ============

    private String limparMascara(String valor) {
        if (valor == null) return null;
        return valor.replaceAll("\\D", ""); // remove tudo que não é dígito
    }
}
