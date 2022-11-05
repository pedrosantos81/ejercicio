package com.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dto.ClienteCuentaProjection;
import com.dto.ClienteProjection;
import com.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	static String queryStr1="SELECT new Cliente(S.idpersona,p.nombre,p.telefono,S.direccion,S.pass,S.estado)"
			+ "FROM Cliente S join Persona p on S.idpersona=p.id";
			
	static String queryStr3="select p.nombre as nombre,"
			+ "p.telefono as telefono, "
			+ "p.direccion as direccion,"
			+ "S.estado as estado,S.pass as pass "
			+ "from Cliente S join Persona p on S.idpersona=p.id and S.estado=1";
			
	static String queryStr2="SELECT "
			+ "S.idcliente,S.pass,S.estado,S.idPersona,"
			+ "p.direccion,p.nombre,p.telefono,p.edad,p.identificacion,p.genero,p.id "
			+ ""
			+ "FROM Cliente S join Persona p on S.IdPersona=p.id";
	
	static String queryUpdaCliente ="update Cliente c set c.nombre=:nombre"
			+ ", c.genero=:genero"
			+ ", c.identificacion=:identificacion"
			+ ", c.direccion=:direcion"
			+ ", c.telefono=:telefono"
			+ ", c.edad=:edad"
			+ ", c.pass=:pass"
			
			+ " WHERE c.idpersona = :id";
	
	static String queryBajaCliente="update Cliente c set c.estado=0 where c.idpersona=:id";
	
	//@Query(value="select c from Cliente c")
	@Query(queryStr1)
	public List<Cliente> getNombreCliente();
	
	@Query(queryStr3)
	public List<ClienteProjection> getNombreClienteProjection();
	
	@Query("select c from Cliente c where c.idcliente=:idcliente")
	public Optional<Cliente> findById(int idcliente);
	
	@Query("select c from Cliente c where c.idpersona=:id")
	public Optional<Cliente> findByIdPersona(int id);
	
	@Query("select c from Cliente c where c.idpersona=:id")
	public Optional<Cliente> findByIdPersonaCliente(int id);
	
	@Query("select a.numerocuenta as numerocuenta, c.idcliente as idcliente, "
		  + "a.saldoinicial as saldoinicial, a.tipocuenta as tipocuenta, "
		  + "c.persona.nombre as nombre "
	      +" from Cliente c join Cuenta a on c.idcliente=a.idcliente where c.idpersona=:id")
	public Optional<List<ClienteCuentaProjection>> findCuentasByIdPersona(int id);
	
	 // with @EntityGraph
    @EntityGraph(attributePaths = "cuentas")
    @Override
    List<Cliente> findAll(); 
    
    @Modifying
    @Transactional
    @Query(value = queryUpdaCliente)
    void actualizaCliente(@Param("id") int id,@Param("nombre") String nombre,@Param("genero") String genero,@Param("nombre") String identificacion,
    		@Param("telefono") String telefono,
    		@Param("edad") int edad,
    		@Param("pass") String pass);
    
    @Modifying
    @Transactional
    @Query(queryBajaCliente)
    void delete(int id);

	
}