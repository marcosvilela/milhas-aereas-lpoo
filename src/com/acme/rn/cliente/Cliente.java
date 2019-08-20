package com.acme.rn.cliente;

import java.io.Serializable;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.rn.classesGerais.Registro;

@SuppressWarnings("serial")
public class Cliente extends Registro implements Serializable{

	/**
	 * Vari�veis necess�rias para a classe, incluindo duas vari�veis constantes para definir o sexo como masculino ou feminino, imposs�vel 
	 * de ser mudado.
	 */
	private static final int MASCULINO = 1; 
	private static final int FEMININO = 2;
	private String nome; 
	private CPF cpf;
	private int idade;
	private double renda;
	private int sexo;

	/**
	 * Construtor da classe, inicializa as vari�veis da classe.
	 * @param nome
	 * @param cpf
	 * @param idade
	 * @param renda
	 * @param sexo
	 * @throws AtributoInvalidoException
	 */
	public Cliente(String nome, CPF cpf, int idade, double renda, int sexo) throws AtributoInvalidoException {										
		this.setNome(nome);
		this.setCpf(cpf);
		this.setIdade(idade);
		this.setRenda(renda);
		this.setSexo(sexo);

	}
	
	/**
	 * Getters() e Setters(), m�todos que permitem o uso dos atributos privados sem ferir o encapsulamento, retornando-os, e inicializa 
	 * as vari�veis correspondentes com os valores passados como par�metros, al�m de garantir que esses valores n�o sejam nulos
	 * nem vazios. 
	 * @return
	 */

	public String getNome() {            
								
		return nome;
	}

	public CPF getCpf() {                 
		return cpf;
	}

	public int getIdade() {                
		return idade;
	}

	public double getRenda() {              
		return renda;
	}

	public int getSexo() {               
		return sexo;
	}

	private void setSexo(int sexo) throws AtributoInvalidoException {     
		if (sexo >= 1 && sexo <= 2) {         //Garante que o sexo assuma apenas os valores de 1 e 2
			this.sexo = sexo;
		} else { 
			throw new AtributoInvalidoException("Atributo Sexo inv�lido!");
		}

	}

	private void setRenda(double renda) throws AtributoInvalidoException {       
		if (renda >= 0) {                   //Garante que o valor inicializado da renda n�o seja negativo   
			this.renda = renda;
		}else{
			throw new AtributoInvalidoException("Atributo Renda inv�lido!");
		}

	}

	private void setCpf(CPF cpf) throws AtributoInvalidoException {			
            if(cpf != null){
			this.cpf = cpf;
		}else{
			throw new AtributoInvalidoException("Atributo CPF inv�lido!");
		}
	}


	private void setNome(String nome) throws AtributoInvalidoException {			
		if (nome != null) {						//Testa se a String do nome � nula.
			this.nome = nome;
		}else{
			throw new AtributoInvalidoException("Atributo 'Nome' inv�lido!");
		}

	}

	private void setIdade(int idade) throws AtributoInvalidoException{			
		if (idade > 0) {						//Testa se a idade � um n�mero positivo maior que zero.
			this.idade = idade;
		}else {
			throw new AtributoInvalidoException("Atributo 'Idade' inv�lido!");
		}

	}

	public String getPrimeiroNome() { 		
		String[] primeiroNome = nome.split(" ");		//SEPARA A STRING DO NOME E GUARDA APENAS A PARTE DA STRING ANTES DO "ESPA�O".
		return primeiroNome[0];
	}

	public String getUltimoNome() { 		
		String[] ultimoNome = nome.split(" ");		//Separa a string e guarda apenas o ultimo nome depois do espa�o.
		return ultimoNome[ultimoNome.length - 1];
	}

	/**
	 * M�todo toString(), n�o recebe par�metros.
	 * Transforma todos os dados da classe me uma String e retorna essa String.
	 */
	public String toString() {
		String aux = "";			//Cria uma string para associar o n�mero do sexo (1 ou 2) com a palavra correspondente (masculino ou feminino)
		if (getSexo() == MASCULINO) {     //Testa se o sexo � masculino
			aux = "Masculino";
		} else if (getSexo() == FEMININO) {			//Testa se o sexo � feminino.
			aux = "Feminino";
		}

		return "\nNome: " + this.getNome() + "\nCPF: " + this.getCpf().getCPF() + "\nIdade: " + this.getIdade()
				+ "\nRenda: " + this.getRenda() + "\nSexo: " + aux;
	}
	
	/**
	 * Sobrescrita do m�todo equals(), recebe como par�metro um Object.
	 * Verifica se ele � uma inst�ncia de um Cliente e retorna um valor booleano de verdade caso o cpf do Cliente passado como cliente
	 * � igual ao cpf do cliente da classe.
	 */
	public boolean equals (Object obj) {
		boolean retorno = false;
		if (obj instanceof Cliente) {
			retorno = this.getCpf().equals(((Cliente) obj).getCpf());
		}
		return retorno;
	}

	/**
	 * M�todo sobrecarregado getChave(), retorna a chave da Classe no formato de String, contendo o CPF do Cliente.
	 */
	public String getChave(){
		return this.getCpf().getCPF();
	}

	
	public void validar() throws AtributoInvalidoException {
			if(this.cpf == null){
				throw new AtributoInvalidoException("CPF inv�lido!");
			}else if (this.nome == null || this.nome.length() > 60 || this.nome.isEmpty()) {
				throw new AtributoInvalidoException("Nome inv�lido!");
			}else if(this.idade < 18 || this.idade > 200){
				throw new AtributoInvalidoException("Idade inv�lida!");
			}else if(this.renda < 0 || this.renda >= 1000001){
				throw new AtributoInvalidoException("Renda inv�lida!");
			}else if( this.sexo != MASCULINO && this.sexo != FEMININO){
				throw new AtributoInvalidoException("Sexo inv�lido!");
			}

	}

}
