package com.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GestionExcepciones extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ClienteNotFound.class)
	public ResponseEntity<ErrorMensaje> manejaError(ClienteNotFound exc){
		ErrorMensaje errormsg = new ErrorMensaje(0,exc.getMessage());
		return new ResponseEntity<ErrorMensaje>(errormsg,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> generalError(Exception exc){
		ErrorMensaje errormsg = new ErrorMensaje(0,exc.getMessage());
		return new ResponseEntity<ErrorMensaje>(errormsg,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
