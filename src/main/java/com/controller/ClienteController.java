package com.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ClienteCuentaProjection;
import com.dto.ClienteProjection;
import com.dto.ClientesDTO;
import com.model.Cliente;
import com.model.Persona;
import com.service.ClienteService;
import com.service.PersonaService;

@CrossOrigin(origins = "*",maxAge = 3600,allowedHeaders = "*")
@RestController
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	PersonaService personaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(value="clientesaf",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ClientesDTO> getClientes(){
		
		List<Cliente> lst = clienteService.getNombreCliente();
		
		for (Cliente p:lst) {
			System.out.println("idpersona: "+p.getId()+",nombre: "+p.getNombre()+",tel: "+p.getTelefono()+",pass: "+p.getPass()+",addres: "+p.getDireccion());
		}
		
		System.out.println(clienteService.getNombreCliente().stream().map(post -> modelMapper.map(post, ClientesDTO.class))
		.collect(Collectors.toList()));
//		
//		clienteService.getNombreCliente().stream().map(post -> modelMapper.map(post, ClientesDTO.class))
//		.collect(Collectors.toList());
		
		return clienteService.getNombreCliente().stream().map(post -> modelMapper.map(post, ClientesDTO.class))
				.collect(Collectors.toList());
	}
	
	
	@PostMapping("crearcliente")
	public ResponseEntity<ClientesDTO> createPost(@RequestBody ClientesDTO clienteDto) {
		// convert DTO to entity
		
		System.out.println("cliente: "+clienteDto.toString());
		Cliente clienteRequest = modelMapper.map(clienteDto, Cliente.class);

		Cliente post = clienteService.createCliente(clienteRequest);

		// convert entity to DTO
		ClientesDTO postResponse = modelMapper.map(post, ClientesDTO.class);

		return new ResponseEntity<ClientesDTO>(postResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(value="clientes",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ClienteProjection> getClientesporcampo(){
		return clienteService.getNombreClienteProjection();
	}
	
	@GetMapping(value="clientescuentas",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> getClientesCuentas(){
		return clienteService.findAll();
	}
	
	@GetMapping(value="clientes/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ClienteCuentaProjection> getClientesporcampo(@PathVariable("id") int id){
		
		List<ClienteCuentaProjection> cliente = clienteService.findCuentasByIdPersona(id);
		
		System.out.println("cliente "+cliente);
		
		return clienteService.findCuentasByIdPersona(id);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletePost(@PathVariable("id") int id) {
         Persona cliente = personaService.findById(id);	
         
		if(cliente==null) {
			return new ResponseEntity<>("Persona"+id+"not found",HttpStatus.NOT_FOUND);
		}else {
			personaService.deletePersona(id);
			return new ResponseEntity<>("Persona y cliente deleted",HttpStatus.OK);
		}
	}
	
	@PutMapping("actualizacliente/{id}")
	public ResponseEntity<ClientesDTO> updateCliente(@PathVariable int id,@RequestBody ClientesDTO clienteDto){
		Cliente cliente = clienteService.findByIdPersona(id);
		
		Persona person = personaService.findById(cliente.getId());
			
		System.out.println("cliente a actualizar: "+cliente.toString());
		
//		if(cliente==null) {
//			return new ResponseEntity<ClientesDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}else {
//			//clienteDto.setIdpersona(cliente.getIdpersona());
			Persona clienteRequest = modelMapper.map(clienteDto, Persona.class);
			System.out.println(clienteRequest.toString());
			System.out.println(person.toString());
//			//clienteService.createPost(clienteRequest);
//			ClientesDTO postResponse = modelMapper.map(clienteService.updateCliente(clienteRequest), ClientesDTO.class);
//			
//			return new ResponseEntity<ClientesDTO>(postResponse,HttpStatus.OK);
//		}
		
		return null;
		
	} 

	

}