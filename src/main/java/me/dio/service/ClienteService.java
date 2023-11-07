package me.dio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.domain.model.Cliente;
import me.dio.domain.model.Endereco;
import me.dio.domain.repository.ClienteRepository;
import me.dio.domain.repository.EnderecoRepository;
import me.dio.exception.ClienteNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ViaCepService viaCepService;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente inserirCliente(Cliente cliente) {
		return inserirClienteComCep(cliente);
	}

	public Cliente atualizarCliente(Long id, Cliente cliente) {
		if (!clienteRepository.existsById(id))
			throw new ClienteNotFoundException();
		cliente.setId(id);
		return inserirClienteComCep(cliente);
	}

	public void excluirCliente(Long id) {
		if (!clienteRepository.existsById(id))
			throw new ClienteNotFoundException();
		clienteRepository.deleteById(id);
	}

	public Cliente buscarCliente(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException());
		return cliente;
	}

	public List<Cliente> buscarTodosClientes() {
		return clienteRepository.findAll();
	}

	private Cliente inserirClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco enderecoNovo = viaCepService.consultarCep(cep);
			enderecoNovo = enderecoRepository.save(enderecoNovo);
			return enderecoNovo;
		});
		cliente.setEndereco(endereco);
		cliente = clienteRepository.save(cliente);
		return cliente;
	}
}
