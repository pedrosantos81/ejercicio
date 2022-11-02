package com.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MovimientoRepository;
import com.dto.ClienteCuentaMovimientosProjection;
import com.model.Movimientos;

@Service
public class MovimientosServiceImpl implements MovimientoService {

	MovimientoRepository movimientoRepository;
	
	ModelMapper modelMapper;
	
	@Autowired
	public MovimientosServiceImpl(MovimientoRepository movimientoRepository,ModelMapper modelMapper) {
       super();
       this.movimientoRepository = movimientoRepository;
       this.modelMapper = modelMapper;
    }
	
	@Override
	public Movimientos save(Movimientos movimiento) {
		// TODO Auto-generated method stub
		return movimientoRepository.save(movimiento);
	}

	@Override
	public List<Movimientos> getMovimientos(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClienteCuentaMovimientosProjection> getMovimientosAll() {
		// TODO Auto-generated method stub
		return movimientoRepository.getMovimientosAll();
	}

	@Override
	public List<ClienteCuentaMovimientosProjection> findMovmientoByClienteyFecha(int id, Date startDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return movimientoRepository.findMovmientoByClienteyFecha(id, startDate, endDate);
	}

	

}
