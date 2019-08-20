package com.acme.excecoes;

@SuppressWarnings("serial")
public class ObjetoExistenteException extends Exception {

	/**
	 * EXCEÇÃO PARA OBJETOS JÁ EXISTENTES EM TODOS OS REPOSITÓRIOS
	 */
	public ObjetoExistenteException(String s) {
		super(s);
	}
	public ObjetoExistenteException(){
		
	}
	
}

