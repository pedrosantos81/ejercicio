package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.dao.PersonaRepository;
import com.model.Persona;

@Service
public class PersonaServiceImpl implements PersonaService{
	
	PersonaRepository personaRepository;

	@Autowired
	public PersonaServiceImpl(PersonaRepository personaRepository) {
		this.personaRepository = personaRepository;
	}

	@Override
	public List<Persona> listaPersonas() {
		return personaRepository.findAll();
	}


	@Override
	public Persona findById(int id) {
		Optional<Persona> result = personaRepository.findById(id);
		
		Persona persona=null;
		if(result.isPresent()) {
			persona = result.get();
		}else {
			throw new RuntimeException("No encontrada persona con Id: "+id);
		}
		
		return persona;
	}

	@Override
	public void deletePersona(long id) {
		personaRepository.deletePersona(id);
	}

	@Override
	public Persona updatePersona(Persona p) {
		return personaRepository.save(p);
	}

}
