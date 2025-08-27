package br.com.cotiinformatica.handlers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import br.com.cotiinformatica.domain.exceptions.CargoNaoAutorizadoException;
import br.com.cotiinformatica.domain.exceptions.ForaDaAreaDeServicoException;
import br.com.cotiinformatica.domain.exceptions.ServicoEmAbertoException;
import br.com.cotiinformatica.domain.exceptions.ServicoEmAbertoNotFoundException;
import br.com.cotiinformatica.domain.exceptions.ServicoForaDeIntervaloException;
import br.com.cotiinformatica.domain.exceptions.ServicoNaoEncontradoException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ServicoNaoEncontradoException.class)
	public ResponseEntity<Map<String,Object>> handleServicoNaoEncontradoException(ServicoNaoEncontradoException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", "Not Found");
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String,Object>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Bad Request");
		body.put("message", "Parâmetro inválido: " + ex.getName());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Map<String,Object>> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", "Not Found");
		body.put("message", "Recurso não encontrado");
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Bad Request");
		body.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String,Object>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Bad Request");
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ServicoEmAbertoException.class)
	public ResponseEntity<Map<String,Object>> handleServicoEmAbertoException(ServicoEmAbertoException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Bad Request");
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ServicoForaDeIntervaloException.class)
	public ResponseEntity<Map<String,Object>> handleServicoForaDeIntervaloException(ServicoForaDeIntervaloException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Bad Request");
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CargoNaoAutorizadoException.class)
	public ResponseEntity<Map<String,Object>> handleCargoNaoAutorizadoException(CargoNaoAutorizadoException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("error", "Forbidden");
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(ForaDaAreaDeServicoException.class)
	public ResponseEntity<Map<String,Object>> handleForaDaAreaDeServicoException(ForaDaAreaDeServicoException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Bad Request");
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ServicoEmAbertoNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleServicoEmAbertoNotFoundException(ServicoEmAbertoNotFoundException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Bad Request");
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseEntity<Map<String,Object>> handleMissingServletRequestPartException(MissingServletRequestPartException ex, WebRequest request) {
		var body = new HashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Bad Request");
		body.put("message", ex.getMessage());
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
