package com.acme.testes.cliente;

import java.util.Date;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.*;

public class TesteIdentificaveis {
	public static void main(String[] args){
	try{	
		    Cliente a = new Cliente("Marcel Souza", new CPF("11088285406"), 18, 1000, 1);
			ContaMilhagem b = new ContaMilhagem(new IdentificadorConta(11122233344l), new Cliente("Marcos Vilela", new CPF("11122233344"), 17, 2000, 1));
			MovimentoContaCredito c = new MovimentoContaCredito(b, 200, b.getNewClient().getPrimeiroNome() , new Date());
			
			System.out.println(a.getChave() + "\n" + b.getChave() + "\n" + c.getChave() + "\n");
		}catch(AtributoInvalidoException erro){
			System.out.println(erro.getMessage());
		}
	
	
	}
}
