package com.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ClienteRepository;
import com.dto.ClienteCuentaProjection;
import com.dto.ClienteProjection;
import com.dto.ClientesDTO;
import com.excepcion.ClienteNotFound;
import com.model.Cliente;
import com.utils.ClienteConverter;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	ClienteRepository clienteRepository;
	
	ModelMapper modelMapper;
	
	@Autowired
	public ClienteServiceImpl(ClienteRepository clienteRepository,ModelMapper modelMapper) {
		super();
		this.clienteRepository = clienteRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<Cliente> listarTodos() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}


	@Override
	public List<Cliente> getNombreCliente() {
		// TODO Auto-generated method stub
		return clienteRepository.getNombreCliente();
	}

	@Override
	public Cliente createCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteRepository.save(cliente);
	}
	
	@Override
	public Cliente updateCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteRepository.save(cliente);
	}

	@Override
	public List<ClienteProjection> getNombreClienteProjection() {
		// TODO Auto-generated method stub
		return clienteRepository.getNombreClienteProjection();
	}


	@Override
	public Cliente findById(int id) {
		// TODO Auto-generated method stub
		Optional<Cliente> result = clienteRepository.findById(id);
		
		Cliente cliente = null;
		if(result.isPresent()) {
			cliente = result.get();
		}else {
			throw new ClienteNotFound("No se encontro cliente: "+id);
		}
		return cliente;
	}


	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}


	@Override
	public Cliente findByIdPersona(int id) {
		// TODO Auto-generated method stub
		Optional<Cliente> result = clienteRepository.findByIdPersona(id);
		
		Cliente cliente = null;
		if(result.isPresent()) {
			cliente = result.get();
		}else {
			throw new ClienteNotFound("No se encontro persona: "+id);
		}
		return cliente;
	}

	@Override
	public List<ClienteCuentaProjection> findCuentasByIdPersona(int id) {
		// TODO Auto-generated method stub
		return clienteRepository.findCuentasByIdPersona(id);
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

}
