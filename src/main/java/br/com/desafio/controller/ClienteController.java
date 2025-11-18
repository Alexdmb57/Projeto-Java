package br.com.desafio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import br.com.desafio.dto.ClienteDTO;
import br.com.desafio.dto.ClienteResponseDTO;
import br.com.desafio.service.ClienteService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/clientes") //Caminho base de todos os endpoints: /api/clientes
@Tag(name = "Clientes", description = "Operações de CRUD de clientes") // Agrupa no Swagger na seção "Clientes"
public class ClienteController {

    private final ClienteService clienteService;
    
    // Injeção de dependência automática pelo Spring
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    // Atualizar (PUT)
    @Operation(summary = "Atualiza um cliente", description = "Atualiza os dados do cliente com o ID especificado.")
    @PutMapping("/{id}")
    public ClienteResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
		return clienteService.atualizarCliente(id, dto);
	}
    
    // Deletar (DELETE)
    @Operation(summary = "Deleta um cliente", description = "Deleta o cliente com o ID especificado.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
		clienteService.deletarCliente(id);
	}
    
    // Listar todos (GET)
   // @GetMapping
   // public List<ClienteDTO> listarTodos() {
   //     return clienteService.listarTodos();
   // }
    @Operation(summary = "Lista todos os clientes", description = "Retorna a lista de clientes com dados mascarados.")
    @GetMapping
    public List<ClienteResponseDTO> listarTodos() {
        return clienteService.listarTodos();
    }
    
    // Buscar por ID (GET)
    @Operation(summary = "Busca um cliente por ID", description = "Retorna os dados do cliente com o ID especificado, com dados mascarados.")
    @GetMapping("/{id}")
    public ClienteResponseDTO buscar(@PathVariable Long id) {
        return clienteService.buscar(id);
    }
    
    // Criar (POST)
    @Operation(summary = "Cria um novo cliente", description = "Cria um novo cliente com os dados fornecidos.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO criar(@Valid @RequestBody ClienteDTO dto) {
        return clienteService.criarCliente(dto);
    }
}

