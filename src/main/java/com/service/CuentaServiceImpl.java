package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CuentaRepository;
import com.dto.CuentaProjection;
import com.excepcion.ClienteNotFound;
import com.excepcion.ManejoCuentaExcepcion;
import com.model.Cuenta;
import com.model.TipoCuenta;

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

	@Override
	public Cuenta getCuentaByClienteTipoCuenta(int idcliente, TipoCuenta tipocuenta) {
		// TODO Auto-generated method stub
		Cuenta cuenta = null;
		
		Optional<Cuenta> account = cuentaRep.getCuentaByClienteTipoCuenta(idcliente, tipocuenta);
		
		if(account.isPresent()) {
			cuenta = account.get();
		}else {
			throw new ManejoCuentaExcepcion("No se encontro idcliente: "+idcliente+" o no tiene tipo de cuenta: "+tipocuenta);
		}
		
		
		return cuenta;
	}

}
