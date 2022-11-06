package com.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CuentaDTO;
import com.dto.CuentaProjection;
import com.model.Cliente;
import com.model.Cuenta;
import com.model.TipoCuenta;
import com.service.ClienteService;
import com.service.CuentaService;

@CrossOrigin(origins = "*",maxAge = 3600,allowedHeaders = "*")
@RestController
public class CuentaController {
	
	@Autowired
	CuentaService cuentaService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("cuentas")
	public List<CuentaProjection> findAllCuentas(){
		return cuentaService.findAllClienteCuentas();
	}
	
	@PostMapping("cuentas")
	public ResponseEntity<CuentaDTO> createCuenta(@RequestBody CuentaDTO cuentaDto) {
		
		Cuenta cuentaRequest,post;
		
		Cliente clienteobj = clienteService.findById(cuentaDto.getIdcliente());
		
		System.out.println(clienteobj);
		
		if(clienteobj==null) {
			return new ResponseEntity<CuentaDTO>(HttpStatus.NOT_FOUND);
		}else {
			
			Cliente cl = new Cliente();
			cuentaDto.getTipocuenta();
			cuentaRequest = new Cuenta(TipoCuenta.valueOf(cuentaDto.getTipocuenta()),cuentaDto.getSaldoinicial(),cuentaDto.isStatus());
			cuentaRequest.setCliente(clienteobj);
			
			post = cuentaService.save(cuentaRequest);
			
			CuentaDTO cuentaResponse = new CuentaDTO();
			
			cuentaResponse.setTipocuenta(post.getTipocuenta().toString());
			cuentaResponse.setIdcliente(post.getClientes().getIdcliente());
			cuentaResponse.setSaldoinicial(post.getSaldoinicial());
			cuentaResponse.setStatus(post.isStatuscuenta());
			
			return new ResponseEntity<CuentaDTO>(cuentaResponse,HttpStatus.OK);
		}
		
	}
	
	
	
	

}
