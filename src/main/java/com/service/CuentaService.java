package com.service;

import java.util.List;

import com.dto.CuentaProjection;
import com.model.Cuenta;

public interface CuentaService {
	
	Cuenta save(Cuenta cuenta);
	List<CuentaProjection> findAllClienteCuentas();

}
