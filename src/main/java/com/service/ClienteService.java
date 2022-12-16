package com.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dto.ClienteCuentaProjection;
import com.dto.ClienteProjection;
import com.model.Cliente;
import com.model.TipoIdentificacion;


public interface ClienteService {
	
	public abstract List<Cliente> listarTodos();
	//public abstract List<Cliente> getNombreCliente();
	Cliente createCliente(Cliente cliente);
	Cliente updateCliente(Cliente cliente);
	List<ClienteProjection> getNombreClienteProjection();
	Cliente findById(int id);
	Cliente findByIdPersona(int id);
	Cliente findByIdPersonaCliente(int id);
	List<ClienteCuentaProjection> findCuentasByIdPersona(int id);
	List<Cliente> findAll();
	Page<Cliente> findAll(Pageable pageable);
	void actualizaCliente(int id,String nombre,String genero,String identificacion,String telefono,int edad,String pass);
	void delete(int id);
	List<TipoIdentificacion> findAllTipoIdentificacion();
	
	

}
