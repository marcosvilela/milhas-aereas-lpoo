package com.acme.rn.cliente;

import java.io.Serializable;
import java.lang.Character;

import com.acme.excecoes.AtributoInvalidoException;

@SuppressWarnings("serial")
public class CPF implements Serializable{

	private String cpf;

	
	/**
	 * Construtor da classe, que inicializa a cadeia de caracteres do CPF. Garante tamb�m que essa cadeia de caracteres tenha apenas
	 * 11 d�gitos, tal qual o modelo b�sico do CPF.
	 * @param cpf
	 * @throws AtributoInvalidoException
	 */
	public CPF(String cpf) throws AtributoInvalidoException { 		
		setCpf(cpf);
	}
	
	/**
	 * M�todo getCPF(), permite o uso o valor existente na vari�vel CPF
	 * @return
	 */
	public String getCPF() { 		
		return this.cpf; 
	}
	
	/**
	 * M�todo setCpf(), inicializa a vari�vel CPF com uma String n�o nula. Garante tamb�m que a String do CPF possua apenas 
	 * n�meros 
	 * @param cpf
	 */
	public void setCpf(String cpf) throws AtributoInvalidoException{		
		if(cpf !=null){
			for (int i = 0; i < cpf.length(); i++) {		
				if (!Character.isDigit(cpf.charAt(i))) {		
					System.out.println("CPF admite apenas n�meros!");
					break;
				} else {
					this.cpf = cpf;
			
				}
			}
		}else{
			throw new AtributoInvalidoException("CPF inv�lido!");
	}
}		

	/**
	 * M�todo validar(), testa se o CPF passado como par�metro possua apenas 11 caracteres. Se sim, retorna verdadeiro; 
	 * Se n�o, retorna falso.
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
	 * M�todo toString(), que transforma todos os dados da Classe em uma String, para facilitar sua impress�o no console.
	 */
	public String toString() {  //Transforma todos os dados da classe em uma String e retorna a mesma.
		return "CPF: " + cpf;
	}

	/**
	 * Sobrescrita do m�todo equals(), que testa se o objeto passado � uma inst�ncia de CPF, e compara o par�metro com o CPF da pr�pria 
	 * Classe.
	 */
	public boolean equals(Object obj) {			//M�todo sobrescrito, que testa se dois CPF's s�o iguais.
		boolean retorno = false;
		if(obj instanceof CPF){
			retorno = this.getCPF().equals(((CPF) obj).getCPF());
		}
		return retorno;
	}
}
