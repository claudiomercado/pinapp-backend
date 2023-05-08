package com.pinapp.backend.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinapp.backend.app.model.Cliente;
import com.pinapp.backend.app.model.dto.ClienteDTO;
import com.pinapp.backend.app.service.IClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@PostMapping("/createClientes")
	public Cliente create(@RequestBody Cliente cliente) {
		return clienteService.createCliente(cliente);
	}

	@GetMapping("/listClientes")
	public List<ClienteDTO> getAll() {
		return clienteService.listClienteDTO();
	}

	@GetMapping("/averageAgeClientes")
	public String averageAge() {
		return clienteService.getAverageAgeCliente();
	}

	@GetMapping("/standardDeviationAgeClientes")
	public String standardDeviationAge() {
		return clienteService.standardDeviationAgeCliente();
	}

}
