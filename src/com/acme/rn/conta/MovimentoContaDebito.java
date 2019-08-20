package com.acme.rn.conta;

import java.io.Serializable;
import java.util.Date;

import com.acme.excecoes.AtributoInvalidoException;

@SuppressWarnings("serial")
public class MovimentoContaDebito extends MovimentoConta implements Serializable{

	/**
	 *  Construtor da Classe, que inicializa seus atributos com os valores passados como par�metro. Usa o construtor da Superclasse para passar seus 
	 *  par�metros.
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
				+ "\nTipo de transa��o: D�BITO" +  
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
