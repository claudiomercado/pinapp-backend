package com.pinapp.backend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pinapp.backend.app.model.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente, Long> {

}
