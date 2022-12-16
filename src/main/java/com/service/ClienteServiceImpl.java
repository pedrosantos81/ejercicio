package com.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dao.ClienteRepository;
import com.dto.ClienteCuentaProjection;
import com.dto.ClienteProjection;
import com.excepcion.ClienteNotFound;
import com.model.Cliente;
import com.model.TipoIdentificacion;

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
		return clienteRepository.findAll();
	}


//	@Override
//	public List<Cliente> getNombreCliente() {
//		return clienteRepository.getNombreCliente();
//	}

	@Override
	public Cliente createCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@Override
	public Cliente updateCliente(Cliente cliente) {
		
//		Cliente post = null;
//		try {
//			post = clienteRepository.findByIdPersona(id).orElseThrow(()->new ClienteNotFound("Cliente no encontrado "));
//					
//			post.setNombre(cliente.getNombre());
//			post.setGenero(cliente.getGenero());
//			post.setTelefono(cliente.getTelefono());
//			post.setDireccion(cliente.getDireccion());
//			post.setEdad(cliente.getEdad());
//			post.setEstado(true);
//			post.setPass(cliente.getPass());
//			post.setIdentificacion(cliente.getIdentificacion());
//			
//			
//		} catch (ClienteNotFound e) {
//			// TODO Auto-generated catch block
//		    e.printStackTrace();
//		}
		return clienteRepository.save(cliente);
	}

	@Override
	public List<ClienteProjection> getNombreClienteProjection() {
		return clienteRepository.getNombreClienteProjection();
	}


	@Override
	public Cliente findById(int id) {
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
		clienteRepository.delete(id);
	}


	@Override
	public Cliente findByIdPersona(int id) {
		Optional<Cliente> result = clienteRepository.findByIdPersona(id);
		
		Cliente cliente = null;
		if(result.isPresent()) {
			cliente = result.get();
		}else {
			throw new NoSuchElementException("No se encontro el cliente: "+id+" para dar de baja en la bd");
		}
		return cliente;
	}

	@Override
	public List<ClienteCuentaProjection> findCuentasByIdPersona(int id) {

		Optional<List<ClienteCuentaProjection>> result=clienteRepository.findCuentasByIdPersona(id);
		
		List<ClienteCuentaProjection> lista = null;
		if(result.get().size()<1) {
			throw new ClienteNotFound("No se encontro el cliente :"+id);
		}else {
			lista = result.get();
		}
		return lista;
	}

	@Override
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	public void actualizaCliente(int id,String nombre,String genero,String identificacion,String telefono,int edad,String pass) {
		//clienteRepository.actualizaCliente(id, nombre,genero,identificacion,telefono,edad,pass);
	}

	@Override
	public Cliente findByIdPersonaCliente(int id) {
		Optional<Cliente> result = clienteRepository.findByIdPersonaCliente(id);
		
		Cliente cliente=null;
		if(result.isPresent()) {
			cliente = result.get();
		}else {
			throw new ClienteNotFound("No se hallo cliente "+id);
		}
		return cliente;
	}

	@Override
	public Page<Cliente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clienteRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public List<TipoIdentificacion> findAllTipoIdentificacion() {
		return clienteRepository.findAllTipoIdentificacion();
	}

}

