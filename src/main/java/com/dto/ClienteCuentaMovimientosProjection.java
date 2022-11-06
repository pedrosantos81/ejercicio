package com.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface ClienteCuentaMovimientosProjection {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	Date getFecha();
	String getCliente();
	int getNumerocuenta();
	String getTipo();
	double getSaldoinicial();
	double getMovimiento();
	double getSaldodisponible();
	boolean getEstado();
	

}
