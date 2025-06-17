package com.Facil.Nota.Projeto.Exceptions;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundException(String warning) {
		super(warning);
	}
}
