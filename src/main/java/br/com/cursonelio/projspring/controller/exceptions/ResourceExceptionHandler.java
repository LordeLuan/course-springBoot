package br.com.cursonelio.projspring.controller.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cursonelio.projspring.services.exceptions.DatabaseException;
import br.com.cursonelio.projspring.services.exceptions.ResourceNotFoundExecption;

// instercepta as exceções para chamar o metodo da classe
@ControllerAdvice
public class ResourceExceptionHandler {

//	Anotação para que o metodo seja capaz de intercep esse tipo de exceção especifica e faça o devido tratamento
	@ExceptionHandler(ResourceNotFoundExecption.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundExecption e, HttpServletRequest request){
//		Mensagem de erro da exceção
		String error = "Resource not found";
//		Status http para a resposta da requisição - 404, 400, 500 e etc
		HttpStatus status = HttpStatus.NOT_FOUND;
//		Instancia a classe que criamos para ser um formato padrão de resposta para as exceções capturadas
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databse(ResourceNotFoundExecption e, HttpServletRequest request){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
//		Retorno do metodo é o status da requisição e a mensagem de erro
		return ResponseEntity.status(status).body(err);
	}
	
	
	
	
}
