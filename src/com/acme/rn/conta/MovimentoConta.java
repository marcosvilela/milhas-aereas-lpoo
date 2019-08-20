package com.acme.rn.conta;

import java.io.Serializable;
import java.util.Date;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.rn.classesGerais.Registro;

@SuppressWarnings("serial")
public abstract class MovimentoConta extends Registro implements Serializable{

	private ContaMilhagem contaDeOrigem; 	
	private int valor;
	private Date dataDaOperacao;
	private String nomeExtrato;
	

	/**
	 * Construtor da Classe, inicializa as variáveis com os valores passados como parâmetros. 
	 * @param contaDeOrigem
	 * @param valor
	 * @param tipoDeTransacao
	 * @param contaDeDestino
	 * @param nomeExtrato
	 * @param dataDaOperacao
	 */
	public MovimentoConta(ContaMilhagem contaDeOrigem, int valor,		//Construtor da classe, inicializa as variáveis da classe.
			String nomeExtrato, Date dataDaOperacao) {
		super();
		setContaDeOrigem(contaDeOrigem);
		setValor(valor);
		this.nomeExtrato = getNomeExtrato();
		setDataDaOperacao(dataDaOperacao);
	}

	/**
	 * Getters() e Setters(), retornam e inicializam os valores dos atributos, garantindo que eles não sejam nulos/vazios/ilógicos
	 * @return
	 */
	public ContaMilhagem getContaDeOrigem() {		
		return contaDeOrigem; 
		}

	private void setContaDeOrigem(ContaMilhagem contaDeOrigem) {		
		if(contaDeOrigem != null){
		this.contaDeOrigem = contaDeOrigem;
		}
	}
	
	public int getValor() {		
		return valor;
	}

	private void setValor(int valor) {		
		if(valor >= 0)
		this.valor = valor;
	}

	/**
	 * Getters(contaDeOrigem, valor, tipoDeTransacao, dataDaOperacao, nomeExtrato)
	 * Retornam os valores dos atributos.
	 * 
	 * Setters(contaDeOrigem, valor, tipoDeTRansacao, dataDaOperacao, nomeExtrato)
	 * Atribuem aos atributos os valores passados como parâmetros.
	 */

	public Date getDataDaOperacao() {
		return dataDaOperacao;
	}

	private void setDataDaOperacao(Date dataDaOperacao) {
		this.dataDaOperacao = dataDaOperacao;
	}

	/**
	 * Método getNomeExtrato(), que retorna um nome de Extrato de acordo com o modelo e os atributos anteriores.
	 * @return
	 */
	public String getNomeExtrato() {
										
		String tratamento;
		String retorno = null;

		if (contaDeOrigem.getNewClient().getSexo() == 1) {
			tratamento = "MR";
			retorno = contaDeOrigem.getNewClient().getUltimoNome().toUpperCase() + ", "
					+ contaDeOrigem.getNewClient().getPrimeiroNome().toUpperCase() + " " + tratamento + ".";
		} else if (contaDeOrigem.getNewClient().getSexo() == 2) {
			tratamento = "MRS";
			retorno = contaDeOrigem.getNewClient().getUltimoNome().toUpperCase() + ", "
					+ contaDeOrigem.getNewClient().getPrimeiroNome().toUpperCase() + " " + tratamento + ".";
		} else {
			System.out.println("Opção escolhida inválida!");
		}
		return retorno;
	}

	/**
	 * Método toString() transforma todos os dados da classe em uma String e a retorna.
	 */
	public String toString() {
		String datesAndHours[] = new String[150];
		String data = dataDaOperacao.toString();
		for (int i = 0; i < datesAndHours.length; i++) {
			if (datesAndHours[i] == null) {
				datesAndHours[i] = data;
			}
		}

		return "\nConta de Origem: " + contaDeOrigem.getNewClient().getPrimeiroNome() + "\nValor: " + valor
				+ "\nData Da Operacao: " + data
				+ "\nNome no Extrato: " + nomeExtrato;
	}
	
	/**
	 * Método sobrescrito equals(), que recebe um objeto e testa se esse objeto é uma instância da Classe MovimentoConta. Se sim, compara o valor passado como parâmetro
	 * com o da própria Classe.
	 */
	public boolean equals (Object obj) {
		boolean retorno = false;
		if (obj instanceof MovimentoConta){
			retorno = this.getDataDaOperacao().equals(((MovimentoConta)obj).getDataDaOperacao());
		}
		return retorno;
	}
	
/**
 * Método sobrecarregado getChave(), que retorna uma Chave no formato de String, contendo o número da Conta e a Data da Operação.
 */
	public String getChave(){
		return this.getContaDeOrigem().getChave() + this.getDataDaOperacao().getTime();
	}
	
	public void validar() throws AtributoInvalidoException{
		
		if(this.contaDeOrigem == null){
			throw new AtributoInvalidoException("Conta Milhagem de origem inválida!");
			
		}else if (this.valor <= 0) {
			throw new AtributoInvalidoException("Valor da transação inválido!");
			
		}else if(this.nomeExtrato.length() > 100){
			throw new AtributoInvalidoException("Nome da fonte inválido!");
		
		}else if(this.dataDaOperacao == null){
			throw new AtributoInvalidoException("Data da operação inválida!");
		}
	}


}
