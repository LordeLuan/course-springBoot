package br.com.cursonelio.projspring.services.exceptions;

public class ResourceNotFoundExecption extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundExecption(Object id) {
//		Construtor da super classe
		super("Resource not found. ID " + id);
	}
}
