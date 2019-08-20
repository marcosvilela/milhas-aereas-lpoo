package com.acme.testes.conta;

import java.util.Date;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.IdentificadorConta;
import com.acme.rn.conta.MovimentoContaDebito;

public class TesteMovimentoConta {

	public static void main(String[] args) {

		// Cria��o das contas milhagem(objeto) para a movimenta��o da conta.
try{
		CPF cpf = new CPF("11088285406");
		Cliente newClient = new Cliente("Marcel Airlen Souza Ramos", cpf, 17, 1400.0, 1);
		IdentificadorConta idconta = new IdentificadorConta(11088285406l);
		ContaMilhagem contaDeOrigem = new ContaMilhagem(idconta, newClient);

		// Cria��o da movimenta��o de conta(objeto).

		MovimentoContaDebito movimentacao = new MovimentoContaDebito(contaDeOrigem, 300, "", new Date());

		// Printando na tela a movimenta��o.

		System.out.println(movimentacao.toString());

		}catch(AtributoInvalidoException e) {
			System.out.println(e.getMessage());
		}
	}
}
