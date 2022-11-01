package com.excepcion;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GestionExcepciones extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ClienteNotFound.class)
	public ResponseEntity<ErrorMensaje> manejaError(ClienteNotFound exc){
		ErrorMensaje errormsg = new ErrorMensaje(HttpStatus.NOT_FOUND.value(),exc.getMessage());
		return new ResponseEntity<ErrorMensaje>(errormsg,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ManejoCuentaExcepcion.class)
	public ResponseEntity<ErrorMensaje> manejaError(ManejoCuentaExcepcion exc){
		ErrorMensaje errormsg = new ErrorMensaje(HttpStatus.BAD_REQUEST.value(),exc.getMessage());
		return new ResponseEntity<ErrorMensaje>(errormsg,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorMensaje> manejaError(NoSuchElementException exc){
		ErrorMensaje errormsg = new ErrorMensaje(HttpStatus.NOT_FOUND.value(),exc.getMessage());
		return new ResponseEntity<ErrorMensaje>(errormsg,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> generalError(Exception exc){
		ErrorMensaje errormsg = new ErrorMensaje(2,exc.getMessage());
		return new ResponseEntity<ErrorMensaje>(errormsg,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
