package com.acme.rn.conta;

import java.io.Serializable;
import java.util.Date;

import com.acme.excecoes.AtributoInvalidoException;

@SuppressWarnings("serial")
public class MovimentoContaDebito extends MovimentoConta implements Serializable{

	/**
	 *  Construtor da Classe, que inicializa seus atributos com os valores passados como parâmetro. Usa o construtor da Superclasse para passar seus 
	 *  parâmetros.
	 * @param contaDeOrigem
	 * @param valor
	 * @param nomeExtrato
	 * @param dataDaOperacao
	 */
	public MovimentoContaDebito(ContaMilhagem contaDeOrigem, int valor, String nomeExtrato, Date dataDaOperacao) {
		super(contaDeOrigem, valor, nomeExtrato, dataDaOperacao);
	}
	
	public String toString(){
		return "\nConta de Origem: " + super.getContaDeOrigem().getNewClient().getPrimeiroNome() + "\nValor: " + super.getValor()
				+ "\nTipo de transação: DÉBITO" +  
				"\nData Da Operacao: " + super.getDataDaOperacao()
				+ "\nNome no Extrato: " + super.getNomeExtrato();
	}
	
	public void validar() throws AtributoInvalidoException{
		
		if(super.getContaDeOrigem() == null){
			throw new AtributoInvalidoException("Conta Milhagem de origem inválida!");
			
		}else if (super.getValor() <= 0) {
			throw new AtributoInvalidoException("Valor da transação inválido!");
			
		}else if(super.getNomeExtrato().length() > 100){
			throw new AtributoInvalidoException("Nome da fonte inválido!");
		
		}else if(super.getDataDaOperacao() == null){
			throw new AtributoInvalidoException("Data da operação inválida!");
		}
	}
}
