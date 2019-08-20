package com.acme.testes.conta;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.IdentificadorConta;

public class TesteContaMilhagem {

	public static void main(String[] args) {
try{
		ContaMilhagem conta1 = new ContaMilhagem(new IdentificadorConta(11111111111l),
				new Cliente("Marcos Vilela", new CPF("11828507407"), 17, 1400.0, 1));
		// Saldo inicial = 0
		System.out.println(conta1.getSaldo());

		// Saldo incrementado = 0 + 140 = 140
		System.out.println(conta1.creditar(140));
		// Saldo decrementado = 140 - 120 = 20
		conta1.debitar(120);
		System.out.println(conta1.getSaldo());
		// Atividade da conta ao inicializar
		System.out.println(conta1.getAtiva());
		conta1.desativar();
		// Atividade da conta após o desativar()
		System.out.println(conta1.getAtiva());
		conta1.reativar();
		// Conta reativada
		System.out.println(conta1.getAtiva());

		// Conta nova para testar a transferência
		ContaMilhagem conta2 = new ContaMilhagem(new IdentificadorConta(11111111111l),
				new Cliente("Marcos Vilela", new CPF("11828507407"), 17, 1400.0, 1));

		conta2.creditar(240);
		conta2.transferir(200, conta1);

		// Saldo da conta1 + 200:
		System.out.println(conta1.getSaldo());



		// Printando na tela as informações da conta milhagem

		System.out.println(conta1.toString());

	}catch(AtributoInvalidoException e1){
			System.out.println(e1.getMessage());
		}catch (QuebraDeRegraException e2) {
			System.out.println(e2.getMessage());
		}
	}

}
