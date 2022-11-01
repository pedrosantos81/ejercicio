package com.service;

import java.util.List;
import java.util.NoSuchElementException;
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
	public Cliente updateCliente(int id,Cliente cliente) {
		// TODO Auto-generated method stub
		
		Cliente post = null;
		try {
			post = clienteRepository.findByIdPersona(id).orElseThrow(()->new ClienteNotFound("Cliente no encontrado "));
					
			
			post.setNombre(cliente.getNombre());
			post.setGenero(cliente.getGenero());
			post.setTelefono(cliente.getTelefono());
			post.setDireccion(cliente.getDireccion());
			post.setEdad(cliente.getEdad());
			post.setEstado(true);
			post.setPass(cliente.getPass());
			post.setIdentificacion(cliente.getIdentificacion());
			
			
		} catch (ClienteNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return clienteRepository.save(post);
		
		//return clienteRepository.saveAndFlush(cliente);
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
			throw new NoSuchElementException("No se encontro el id: "+id+" en la bd");
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

	@Override
	public void actualizaCliente(int id,String nombre,String genero,String identificacion,String telefono,int edad,String pass) {
		// TODO Auto-generated method stub
		clienteRepository.actualizaCliente(id, nombre,genero,identificacion,telefono,edad,pass);
	}

}
