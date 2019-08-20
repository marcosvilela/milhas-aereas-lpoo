package com.acme.rn.conta;

import java.io.Serializable;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.cliente.Cliente;

@SuppressWarnings("serial")
public class ContaMilhagemPremium extends ContaMilhagem implements Serializable{

/**
 * Vari�vel especial desse tipo de ContaMilhagem	
 */
	private double multCredito;
	
	/**
	 * Construtor da Classe, que recebe os valores e inicializa as vari�veis com os valores passados como par�metro. Usa o construtor da Superclasse para inicializar os atributos que pertencem � esta
	 * @param id
	 * @param c
	 * @param multCredito
	 */
	public ContaMilhagemPremium(IdentificadorConta id, Cliente c, double multCredito) throws AtributoInvalidoException{
		super(id, c);
		this.setMultiplicador(multCredito);
	}
	
	/**
	 * Construtor especial da Classe, que s� recebe um Identificador e um Cliente. Inicializa os valores das vari�veis com o construtor da Superclasse, e inicializa o atributo Multiplicador de Cr�dito com o valor 1.
	 * @param id
	 * @param c
	 */
	public ContaMilhagemPremium(IdentificadorConta id, Cliente c) throws AtributoInvalidoException{
		this(id, c, 1);
	}
	
	/**
	 * Getters() e Setters(), permitem o acesso �s vari�veis, e tamb�m permitem a inicializa��o de seus valores.
	 * @return
	 */
	public double getMultiplicador(){
		return multCredito;
	}
	
	public void setMultiplicador(double multiplicador){
		if(multiplicador > 0)
			this.multCredito = multiplicador;
	}

/**
 * M�todo creditar() sobrescrito. Leva em considera��o um multiplicador Imediato de cr�dito passado como par�metro, que altera o valor inicial e o passa como par�metro do creditar() da superclasse. Retorna o Saldo posterior ao cr�dito.
 * @param valor
 * @param multImediat
 * @return int
 * @throws QuebraDeRegraException 
 */
	public int creditar(int valor, double multImediat) throws QuebraDeRegraException{
		if(valor > 0){
			valor = (int) (valor*multImediat);
			super.creditar(valor);
		}
		return super.getSaldo();
	}
	
	/**
	 * M�todo creditar() sobrecarregado. Leva em considera��o o multiplicador de cr�dito da pr�pria classe, sem ser passado como par�metro. Usa o m�todo creditar() sobrescrito, passando o valor do par�metro e o multiplicador da Classe.
	 * @throws QuebraDeRegraException 
	 */
	public int creditar(int valor) throws QuebraDeRegraException{
		if(valor > 0){
			creditar(valor, this.getMultiplicador());
		}
		return super.getSaldo();
	}
}