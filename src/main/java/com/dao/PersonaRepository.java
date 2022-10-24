package com.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer>{
	
	@Transactional
	@Modifying
	@Query("delete from Persona p where p.id=:id")
	void deletePersona(@Param("id") long id);
	
	@Query("select p from Persona p where p.id=:id")
	Optional<Persona> findById(int id);

}
