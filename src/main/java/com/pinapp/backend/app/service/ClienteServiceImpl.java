package com.pinapp.backend.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinapp.backend.app.model.Cliente;
import com.pinapp.backend.app.model.dto.ClienteDTO;
import com.pinapp.backend.app.repository.IClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepository;

	@Override
	public Cliente createCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public String getAverageAgeCliente() {
		double average = this.averageAgeCliente();
		return "El promedio de edad es: " + average;
	}

	@Override
	public String standardDeviationAgeCliente() {
		double deviation = this.getStandardDeviation();
		return "La desviacion estandar sobre la muestra es: " + deviation;
	}

	@Override
	public List<ClienteDTO> listClienteDTO() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDTO> clientesConFechaProbableMuerte = new ArrayList<>();

		for (Cliente cliente : clientes) {
			int edad = cliente.getEdad();
			LocalDate fechaNacimiento = cliente.getFecha_nacimiento();
			LocalDate fechaProbableMuerte = calculateDate(edad);

			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setNombre(cliente.getNombre());
			clienteDTO.setApellido(cliente.getApellido());
			clienteDTO.setEdad(edad);
			clienteDTO.setFecha_nacimiento(fechaNacimiento);
			clienteDTO.setFecha_posible_muerte(fechaProbableMuerte);

			clientesConFechaProbableMuerte.add(clienteDTO);
		}
		return clientesConFechaProbableMuerte;
	}

	// Cálculo de la probable fecha de muerte con 76 años como expectativa de vida
	private LocalDate calculateDate(int edad) {
		LocalDate currentDate = LocalDate.now();

		int remainingYears = 76 - edad;

		LocalDate probableDateDeath = currentDate.plusYears(remainingYears);
		return probableDateDeath;
	}

	// Cálculo del promedio de edad
	public double averageAgeCliente() {
		List<Cliente> clientes = clienteRepository.findAll();

		double totalAge = clientes.stream()
				.mapToDouble(x -> x.getEdad())
				.sum();

		double average = (totalAge / clientes.size());
		return average;
	}

	// Cálculo de la desviacion estandar de la muestra
	public double getStandardDeviation() {
		double prom = this.averageAgeCliente();
		List<Cliente> clientes = clienteRepository.findAll();
		double sumaCuadrados = 0;

		List<Double> edades = clientes.stream()
				.map(x -> (x.getEdad() - prom))
				.collect(Collectors.toList());

		for (double edad : edades) {
			sumaCuadrados += Math.pow(edad, 2);
		}

		double varianzaEdades = sumaCuadrados / clientes.size();
		return Math.sqrt(varianzaEdades);
	}

}
