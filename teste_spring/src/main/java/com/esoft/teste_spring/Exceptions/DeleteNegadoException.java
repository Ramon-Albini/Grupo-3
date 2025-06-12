package com.esoft.teste_spring.Exceptions;

public class DeleteNegadoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public DeleteNegadoException(String mensagem) {
        super(mensagem);
    }
}
