package com.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dto.ClienteCuentaMovimientosProjection;
import com.model.Movimientos;

public interface MovimientoRepository extends JpaRepository<Movimientos, Integer> {
	
	static String queryCuentaMovimientosAllClientes="select "
			+ "m.fecha as fecha, "
			+ "p.nombre as cliente, "
			+ "c.numerocuenta as numerocuenta, "
			+ "c.tipocuenta as tipo, "
			+ "m.saldo as saldoinicial, "
			+ "m.valor as movimiento,  "
			+ "c.saldoinicial as saldodisponible  "
			+ "from Cliente S join Persona p on S.idpersona=p.id "
			+ "join Cuenta c on c.idcliente=S.idcliente "
			+ "join Movimientos m on m.idcuenta=c.numerocuenta";
	
	static String queryCuentaMovimientosByCliente="select "
			+ "m.fecha as fecha, "
			+ "p.nombre as cliente, "
			+ "c.numerocuenta as numerocuenta, "
			+ "c.tipocuenta as tipo, "
			+ "m.saldo as saldoinicial, "
			+ "m.valor as movimiento,  "
			+ "c.saldoinicial as saldodisponible  "
			+ "from Cliente S join Persona p on S.idpersona=p.id "
			+ "join Cuenta c on c.idcliente=S.idcliente "
			+ "join Movimientos m on m.idcuenta=c.numerocuenta "
			+ "where p.id=:id "
			+ "and DATE(m.fecha) between :startDate and :endDate";
	
	@Query(queryCuentaMovimientosAllClientes)
	public List<ClienteCuentaMovimientosProjection> getMovimientosAll();
	
	@Query(queryCuentaMovimientosByCliente)
	public List<ClienteCuentaMovimientosProjection> findMovmientoByClienteyFecha(int id,@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	

}
