package com.acme.testes.cliente;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;

public class TesteCliente {

	public static void main(String[] args) {

		Cliente c1;
		try {
			c1 = new Cliente("Marcel Airlen Souza Ramos", new CPF("11088285406"), 17, 1400.0, 1);
			// Printando na tela as informações do cliente!
			System.out.println(c1.toString());
		} catch (AtributoInvalidoException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("\n\n");
		Cliente c2;
		try{
			c2 = new Cliente("", new CPF(null), 15, -10, 3);
			System.out.println(c2.toString());
		}catch(AtributoInvalidoException e){
			System.out.println(e.getMessage());
		}
	}
}
