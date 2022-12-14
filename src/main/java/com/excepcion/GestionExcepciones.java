package com.excepcion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GestionExcepciones extends ResponseEntityExceptionHandler {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(GestionExcepciones.class);

	@ExceptionHandler(ClienteNotFound.class)
	public ResponseEntity<ErrorMensaje> manejaError(ClienteNotFound exc) {
		ErrorMensaje errormsg = new ErrorMensaje(HttpStatus.NOT_FOUND.value(), exc.getMessage(), "No encontrado");
		return new ResponseEntity<ErrorMensaje>(errormsg, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ManejoCuentaExcepcion.class)
	public ResponseEntity<ErrorMensaje> manejaError(ManejoCuentaExcepcion exc) {
		ErrorMensaje errormsg = new ErrorMensaje(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), "Mala peticion");
		return new ResponseEntity<ErrorMensaje>(errormsg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorMensaje> manejaError(NoSuchElementException exc) {
		ErrorMensaje errormsg = new ErrorMensaje(HttpStatus.NOT_FOUND.value(), exc.getMessage(),
				"No encontrado en la bd");
		return new ResponseEntity<ErrorMensaje>(errormsg, HttpStatus.NOT_FOUND);
	}

//	/*
//	 * Si jala esta exception
//	 */
//	@ExceptionHandler({ ConstraintViolationException.class })
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	public Map<String, Object> handleValidationError(ConstraintViolationException exception) {
//		LOGGER.warn("ConstraintViolationException thrown", exception);
//		Map<String, Object> response = new HashMap<>();
//		List<Map<String, String>> errors = new ArrayList<>();
//
//		for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
//			Map<String, String> transformedError = new HashMap<>();
//
//			String fieldName = violation.getPropertyPath().toString();
//			transformedError.put("field", fieldName.substring(fieldName.lastIndexOf('.') + 1));
//			transformedError.put("error", violation.getMessage());
//
//			errors.add(transformedError);
//		}
//		response.put("errors", errors);
//
//		return response;
//	}
	
	/*
	 * Si jala esta exception
	 */
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleValidationError2(ConstraintViolationException exception) {
		LOGGER.warn("ConstraintViolationException thrown", exception);
//		Map<String, Object> response = new HashMap<>();
//		List<Map<String, String>> errors = new ArrayList<>();
//
//		for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
//			Map<String, String> transformedError = new HashMap<>();
//
//			String fieldName = violation.getPropertyPath().toString();
//			transformedError.put("field", fieldName.substring(fieldName.lastIndexOf('.') + 1));
//			transformedError.put("error", violation.getMessage());
//
//			errors.add(transformedError);
//			
//			
//		}
//		response.put("errors", errors);
		
		String str = exception.getConstraintViolations().stream().map(err->err.getPropertyPath().toString().substring(err.getPropertyPath().toString().lastIndexOf('.')+1) +" "+err.getMessage()).collect(Collectors.joining(";"));

		return new ResponseEntity<Object>(new ErrorMensaje(HttpStatus.BAD_REQUEST.value(),str,"Error al insertar en la bd"),HttpStatus.BAD_REQUEST);
	}

	/*
	 * Si jala esta exception
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub

		Map<String, Object> response = new HashMap<>();
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> "El campo '"+ err.getField() + " " + err.getDefaultMessage()).collect(Collectors.toList());
		
		String typeRequest="";
//		Map<String, String> transformedError = null;
//		List<Map<String, String>> errors = new ArrayList<>();
//		
//		for (FieldError result: ex.getBindingResult().getFieldErrors()) {
//			LOGGER.info(result.getField()+"  "+result.getDefaultMessage());
//			transformedError = new HashMap<>();
//			transformedError.put("errors", result.getField()+"  "+result.getDefaultMessage());
//			errors.add(transformedError);
//		}
		LOGGER.info(((ServletWebRequest) request).getHttpMethod().toString());
		if(((ServletWebRequest) request).getHttpMethod().toString().equals("POST")) {
			typeRequest = "Error al realizar el insert en la base de datos";
		}else if(((ServletWebRequest) request).getHttpMethod().toString().equals("PUT")) {
			typeRequest = "Error al actualizar en la base de datos";
		}
		
		response.put("errors", errors);
		
		return new ResponseEntity<Object>(new ErrorMensaje(HttpStatus.BAD_REQUEST.value(), response, typeRequest),
				HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		//return super.handleHttpMessageNotReadable(ex, headers, status, request);
		Map<String, Object> response = new HashMap<>();
		
		ErrorMensaje errormsg = new ErrorMensaje(HttpStatus.NOT_ACCEPTABLE.value(),ex.getHttpInputMessage().toString(),ex.getLocalizedMessage());
		
		return new ResponseEntity<Object>(errormsg,HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	
	

}
