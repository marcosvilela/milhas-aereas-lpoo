package com.acme.excecoes;

@SuppressWarnings("serial")
public class ObjetoInexistenteException extends Exception {

	/**
	 * EXCE��O PARA OBJETOS INEXISTENTES EM TODOS OS REPOSIT�RIOS
	 */
	public ObjetoInexistenteException(String s){
		super(s);
	}
	public ObjetoInexistenteException(){
		
	}
}
