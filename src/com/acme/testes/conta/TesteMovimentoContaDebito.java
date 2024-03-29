package com.acme.testes.conta;

import java.util.Date;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.ControladorMovimentoConta;
import com.acme.rn.conta.IdentificadorConta;
import com.acme.rn.conta.MovimentoContaDebito;

public class TesteMovimentoContaDebito {

	public static void main(String[] args) {
	try{	
		ContaMilhagem contaDeOrigem = new ContaMilhagem(new IdentificadorConta(11122233344l), new Cliente("Marcos Vilela", new CPF("11122233344"), 17, 2000, 1));
		MovimentoContaDebito movimento = new MovimentoContaDebito(contaDeOrigem, 200, contaDeOrigem.getNewClient().getPrimeiroNome(), new Date());
		//Inserindo o D�bito
		ControladorMovimentoConta.inserir(movimento);
		//Printando as informa��es do D�bito
		System.out.println(movimento.toString());
		}catch(AtributoInvalidoException e){
			System.out.println(e.getMessage());
		}catch (ObjetoInexistenteException e2) {
			System.out.println(e2.getMessage());
		}catch (ObjetoExistenteException e3) {
			System.out.println(e3.getMessage());
		}
	}
}
