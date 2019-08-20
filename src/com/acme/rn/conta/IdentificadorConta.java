package com.acme.rn.conta;

import java.io.Serializable;

@SuppressWarnings("serial")
public class IdentificadorConta implements Serializable{
	private long numeroDaConta; 

	/**
	 * Construtor da Classe, inicializa as variáveis necessárias do Identificador Conta.
	 * @param numeroDaConta
	 */
	public IdentificadorConta(long numeroDaConta) {
		setNumeroDaConta(numeroDaConta);
	}

	/**
	 * Getters() e Setters(), métodos para retornar e dar valores às variáveis
	 * @return
	 */
	public long getNumeroDaConta() { 
		return numeroDaConta; 
								
	}

	private void setNumeroDaConta(long numeroDaConta) {
		this.numeroDaConta = numeroDaConta;

	}

	/**
	 * Método calcularDigitoVerificador(), calcula e retorna os dois digitos verificadores. Inicializa a variável soma com 0 (elemento neutro), 
	 * e um laço se repete até que o número da conta seja reduzido a zero e não exista mais nada para somar. Então, se incrementa a variável soma com o último dígito do número da conta,
	 * eliminando o último dígito previamente somado, para continuar a somar no loop. No fim, o resto da divisão da soma por 99 é o dígito verificador.
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
	 * Método  verificarValidadeDoDigito(), recebe um numero e testa se esse numero é o dígito verificador. Se for, retorna verdadeiro. Se não, falso.
	 * @param dv
	 * @return boolean
	 */
	public boolean verificarValidadeDoDigito(int dv) { 
		boolean retorno = false;
		if(dv >=0 && dv <= 99){         
		if (dv == calcularDigitoVerificador()) {
			System.out.println("Dígito correto");
			retorno = true;
		} else {
			System.out.println("Dígito incorreto!");
		}
		return retorno;
	}
		return retorno;
		}

	/**
	 * Méto toString(), transforma os dados da Classe em uma String e a retorna
	 */
	public String toString() {

		return "Identificador da Conta: " + numeroDaConta;
	}

	/**
	 * Método sobrescrito equals(), que recebe um objeto e checa se esse objeto é uma instância da Classe IdentificadorConta. Se sim, compara o Identificador
	 * da Conta passada como parâmetro com o Identificador da Classe. 
	 */
	public boolean equals(Object obj) {
		boolean retorno = false;
		if (obj instanceof IdentificadorConta) {
			retorno = ((IdentificadorConta) obj).getNumeroDaConta() == this.getNumeroDaConta();
		}
		return retorno;
	}

}
