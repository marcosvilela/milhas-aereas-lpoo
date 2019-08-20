package com.acme.testes.cliente;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.rn.cliente.CPF;

public class TesteCPF {

	public static void main(String[] args) {
try{
			CPF cpf = new CPF("11828507407");
			CPF cpf2 = new CPF("1124789621");
	
			// Printa true se for válido, false caso contrário
			System.out.println(cpf.validar(cpf));
			System.out.println(cpf.validar(cpf2));
	
			System.out.println(cpf.toString() + "\n" + cpf2.toString());
	}catch(AtributoInvalidoException e){
			System.out.println(e.getMessage());
		}

try{
	CPF cpferro = new CPF(null);
	System.out.println(cpferro.validar(cpferro));
	
}catch(AtributoInvalidoException e){
	System.out.println(e.getMessage());
}
	}
}