package com.service;

import java.util.List;

import com.model.Persona;

public interface PersonaService {
	
	public abstract List<Persona> listaPersonas();
	void deletePersona(long id);
	
	Persona findById(int id);
	
	Persona updatePersona(Persona p);

}
