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
import com.acme.rn.conta.MovimentoContaTransferencia;

public class TesteMovimentoContaTransferencia {

	public static void main(String[] args) {
	try{
		ContaMilhagem contaDeOrigem = new ContaMilhagem(new IdentificadorConta(11122233344l), new Cliente("Marcos Vilela", new CPF("11122233344"), 17, 2000, 1));
		ContaMilhagem contaDeDestino = new ContaMilhagem(new IdentificadorConta(11088285406l), new Cliente("Marcel Souza", new CPF("11088285406"), 18, 5000, 1));
		MovimentoContaTransferencia movimento = new MovimentoContaTransferencia(contaDeOrigem, contaDeDestino, 100, contaDeOrigem.getNewClient().getPrimeiroNome(), new Date());
		
		//Inserindo uma nova Transferência
		ControladorMovimentoConta.inserir(movimento);
		
		//Printando na tela as informações da Transferência
		System.out.println(movimento.toString());
		
			}catch(AtributoInvalidoException e1){
				System.out.println(e1.getMessage());
			}catch (ObjetoInexistenteException e2) {
				System.out.println(e2.getMessage());
			}catch (ObjetoExistenteException e3) {
				System.out.println(e3.getMessage());
			}
	}

}
