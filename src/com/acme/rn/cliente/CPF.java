package com.acme.rn.cliente;

import java.io.Serializable;
import java.lang.Character;

import com.acme.excecoes.AtributoInvalidoException;

@SuppressWarnings("serial")
public class CPF implements Serializable{

	private String cpf;

	
	/**
	 * Construtor da classe, que inicializa a cadeia de caracteres do CPF. Garante também que essa cadeia de caracteres tenha apenas
	 * 11 dígitos, tal qual o modelo básico do CPF.
	 * @param cpf
	 * @throws AtributoInvalidoException
	 */
	public CPF(String cpf) throws AtributoInvalidoException { 		
		setCpf(cpf);
	}
	
	/**
	 * Método getCPF(), permite o uso o valor existente na variável CPF
	 * @return
	 */
	public String getCPF() { 		
		return this.cpf; 
	}
	
	/**
	 * Método setCpf(), inicializa a variável CPF com uma String não nula. Garante também que a String do CPF possua apenas 
	 * números 
	 * @param cpf
	 */
	public void setCpf(String cpf) throws AtributoInvalidoException{		
		if(cpf !=null){
			for (int i = 0; i < cpf.length(); i++) {		
				if (!Character.isDigit(cpf.charAt(i))) {		
					System.out.println("CPF admite apenas números!");
					break;
				} else {
					this.cpf = cpf;
			
				}
			}
		}else{
			throw new AtributoInvalidoException("CPF inválido!");
	}
}		

	/**
	 * Método validar(), testa se o CPF passado como parâmetro possua apenas 11 caracteres. Se sim, retorna verdadeiro; 
	 * Se não, retorna falso.
	 * @param cpf
	 * @return boolean
	 */
	public boolean validar(CPF cpf) { 		
		boolean retorno = false;
		if (cpf.getCPF().length() == 11) {		
			System.out.println("CPF validado com sucesso!");
			retorno = true;
		}

		return retorno;

	}

	/**
	 * Método toString(), que transforma todos os dados da Classe em uma String, para facilitar sua impressão no console.
	 */
	public String toString() {  //Transforma todos os dados da classe em uma String e retorna a mesma.
		return "CPF: " + cpf;
	}

	/**
	 * Sobrescrita do método equals(), que testa se o objeto passado é uma instância de CPF, e compara o parâmetro com o CPF da própria 
	 * Classe.
	 */
	public boolean equals(Object obj) {			//Método sobrescrito, que testa se dois CPF's são iguais.
		boolean retorno = false;
		if(obj instanceof CPF){
			retorno = this.getCPF().equals(((CPF) obj).getCPF());
		}
		return retorno;
	}
}
