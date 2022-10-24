package com.example.demo;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dao.ClienteRepository;
import com.dao.CuentaRepository;
import com.model.Cliente;
import com.model.Cuenta;
import com.model.Status;
import com.model.TipoCuenta;

@SpringBootApplication
@ComponentScan(basePackages= {"com.controller","com.dao","com.model","com.service","com.dto","com.excepcion"})
@EntityScan(basePackages= {"com.model"})
@EnableJpaRepositories(basePackages= {"com.dao"})
public class EjercicioApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(EjercicioApplication.class, args);
		
//		ConfigurableApplicationContext configurableApp = SpringApplication.run(EjercicioApplication.class, args);
		
		//PersonaRepository personRep = configurableApp.getBean(PersonaRepository.class);
		
//		CuentaRepository cuentaRep = configurableApp.getBean(CuentaRepository.class);
//		ClienteRepository clienteRep = configurableApp.getBean(ClienteRepository.class);
		
//		Persona p = new Persona("Maria Garces","F",43,"ife","San Isidro 33 col roble","323232");
		
		
//		personRep.save(p);
//		personRep.flush();
//		System.out.println("p.id: "+p.getId());
//		Optional<Persona> temp =personRep.findById(p.getId());
//		System.out.println("idpersona: "+temp.get().getId());
//		Cliente c = new Cliente("Daniel Fonseca","M",33,"ife","Calle Frutos Rev 233 Col Roble","4555","2134");
//		clienteRep.save(c);
//		clienteRep.flush();
		
//		ModelMapper modelMapper = new ModelMapper();
//		
//		ClientesDTO c = new ClientesDTO("juan","M",33,"ife","prueba","4555","2134");
//		
//		System.out.println(c.toString());
//		Cliente cl= new Cliente();
//		
//		cl =modelMapper.map(c,Cliente.class);
//		System.out.println(modelMapper.map(c, Cliente.class).getPass());
		
		//clienteRep.saveAndFlush(cl);
		
//		ClienteService servicio = configurableApp.getBean(ClienteService.class);
//		servicio.createCliente(cl);
		
		
		
		
//		Optional<Cliente> clienteobj = clienteRep.findById(519);
//		
//		Cliente cl = null;
//		
//		if(clienteobj.isPresent()) {
//			System.out.println("existe");
//			cl = new Cliente();
//			cl = clienteobj.get();
//			Cuenta cuentaprueba = new Cuenta(TipoCuenta.CORRIENTE,3000,true);
//			cuentaprueba.setIdCliente(cl);
//			System.out.println(cuentaprueba.toString());
//			cuentaRep.save(cuentaprueba);
//			
//			
//		}else {
//			System.out.println("no existe");
//		}
		
		
		
	}

}
