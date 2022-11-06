package com.service;

import java.util.Date;
import java.util.List;

import com.dto.ClienteCuentaMovimientosProjection;
import com.model.Movimientos;

public interface MovimientoService {
	
   Movimientos save(Movimientos movimiento);
   List<ClienteCuentaMovimientosProjection> getMovimientosAll();
   List<ClienteCuentaMovimientosProjection> findMovmientoByClienteyFecha(int id,Date startDate,Date endDate);
   

}
