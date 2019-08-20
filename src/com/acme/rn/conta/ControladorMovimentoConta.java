package com.acme.rn.conta;

import com.acme.ado.conta.*;
import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;

public class ControladorMovimentoConta {
	private static MetodosRepositorioMovimentosConta repMovimentoConta = new RepositorioMovimentoConta(); 		

	/**
	 * Método estático inserir(), que recebe um novo Movimento Conta e o adiciona ao seu repositório, com base na Data da operação.
	 * @param novaTransacao
	 * @throws ObjetoInexistenteException 
	 * @throws ObjetoExistenteException 
	 */
	public static void inserir(MovimentoConta novaTransacao) throws ObjetoExistenteException, ObjetoInexistenteException {			
		try{
			novaTransacao.validar();
		}catch(AtributoInvalidoException erro){
			System.out.println(erro.getMessage());
		}
		repMovimentoConta.incluir(novaTransacao.getContaDeOrigem(), novaTransacao, novaTransacao.getDataDaOperacao());  
	}
	
	/**
	 * Métodos de Busca a serem implementados futuramente.
	 * @param id
	 */
	public static void buscarPorConta(IdentificadorConta id)	 {
		//TODO método a ser implementado!
	}
	
	public static void buscarCreditos(IdentificadorConta id){
		//TODO método a ser implementado!
	}
	
	public static void buscarDebitos(IdentificadorConta id){
		//TODO método a ser implementado!
	}
	
	public static void buscarTransferencias(IdentificadorConta id){
		//TODO método a ser implementado!
	}
}
