package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.excepcion.ClienteNotFound;
import com.model.Cliente;
import com.model.Persona;
import com.service.PersonaService;

@CrossOrigin(origins = "*",maxAge = 3600,allowedHeaders = "*")
@RestController
public class PersonaController {
	
	@Autowired
	PersonaService service;
	
	@GetMapping(value="personas",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Persona> recuperaPersonas(){
		return service.listaPersonas();
	}
	
	@GetMapping("persona/{id}")
    public ResponseEntity<Persona> getById(@PathVariable int id) {
       Persona user = service.findById(id);
        if (user!=null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            throw new ClienteNotFound("no encontrado");
        }
    }
	
	@PutMapping("actualizarpersona")
	public ResponseEntity<Persona> updatePersona(@RequestBody Persona p){
		
		Persona personaupdating = null;
		personaupdating = service.findById(p.getId());
				
				
		if(personaupdating==null) {
//			throw new RuntimeException("No se encontro id: "+p.getId()+" para actualizar");
			new ResponseEntity<Persona>(new Cliente(),HttpStatus.NOT_FOUND);
		}else {
			personaupdating.setDireccion(p.getDireccion());
			personaupdating.setEdad(p.getEdad());
			personaupdating.setGenero(p.getGenero());
			personaupdating.setNombre(p.getNombre());
			personaupdating.setIdentificacion(p.getIdentificacion());
			personaupdating.setTelefono(p.getTelefono());
			
			service.updatePersona(personaupdating);
		}
		
		return ResponseEntity.ok(personaupdating);
	}
	
}
