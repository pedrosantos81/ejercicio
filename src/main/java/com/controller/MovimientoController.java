package com.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ClienteCuentaMovimientosProjection;
import com.dto.MovimientoDTO;
import com.excepcion.ManejoCuentaExcepcion;
import com.model.Cliente;
import com.model.Cuenta;
import com.model.Movimientos;
import com.model.TipoTransaccion;
import com.service.ClienteService;
import com.service.CuentaService;
import com.service.MovimientoService;

@CrossOrigin(origins = "*",maxAge = 3600,allowedHeaders = "*")
@RestController
public class MovimientoController {
	
	@Autowired
	CuentaService cuentaService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	MovimientoService movimientoService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("movimientos")
	public List<ClienteCuentaMovimientosProjection> findAllMovimientos(){
		return movimientoService.getMovimientosAll();
	}
	
	@GetMapping("movimientos/{id}/{startDate}/{endDate}")
	public List<ClienteCuentaMovimientosProjection> findAllMovimientos(@PathVariable("id") int id,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){
		return movimientoService.findMovmientoByClienteyFecha(id, startDate, endDate);
	}
	
	@PostMapping("crearmovimiento")
	public ResponseEntity<MovimientoDTO> saveMovimiento(@RequestBody MovimientoDTO movimientoDto){
		
		Cliente cliente = clienteService.findByIdPersona(movimientoDto.getId());
	
		
		Cuenta cuenta = cuentaService.getCuentaByClienteTipoCuenta(cliente.getIdcliente(), movimientoDto.getTipoCuenta());
		
		Movimientos movimientos = null;
		
		
		
		if(movimientoDto.getTipoTransaccion()==TipoTransaccion.ABONO) {
			
			movimientos = new Movimientos(movimientoDto.getTipoTransaccion().toString() +" a cuenta "+movimientoDto.getValor(),movimientoDto.getValor(),cuenta.getSaldoinicial());
			cuenta.addMovimiento(movimientos);
			cuenta.setSaldoinicial(cuenta.getSaldoinicial()+movimientoDto.getValor());
			
			
			
			
		}else if(movimientoDto.getTipoTransaccion()==TipoTransaccion.RETIRO) {
			
			if(cuenta.getSaldoinicial()<movimientoDto.getValor()) {
				throw new ManejoCuentaExcepcion("Fondos insuficientes o la cantidad a retirar excede");
			}else if(cuenta.getSaldoinicial()>=movimientoDto.getValor()) {
				movimientos =new Movimientos(movimientoDto.getTipoTransaccion().toString()+" a cuenta "+movimientoDto.getValor(),movimientoDto.getValor(),cuenta.getSaldoinicial());
			cuenta.addMovimiento(movimientos);	
			cuenta.setSaldoinicial(cuenta.getSaldoinicial()-movimientoDto.getValor());
			
			}
			
		}
		
		Movimientos movpost = movimientoService.save(movimientos);
		
		// convert entity to DTO
		MovimientoDTO postResponse = new MovimientoDTO();
		postResponse.setId(movpost.getCuentas().getClientes().getId());
		postResponse.setTipoCuenta(cuenta.getTipocuenta());
		postResponse.setTipoTransaccion(movimientoDto.getTipoTransaccion());
		postResponse.setValor(movimientoDto.getValor());
		postResponse.setSaldoaldia(cuenta.getSaldoinicial());

			return new ResponseEntity<MovimientoDTO>(postResponse, HttpStatus.CREATED);

	}
	

}
