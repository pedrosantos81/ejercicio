package com.service;

import java.util.List;

import com.model.Movimientos;

public interface MovimientoService {
	
   Movimientos save(Movimientos movimiento);
   List<Movimientos> getMovimientos(int id);
   

}
