package com.acme.rn.conta;

import java.io.Serializable;
import java.util.Date;

import com.acme.excecoes.AtributoInvalidoException;

@SuppressWarnings("serial")
public class MovimentoContaTransferencia extends MovimentoConta implements Serializable{
	
	/**
	 * Variável do tipo ContaMilhagem que representa a Conta de destino de uma movimentação.
	 */
	private ContaMilhagem contaDestino;
	
	/**
	 * Construtor da Classe, inicializa seus atributos com os valores passados como parâmetro. Usa o construtor da Superclasse e o setter da Conta de Destino
	 * @param contaDeOrigem
	 * @param contaDestino
	 * @param valor
	 * @param nomeExtrato
	 * @param dataDaOperacao
	 */
	public MovimentoContaTransferencia(ContaMilhagem contaDeOrigem, ContaMilhagem contaDestino, int valor, String nomeExtrato, Date dataDaOperacao) {
		super(contaDeOrigem, valor, nomeExtrato, dataDaOperacao);
		setContaDestino(contaDestino);
	}
	
	/**
	 * Getters() e Setters() do atributo Conta de Destino, permitem o acesso e inicializam seu valor. 
	 * @param contaDestino
	 */
	public void setContaDestino(ContaMilhagem contaDestino){
		if(contaDestino != null){
			this.contaDestino = contaDestino;
		}else{
			System.out.println("A conta passada é nula!");
		}
	}
	
	public ContaMilhagem getContaDestino(){
		return contaDestino;
	}

/**
 * 	
 */
	public void validar() throws AtributoInvalidoException{
		
		
			if(super.getContaDeOrigem() == null){
				throw new AtributoInvalidoException("Conta Milhagem de origem inválida!");
			
			}else if (super.getValor() <= 0) {
				throw new AtributoInvalidoException("Valor da transação inválido!");
				
			}else if(super.getNomeExtrato().length() > 100){
				throw new AtributoInvalidoException("Nome da fonte inválido!");
				
			}else if(super.getDataDaOperacao() == null){
				throw new AtributoInvalidoException("Data da operação inválida!");
				
			}else if(this.contaDestino == null){
			     throw new AtributoInvalidoException("Conta Milhagem de destino inválida!");
			 
			}
		}
	

	public String toString(){
		return "\nConta de Origem: " + super.getContaDeOrigem().getNewClient().getPrimeiroNome() + "\nValor: " + super.getValor()
				+ "\nConta de Destino: " + this.getContaDestino().getNewClient().getPrimeiroNome() + "\nTipo de transação: TRANSFERÊNCIA" +  
				"\nData Da Operacao: " + super.getDataDaOperacao()
				+ "\nNome no Extrato: " + super.getNomeExtrato();
	}
}
