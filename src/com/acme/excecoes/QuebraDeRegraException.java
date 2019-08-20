package com.acme.excecoes;

@SuppressWarnings("serial")
public class QuebraDeRegraException extends Exception {

	/**
	 * EXCEÇÃO PARA QUEBRA DE REGRAS DE NEGÓCIO. EX: SALDO INSUFICIENTE, CRÉDITO NEGATIVO, ETC
	 */
	
	public QuebraDeRegraException(String s){
		super(s);
	}
}
