package com.acme.excecoes;

@SuppressWarnings("serial")
public class ObjetoInexistenteException extends Exception {

	/**
	 * EXCEÇÃO PARA OBJETOS INEXISTENTES EM TODOS OS REPOSITÓRIOS
	 */
	public ObjetoInexistenteException(String s){
		super(s);
	}
	public ObjetoInexistenteException(){
		
	}
}
