package com.acme.rn.conta;

import java.io.Serializable;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.cliente.Cliente;

@SuppressWarnings("serial")
public class ContaMilhagemPremium extends ContaMilhagem implements Serializable{

/**
 * Variável especial desse tipo de ContaMilhagem	
 */
	private double multCredito;
	
	/**
	 * Construtor da Classe, que recebe os valores e inicializa as variáveis com os valores passados como parâmetro. Usa o construtor da Superclasse para inicializar os atributos que pertencem à esta
	 * @param id
	 * @param c
	 * @param multCredito
	 */
	public ContaMilhagemPremium(IdentificadorConta id, Cliente c, double multCredito) throws AtributoInvalidoException{
		super(id, c);
		this.setMultiplicador(multCredito);
	}
	
	/**
	 * Construtor especial da Classe, que só recebe um Identificador e um Cliente. Inicializa os valores das variáveis com o construtor da Superclasse, e inicializa o atributo Multiplicador de Crédito com o valor 1.
	 * @param id
	 * @param c
	 */
	public ContaMilhagemPremium(IdentificadorConta id, Cliente c) throws AtributoInvalidoException{
		this(id, c, 1);
	}
	
	/**
	 * Getters() e Setters(), permitem o acesso às variáveis, e também permitem a inicialização de seus valores.
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
 * Método creditar() sobrescrito. Leva em consideração um multiplicador Imediato de crédito passado como parâmetro, que altera o valor inicial e o passa como parâmetro do creditar() da superclasse. Retorna o Saldo posterior ao crédito.
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
	 * Método creditar() sobrecarregado. Leva em consideração o multiplicador de crédito da própria classe, sem ser passado como parâmetro. Usa o método creditar() sobrescrito, passando o valor do parâmetro e o multiplicador da Classe.
	 * @throws QuebraDeRegraException 
	 */
	public int creditar(int valor) throws QuebraDeRegraException{
		if(valor > 0){
			creditar(valor, this.getMultiplicador());
		}
		return super.getSaldo();
	}
}