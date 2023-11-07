package me.dio.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import me.dio.domain.model.Cliente;
import me.dio.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	@Transactional
	public ResponseEntity<Cliente> inserirCliente(@RequestBody @Valid Cliente cliente, UriComponentsBuilder builder) {
		cliente = clienteService.inserirCliente(cliente);
		URI uri = builder.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(cliente);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		cliente = clienteService.atualizarCliente(id, cliente);
		return ResponseEntity.ok().body(cliente);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletarClientePorId(@PathVariable("id") Long id) {
		clienteService.excluirCliente(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> obterClientes() {
		List<Cliente> clientes = clienteService.buscarTodosClientes();
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obterClientePorId(@PathVariable("id") Long id) {
		Cliente cliente = clienteService.buscarCliente(id);
		return ResponseEntity.ok(cliente);
	}

}
