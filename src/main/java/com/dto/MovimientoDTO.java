package com.dto;

import java.util.Date;

import com.model.TipoCuenta;
import com.model.TipoTransaccion;

public class MovimientoDTO {
	
	private int id;
	private TipoCuenta tipoCuenta;
	private TipoTransaccion tipoTransaccion;
	private double valor;
	private double saldoaldia;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getSaldoaldia() {
		return saldoaldia;
	}
	public void setSaldoaldia(double saldoaldia) {
		this.saldoaldia = saldoaldia;
	}

}
