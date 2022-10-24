package com.service;

import java.util.List;

import com.dto.ClienteCuentaProjection;
import com.dto.ClienteProjection;
import com.model.Cliente;


public interface ClienteService {
	
	public abstract List<Cliente> listarTodos();
	public abstract List<Cliente> getNombreCliente();
	Cliente createCliente(Cliente cliente);
	Cliente updateCliente(Cliente cliente);
	List<ClienteProjection> getNombreClienteProjection();
	Cliente findById(int id);
	Cliente findByIdPersona(int id);
	List<ClienteCuentaProjection> findCuentasByIdPersona(int id);
	List<Cliente> findAll();
	
	void delete(int id);
	
	

}