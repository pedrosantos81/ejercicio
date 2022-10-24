package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CuentaRepository;
import com.dto.CuentaProjection;
import com.model.Cuenta;

@Service
public class CuentaServiceImpl implements CuentaService{

	CuentaRepository cuentaRep;
	
    @Autowired
	public CuentaServiceImpl(CuentaRepository cuentaRep) {
		super();
		this.cuentaRep = cuentaRep;
	}

	@Override
	public Cuenta save(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return cuentaRep.save(cuenta);
	}

	@Override
	public List<CuentaProjection> findAllClienteCuentas() {
		// TODO Auto-generated method stub
		return cuentaRep.findAllClienteCuentas();
	}

}
