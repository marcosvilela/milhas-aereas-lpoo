package com.acme.testes.conta;

import com.acme.rn.conta.IdentificadorConta;

public class TesteIdentificadorConta {

	public static void main(String[] args) {

		IdentificadorConta idconta = new IdentificadorConta(11088285406l);

		// Calculando o dígito verificador para a conta informada
		System.out.println(idconta.calcularDigitoVerificador());

		// Verificando o dígito correto
		idconta.verificarValidadeDoDigito(43);
		// Verificando o dígito incorreto
		idconta.verificarValidadeDoDigito(41);

		// Informações gerais
		System.out.println(idconta.toString());

	}

}
