package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dto.CuentaProjection;
import com.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta,Integer>{
	
	static String queryClienteCuenta="select p.nombre as nombre, "
			+ "c.numerocuenta as numerocuenta, "
			+ "c.tipocuenta as tipocuenta, "
			+ "c.saldoinicial as saldoinicial, "
			+ "c.statuscuenta as statuscuenta "
			+ "from Cliente S join Persona p on S.idpersona=p.id "
			+ "join Cuenta c on c.idcliente=S.idcliente";
	
	@Query(queryClienteCuenta)
	public List<CuentaProjection> findAllClienteCuentas();

}
