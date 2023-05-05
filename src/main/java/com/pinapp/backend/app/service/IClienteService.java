package com.pinapp.backend.app.service;

import java.util.List;

import com.pinapp.backend.app.model.Cliente;
import com.pinapp.backend.app.model.dto.ClienteDTO;

public interface IClienteService {

	public Cliente createCliente(Cliente cliente);
	public List<ClienteDTO> listClienteDTO();
	public String averageAgeCliente();
	public String standardDeviationAgeCliente();

}
