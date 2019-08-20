package com.acme.testes.conta;

import com.acme.rn.conta.IdentificadorConta;

public class TesteIdentificadorConta {

	public static void main(String[] args) {

		IdentificadorConta idconta = new IdentificadorConta(11088285406l);

		// Calculando o d�gito verificador para a conta informada
		System.out.println(idconta.calcularDigitoVerificador());

		// Verificando o d�gito correto
		idconta.verificarValidadeDoDigito(43);
		// Verificando o d�gito incorreto
		idconta.verificarValidadeDoDigito(41);

		// Informa��es gerais
		System.out.println(idconta.toString());

	}

}
