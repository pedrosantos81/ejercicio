package com.example.demo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.dao.ClienteRepository;
import com.model.Cliente;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EjercicioApplicationTests {

	@Mock
	ClienteRepository clienteRep;
	
	@Test
	void contextLoads() {
		
		Optional<Cliente> c = clienteRep.findByIdPersona(1);
		Cliente cli=null;
		
		if(c.isPresent()) {
			cli=c.get();
		}
		System.out.println(cli.getPass());
	}

}
