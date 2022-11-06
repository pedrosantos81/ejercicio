package com.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MovimientoRepository;
import com.dto.ClienteCuentaMovimientosProjection;
import com.excepcion.ManejoCuentaExcepcion;
import com.model.Movimientos;

@Service
public class MovimientosServiceImpl implements MovimientoService {

	MovimientoRepository movimientoRepository;

	ModelMapper modelMapper;

	@Autowired
	public MovimientosServiceImpl(MovimientoRepository movimientoRepository, ModelMapper modelMapper) {
		this.movimientoRepository = movimientoRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Movimientos save(Movimientos movimiento) {
		return movimientoRepository.save(movimiento);
	}

	@Override
	public List<ClienteCuentaMovimientosProjection> getMovimientosAll() {
		return movimientoRepository.getMovimientosAll();
	}

	@Override
	public List<ClienteCuentaMovimientosProjection> findMovmientoByClienteyFecha(int id, Date startDate, Date endDate) {

		List<ClienteCuentaMovimientosProjection> lstclienteMovimientos = movimientoRepository
				.findMovmientoByClienteyFecha(id, startDate, endDate);

		if (lstclienteMovimientos.isEmpty()) {
			throw new ManejoCuentaExcepcion("No tiene movimientos la cuenta");
		}

		return lstclienteMovimientos;
	}

}
