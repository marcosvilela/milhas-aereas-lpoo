package com.acme.excecoes;

@SuppressWarnings("serial")
public class ObjetoExistenteException extends Exception {

	/**
	 * EXCE��O PARA OBJETOS J� EXISTENTES EM TODOS OS REPOSIT�RIOS
	 */
	public ObjetoExistenteException(String s) {
		super(s);
	}
	public ObjetoExistenteException(){
		
	}
	
}

