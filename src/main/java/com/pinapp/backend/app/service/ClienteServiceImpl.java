package com.pinapp.backend.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	public String averageAgeCliente() {
		double average = this.averageAgeClienteProm();
		return "El promedio de edades de los clientes es: "+average;
	}

	@Override
	public String standardDeviationAgeCliente() {
		double deviation = this.getStandardDeviation();
		return "La desviacion estandar sobre la muestra obtenida es: "+deviation;
	}
	
	@Override
	public List<ClienteDTO> listClienteDTO() {
		List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clientesConFechaProbableMuerte = new ArrayList<>();

        for (Cliente cliente : clientes) {
            int edad = cliente.getEdad();
            LocalDate fechaNacimiento = cliente.getFecha_nacimiento();
            LocalDate fechaProbableMuerte = calcularFechaProbableMuerte(fechaNacimiento, edad);
            
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
					 			 
				 
	private LocalDate calcularFechaProbableMuerte(LocalDate fechaNacimiento, int edad) {
		LocalDate fechaActual = LocalDate.now();
        int añosRestantes = 76 - edad; // Esperanza de vida de la población
        LocalDate fechaProbableMuerte = fechaActual.plusYears(añosRestantes);
        return fechaProbableMuerte;
	}

	public double averageAgeClienteProm() {
		double suma = 0.0;
		
		//Obtengo la lista de clientes
		List<Cliente> clientes = clienteRepository.findAll();
		
		//Suma de las edades de todos los elementos de mi lista
		for (Cliente cliente : clientes) {
			suma+=cliente.getEdad();
		}
		
		//Promedio de la suma obtenida
		double prom = (suma/clientes.size());
		return prom;
	}
	
	public double getStandardDeviation(){
		double prom = this.averageAgeClienteProm();
		List<Integer> edades = new ArrayList<>();
		
		double sumaCuadradosDesviaciones = 0.0;
		double varianzaEdades = 0.0;
		List<Cliente> clientes = clienteRepository.findAll();
		
		
		//Resta del promedio a cada elemento de mi muestra
		for (Cliente cliente : clientes) {
			edades.add((int)(cliente.getEdad()-prom));
		}
		
		//Suma de los cuadrados de las desviaciones de los elementos
		for (Integer edad : edades) {
		    sumaCuadradosDesviaciones += Math.pow(edad, 2);
		}
		
		//Desviacion standard dividido en la cantidad de elementos 
		varianzaEdades = sumaCuadradosDesviaciones / clientes.size();
		
		//Retorno de la raiz cuadrada de la desviacion standard
		return Math.sqrt(varianzaEdades);
	}



	
//	public double standardDeviationAgeClienteProm() {
//		return this.getStandardDeviation();
//	}
	
}
