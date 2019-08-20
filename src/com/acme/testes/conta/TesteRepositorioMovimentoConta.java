package com.acme.testes.conta;

import java.util.Date;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.*;

public class TesteRepositorioMovimentoConta {

	public static void main(String[] args) {
		// Criação de uma Conta Milhagem.
try{
		ContaMilhagem conta = new ContaMilhagem(new IdentificadorConta(11111111112l),
				new Cliente("Augusto Ferreira", new CPF("11407678974"), 50, 15000.0, 1));

		// Inicialização dos atributos para a segunda Conta Milhagem.

		CPF cpfDoCliente = new CPF("10101010213");
		Cliente broca = new Cliente("Yuri Broca", cpfDoCliente, 18, 600.0, 1);

		// Criação da segunda Conta Milhagem.

		

		// Capturando a data da operação -> Essa data vai ser comparada para
		// incluir ou não na movimentação.

		Date data = new Date(); // Inicialização de uma data;

		String[] nome1 = conta.getNewClient().getNome().split(" ");
		String[] nome2 = broca.getNome().split(" ");
		
		if(nome1.length <= 1){
			System.out.println("Nome do cliente incompleto!");
		}
		else if(nome2.length <=1 ){
			System.out.println("Nome do cliente incompleto");
		}
		else{
			MovimentoContaCredito transacao1 = new MovimentoContaCredito(conta, 200,
	broca.getUltimoNome() + ", " + conta.getNewClient().getPrimeiroNome() + " MR", data);

			// INCLUINDO UMA MOVIMENTAÇÃO.
			
			ControladorMovimentoConta.inserir(transacao1);
			}
		}catch(AtributoInvalidoException e1){
			System.out.println(e1.getMessage());
		}catch (ObjetoInexistenteException e2) {
			System.out.println(e2.getMessage());
		}catch (ObjetoExistenteException e3) {
			System.out.println(e3.getMessage());
		}
	}
}
