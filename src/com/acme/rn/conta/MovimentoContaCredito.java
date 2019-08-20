package com.acme.rn.conta;

import java.io.Serializable;
import java.util.Date;

import com.acme.excecoes.AtributoInvalidoException;

@SuppressWarnings("serial")
public class MovimentoContaCredito extends MovimentoConta implements Serializable{

/**
 * Construtor da Classe MovimentoContaCredito(), classe-filha de MovimentoConta, que usa o Construtor da superclasse, passando todos os seus par�metro.	
 * @param contaDeOrigem
 * @param valor
 * @param nomeExtrato
 * @param dataDaOperacao
 */
	public MovimentoContaCredito(ContaMilhagem contaDeOrigem, int valor, String nomeExtrato, Date dataDaOperacao) {
		super(contaDeOrigem, valor, nomeExtrato, dataDaOperacao);
	}
	
	public String toString(){
		return "\nConta de Origem: " + super.getContaDeOrigem().getNewClient().getPrimeiroNome() + "\nValor: " + super.getValor()
				+ "\nTipo de transa��o: CR�DITO" +  
				"\nData Da Operacao: " + super.getDataDaOperacao()
				+ "\nNome no Extrato: " + super.getNomeExtrato();
	}

	public void validar() throws AtributoInvalidoException{
		
			if(super.getContaDeOrigem() == null){
				throw new AtributoInvalidoException("Conta Milhagem de origem inv�lida!");
				
			}else if (super.getValor() <= 0) {
				throw new AtributoInvalidoException("Valor da transa��o inv�lido!");
				
			}else if(super.getNomeExtrato().length() > 100){
				throw new AtributoInvalidoException("Nome da fonte inv�lido!");
			
			}else if(super.getDataDaOperacao() == null){
				throw new AtributoInvalidoException("Data da opera��o inv�lida!");
			}
		}

	}
