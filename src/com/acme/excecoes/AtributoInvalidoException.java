package com.acme.excecoes;

@SuppressWarnings("serial")
public class AtributoInvalidoException extends Exception {

    /**
     * EXCE��O PARA ATRIBUTOS INV�LIDOS! EX: CLIENTE NULO, CONTA NULA
     * @param s
     */

	public AtributoInvalidoException(String s) {
		super(s);
		
	}
}
