package com.acme.excecoes;

@SuppressWarnings("serial")
public class AtributoInvalidoException extends Exception {

    /**
     * EXCEÇÃO PARA ATRIBUTOS INVÁLIDOS! EX: CLIENTE NULO, CONTA NULA
     * @param s
     */

	public AtributoInvalidoException(String s) {
		super(s);
		
	}
}
