package com.acme.excecoes;

@SuppressWarnings("serial")
public class ProblemaFisicoException extends RuntimeException {

	public ProblemaFisicoException(String m){
		super(m);
	}
   public ProblemaFisicoException(String m, Throwable e){
	   super(m,e);
   }

}
