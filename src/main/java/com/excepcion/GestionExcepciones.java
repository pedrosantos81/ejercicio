package com.excepcion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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

		String str = ex.getBindingResult().getFieldErrors().stream()
				.map(err -> err.getField() + " " + err.getDefaultMessage()).collect(Collectors.joining("; "));
		// return super.handleMethodArgumentNotValid(ex, headers, status, request);
		return new ResponseEntity<Object>(new ErrorMensaje(HttpStatus.BAD_REQUEST.value(), str, "Error al actualizar la bd"),
				HttpStatus.BAD_REQUEST);
	}

}
