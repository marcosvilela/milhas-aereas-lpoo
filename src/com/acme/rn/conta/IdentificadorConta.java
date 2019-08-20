package com.acme.rn.conta;

import java.io.Serializable;

@SuppressWarnings("serial")
public class IdentificadorConta implements Serializable{
	private long numeroDaConta; 

	/**
	 * Construtor da Classe, inicializa as vari�veis necess�rias do Identificador Conta.
	 * @param numeroDaConta
	 */
	public IdentificadorConta(long numeroDaConta) {
		setNumeroDaConta(numeroDaConta);
	}

	/**
	 * Getters() e Setters(), m�todos para retornar e dar valores �s vari�veis
	 * @return
	 */
	public long getNumeroDaConta() { 
		return numeroDaConta; 
								
	}

	private void setNumeroDaConta(long numeroDaConta) {
		this.numeroDaConta = numeroDaConta;

	}

	/**
	 * M�todo calcularDigitoVerificador(), calcula e retorna os dois digitos verificadores. Inicializa a vari�vel soma com 0 (elemento neutro), 
	 * e um la�o se repete at� que o n�mero da conta seja reduzido a zero e n�o exista mais nada para somar. Ent�o, se incrementa a vari�vel soma com o �ltimo d�gito do n�mero da conta,
	 * eliminando o �ltimo d�gito previamente somado, para continuar a somar no loop. No fim, o resto da divis�o da soma por 99 � o d�gito verificador.
	 * @return int
	 */
	public int calcularDigitoVerificador() { 
		long dv = getNumeroDaConta();
		int soma = 0;            
		while (dv != 0) {        
			soma += dv%10;
			dv = dv / 10;     
		}
		soma %= 99; 

		return soma;
	}

	/**
	 * M�todo  verificarValidadeDoDigito(), recebe um numero e testa se esse numero � o d�gito verificador. Se for, retorna verdadeiro. Se n�o, falso.
	 * @param dv
	 * @return boolean
	 */
	public boolean verificarValidadeDoDigito(int dv) { 
		boolean retorno = false;
		if(dv >=0 && dv <= 99){         
		if (dv == calcularDigitoVerificador()) {
			System.out.println("D�gito correto");
			retorno = true;
		} else {
			System.out.println("D�gito incorreto!");
		}
		return retorno;
	}
		return retorno;
		}

	/**
	 * M�to toString(), transforma os dados da Classe em uma String e a retorna
	 */
	public String toString() {

		return "Identificador da Conta: " + numeroDaConta;
	}

	/**
	 * M�todo sobrescrito equals(), que recebe um objeto e checa se esse objeto � uma inst�ncia da Classe IdentificadorConta. Se sim, compara o Identificador
	 * da Conta passada como par�metro com o Identificador da Classe. 
	 */
	public boolean equals(Object obj) {
		boolean retorno = false;
		if (obj instanceof IdentificadorConta) {
			retorno = ((IdentificadorConta) obj).getNumeroDaConta() == this.getNumeroDaConta();
		}
		return retorno;
	}

}
