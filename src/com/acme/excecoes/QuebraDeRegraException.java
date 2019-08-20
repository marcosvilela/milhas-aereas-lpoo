package com.acme.excecoes;

@SuppressWarnings("serial")
public class QuebraDeRegraException extends Exception {

	/**
	 * EXCE��O PARA QUEBRA DE REGRAS DE NEG�CIO. EX: SALDO INSUFICIENTE, CR�DITO NEGATIVO, ETC
	 */
	
	public QuebraDeRegraException(String s){
		super(s);
	}
}
