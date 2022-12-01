package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ClienteProjection;
import com.dto.ClientesDTO;
import com.model.Cliente;
import com.service.ClienteService;
import com.service.PersonaService;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@Validated
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	PersonaService personaService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "clientesaf", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClientesDTO> getClientes() {

		List<Cliente> lst = clienteService.getNombreCliente();

		for (Cliente p : lst) {
			System.out.println("idpersona: " + p.getId() + ",nombre: " + p.getNombre() + ",tel: " + p.getTelefono()
					+ ",pass: " + p.getPass() + ",addres: " + p.getDireccion());
		}

		System.out.println(clienteService.getNombreCliente().stream()
				.map(post -> modelMapper.map(post, ClientesDTO.class)).collect(Collectors.toList()));

		return clienteService.getNombreCliente().stream().map(post -> modelMapper.map(post, ClientesDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> findAll(@PathVariable Integer page){
		//es lo mismo
//		Pageable paging = PageRequest.of(page, 5);
//		return clienteService.findAll(paging);
		return clienteService.findAll(PageRequest.of(page, 7));
	}

//	@PostMapping("clientes")
//	public ResponseEntity<ClientesDTO> createPost(@RequestBody ClientesDTO clienteDto) {
//		// convert DTO to entity
//
//		Cliente clienteRequest = modelMapper.map(clienteDto, Cliente.class);
//		clienteRequest.setEstado(true);
//
//		Cliente post = clienteService.createCliente(clienteRequest);
//
//		// convert entity to DTO
//		ClientesDTO postResponse = modelMapper.map(post, ClientesDTO.class);
//
//		return new ResponseEntity<ClientesDTO>(postResponse, HttpStatus.CREATED);
//	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente){
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			
			clienteNew = clienteService.createCliente(cliente);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		response.put("mensaje", "El cliente ha sido creado con éxito!");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping(value = "clientes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClienteProjection> getClientesporcampo() {
		return clienteService.getNombreClienteProjection();
	}

	@GetMapping(value = "clientescuentas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> getClientesCuentas() {
		return clienteService.findAll();
	}

	@GetMapping(value = "clientes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente getClienteandCuentas(@PathVariable int id) {
		return clienteService.findByIdPersonaCliente(id);
	}

//	@DeleteMapping("clientes/{id}")
//	public ResponseEntity<Object> deletePost(@PathVariable("id") int id) {
//
//		Cliente cliente = clienteService.findByIdPersona(id);
//
//		if (cliente == null) {
//			return new ResponseEntity<>("Cliente" + id + "not found", HttpStatus.NOT_FOUND);
//		} else {
//			clienteService.delete(id);
//			return new ResponseEntity<>("Cliente dado de baja", HttpStatus.NO_CONTENT);
//		}
//	}
	
	@DeleteMapping("clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePost(@PathVariable("id") int id) {

		Cliente cliente = clienteService.findByIdPersona(id);

		clienteService.delete(cliente.getId());
	}

	@PutMapping("/clientes/{id}")
	//public ResponseEntity<?> updateCliente(@PathVariable int id, @RequestBody ClientesDTO clienteDto) {
	public ResponseEntity<?> updateCliente(@PathVariable int id,@Valid @RequestBody Cliente cliente) {
		Cliente clienteActual = clienteService.findByIdPersona(id);
		Cliente clientepost = null;
		Map<String,Object> response = new HashMap<>();
		
		if(clienteActual==null)
		{
			response.put("mensaje", "Error: No se pudo editar el cliente ID:".concat(String.valueOf(id).concat(" no existe en la bd.")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
//
//		clienteDto.setId(id);
//
//		// convert DTO to Entity
//		Cliente clienteRequest = modelMapper.map(clienteDto, Cliente.class);
//
//		//clientepost = clienteService.updateCliente(id, clienteRequest);
//
//		// entity to DTO
//		ClientesDTO postResponse = modelMapper.map(clienteRequest, ClientesDTO.class);
		
		try {
			//clientepost = clienteService.updateCliente(id, cliente);
			
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setGenero(cliente.getGenero());
			clienteActual.setTelefono(cliente.getTelefono());
			clienteActual.setDireccion(cliente.getDireccion());
			clienteActual.setEdad(cliente.getEdad());
			clienteActual.setEstado(true);
			clienteActual.setPass(cliente.getPass());
			clienteActual.setIdentificacion(cliente.getIdentificacion());
			clientepost = clienteService.updateCliente(clienteActual);
			
		}catch(DataAccessException dexc) {
			response.put("message", dexc.getMessage().concat(": ").concat(dexc.getMostSpecificCause().getMessage()));
			response.put("error","No se pudo actualizar la bd");
			new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El cliente se actualizo con exito");
		response.put("cliente", clientepost);

		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

	}

}
