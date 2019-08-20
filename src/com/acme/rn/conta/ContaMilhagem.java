package com.acme.rn.conta;

import java.io.Serializable;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.classesGerais.Registro;
import com.acme.rn.cliente.Cliente;

@SuppressWarnings("serial")
public class ContaMilhagem extends Registro implements Serializable {

	
	private IdentificadorConta identificador;  
	private Cliente newClient;                                 		
	private int saldo = 0; 
	private boolean ativa = true;

	/**
	 * Construtor da Classe, inicializa os atributos necess�rios � Classe com os respectivos valores passados como par�metros.
	 * @param identificador
	 * @param newClient
	 * @throws AtributoInvalidoException
	 */
	public ContaMilhagem(IdentificadorConta identificador, Cliente newClient) throws AtributoInvalidoException {  
		setIdentificador(identificador);
		setNewClient(newClient);
		setSaldo(saldo);
		
	}

	/**
	 * Getters() e Setters(), m�todos que permitem o uso dos atributos privados sem ferir o encapsulamento, retornando-os, e inicializa 
	 * as vari�veis correspondentes com os valores passados como par�metros, al�m de garantir que esses valores n�o sejam nulos/vazios/il�gicos
	 * nem vazios. 
	 * @return
	 */
	public IdentificadorConta getIdentificador() {  
		return identificador;
	}

	private void setIdentificador(IdentificadorConta identificador) throws AtributoInvalidoException{ 		
		if(identificador != null ){
		this.identificador = identificador;
		}else{
			throw new AtributoInvalidoException("Identificador da Conta inv�lido!");
		}
	}

	public Cliente getNewClient() {   			
		return newClient;
	}

	private void setNewClient(Cliente newClient)throws AtributoInvalidoException {      
		if(newClient != null){
		this.newClient = newClient;
		}else{
			throw new AtributoInvalidoException("Cliente inv�lido!");
		}
	}

	public boolean getAtiva(){    
		return this.ativa;
	}
	
	
	public int getSaldo() {   
		return saldo;
	}

	private void setSaldo(int saldo)throws AtributoInvalidoException {   
		if (saldo >= 0) {            
			this.saldo = saldo; 
		} else {
			throw new AtributoInvalidoException("Saldo inv�lido!");
		}
	}
	
	/**
	 * M�todo creditar() pega um valor inserido como par�metro e incrementa o saldo da Conta com esse valor. Garante que esse valor
	 * seja sempre positivo.
 	 * @param valor
	 * @return int
	 */

	public int creditar(int valor) throws QuebraDeRegraException { 			
		if (valor > 0) {         
			this.saldo += valor;  
		} else {
			throw new QuebraDeRegraException("Imposs�vel creditar! Valor apresentado deve ser maior que R$0. ");
		}
		return saldo;
	}

	/**
	 * M�todo para debitar um valor passado como par�metro no saldo da Conta. Garante que o valor seja positivo e menor que o valor do 
	 * pr�prio saldo.
	 * @param valor
	 */
	
	public void debitar(int valor) throws QuebraDeRegraException{ 

		if (valor >= 0 && valor <= saldo) { // Valida��o de valor n�o negativo e se o valor n�o supera o saldo
											
			this.saldo -= valor;  
		} else {
			throw new QuebraDeRegraException("Imposs�vel debitar! Valor solicitado deve ser maior que R$ 0 e n�o pode ultrapassar o seu saldo!");
		}
	}

	/**
	 * M�todo transferir() transfere um valor passado como par�metro da Conta atual para outra Conta, debitando na Conta de origem e creditando na Conta de Destino
	 * @param valor
	 * @param conta2
	 */
	
	public void transferir(int valor, ContaMilhagem conta2) throws QuebraDeRegraException{     //

		if (valor > 0) { 						//Garante que o valor dado sempre vai ser menor que ou igual ao saldo 
			if(saldo >= valor && conta2 != null){
				this.debitar(valor);
				conta2.creditar(valor);
			}else{
				throw new QuebraDeRegraException("Imposs�vel transferir! Saldo insuficiente");
			}
		} else {
			throw new QuebraDeRegraException("Imposs�vel transferir! Valor solicitado deve ser maior que R$ 0 ");
		}

	}
	
	/**
	 * M�todo desativar() que desativa a Conta, tornando o atributo valor falso 
	 */
	
	public void desativar() {            
		this.ativa = false;
	}

	/**
	 * M�todo reativar() que reativa a Conta, caso ela j� esteja desativada
	 */
	public void reativar() {    
							
		if (ativa == true) {
			System.out.println("A conta j� est� ativada!\n");
		} else
			this.ativa = true;
	}

	/**
	 * M�todo toString() que transforma todos os dados da Classe em uma String e a retorna
	 */
	public String toString() {           

		String aux = " ";      					
		if (this.ativa == true) {         
			aux = "Conta ativa";
		} else {
			aux = "Conta inativa";
		}

		return "\nIdentificador: " + this.getIdentificador().getNumeroDaConta() + "\n--- Cliente ---"
				+ this.getNewClient() + "\nSaldo: " + this.getSaldo() + "\nStatus da conta: " + aux;
	}
	
	public void validar() throws AtributoInvalidoException{
		
	
			if(this.identificador == null){
				throw new AtributoInvalidoException("Identificador Conta inv�lido!");
			
			}else if (this.newClient == null) {
				throw new AtributoInvalidoException("Cliente inv�lido!");
				
			}else if(this.saldo < 0){
				throw new AtributoInvalidoException("Saldo inv�lido!");
		
			}
		}
	

	
	
	/**
	 * Sobrescrita do m�todo equals(), que testa se o objeto passado � uma inst�ncia de ContaMilhagem, e compara o par�metro com o Identificador 
	 * que representa a pr�pria ContaMilhagem da Classe
	 * */
	public boolean equals (Object obj) {
		boolean retorno = false;
		if (obj instanceof ContaMilhagem){
			retorno = this.getIdentificador().equals(((ContaMilhagem) obj).getIdentificador());
		}
		return retorno;
	}
	
	public String getChave(){
		return Long.toString(this.getIdentificador().getNumeroDaConta());
	}
}
