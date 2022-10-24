package com.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.dto.ClientesDTO;
import com.model.Cliente;

@Component
public class ClienteConverter {
	
	public ClientesDTO convertDTO(Cliente cliente) {
		ModelMapper modelMapper = new ModelMapper();
		ClientesDTO clientesDTO = modelMapper.map(cliente, ClientesDTO.class);
		return clientesDTO;
	}
	
	public Cliente convertDtoToEntity(ClientesDTO clientesdto) {
		ModelMapper modelMapper = new ModelMapper();
		Cliente cliente = modelMapper.map(clientesdto, Cliente.class);
		return cliente;
		
	}
	
//	public ClientesDTO convertdtotoentity(Cliente cliente) {
//		ClientesDTO clientedto = new ClientesDTO();
//		clientedto.setId(cliente.getId());
//		clientedto.setDireccion(cliente.getDireccion());
//		clientedto.setContrasena(cliente.getPass());
//		return clientedto;
//		
//	}

}
