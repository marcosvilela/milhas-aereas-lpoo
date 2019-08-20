package com.acme.testes.conta;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.ContaMilhagemPremium;
import com.acme.rn.conta.IdentificadorConta;

public class TesteContaMilhagemPremium {

	public static void main(String[] args) {
		try{
			ContaMilhagemPremium contaPremium = new ContaMilhagemPremium(new IdentificadorConta(11122233344l),
		
				new Cliente("Marcos Vilela", new CPF("11122233344"), 17, 1000, 1));

		ContaMilhagemPremium contaPremium2 = new ContaMilhagemPremium(new IdentificadorConta(11088285406l),
				new Cliente("Marcel Souza", new CPF("11088285406"), 18, 2000, 1), 1.72);
		
		
		System.out.println(contaPremium.getMultiplicador() + " " + contaPremium.getNewClient().getPrimeiroNome());
		System.out.println(contaPremium2.getMultiplicador() + " " + contaPremium2.getNewClient().getPrimeiroNome());
		
		contaPremium.creditar(1000);
		contaPremium.creditar(10, contaPremium.getMultiplicador());
		System.out.println("Saldo: Creditar R$ " + contaPremium.getSaldo() + " " + contaPremium.getNewClient().getPrimeiroNome());
		
		contaPremium2.creditar(500);
		contaPremium2.creditar(10, 1);
		System.out.println("Saldo: Creditar R$ " + contaPremium2.getSaldo() + " " + contaPremium2.getNewClient().getPrimeiroNome());
		
		contaPremium.debitar(100);
		System.out.println("Saldo: Debitar R$ " + contaPremium.getSaldo() + " " + contaPremium.getNewClient().getPrimeiroNome());
		contaPremium2.debitar(200);
		System.out.println("Saldo: Debitar R$ " + contaPremium2.getSaldo() + " " + contaPremium2.getNewClient().getPrimeiroNome());
		
		contaPremium.transferir(200, contaPremium2);
		System.out.println("Saldo: Transferir R$ " + contaPremium.getSaldo() + " " + contaPremium.getNewClient().getPrimeiroNome());
		System.out.println("Saldo: Transferir R$ " + contaPremium2.getSaldo() + " " + contaPremium2.getNewClient().getPrimeiroNome());
		}catch(AtributoInvalidoException e1){
			System.out.println(e1.getMessage());
		}catch (QuebraDeRegraException e2) {
			System.out.println(e2.getMessage());
		}
	}
		}


