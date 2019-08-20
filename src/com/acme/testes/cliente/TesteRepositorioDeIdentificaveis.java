package com.acme.testes.cliente;

import java.util.Date;

import com.acme.ado.classesGerais.RepositorioDeRegistros;
import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.*;

public class TesteRepositorioDeIdentificaveis {
	public static void main(String[] args){
		//Instandiando um novo repositório de Identificáveis
		RepositorioDeRegistros repRegistros = new RepositorioDeRegistros();
		
		try{		
		
			CPF cpfDoCliente1 = new CPF("11088285406");
			Cliente cliente1 = new Cliente("Severino Portoes", cpfDoCliente1, 65, 200000.0, 1);
			CPF cpfDoCliente2 = new CPF("44433322211");
			Cliente cliente2 = new Cliente("Marcos Vilela", cpfDoCliente2, 17, 500, 1);
			CPF cpfDoCliente3 = new CPF("11100011100");
			Cliente cliente3 = new Cliente("Byron Leite", cpfDoCliente3, 34, 4500, 1);
			CPF cpfAlterar = new CPF("11088285406");
			Cliente alterar = new Cliente("Marcel Souza", cpfAlterar, 18, 2500, 1);
			CPF cpfDoClienteCM = new CPF("12345678900");
			Cliente clienteDaCM = new Cliente("Notorious Big", cpfDoClienteCM, 45, 2450, 1);
			IdentificadorConta idDoCliente = new IdentificadorConta(12345678900l);
			ContaMilhagem conta = new ContaMilhagem(idDoCliente, clienteDaCM);
			MovimentoContaCredito mv = new MovimentoContaCredito(conta, 200, conta.getNewClient().getNome(), new Date());
			
		    repRegistros.incluir(mv);
			repRegistros.incluir(conta);
			repRegistros.incluir(cliente1);
			repRegistros.incluir(cliente2);
			repRegistros.incluir(cliente3);
			
			System.out.println("-------- TESTANDO O BUSCAR -------");
			System.out.println(repRegistros.buscarPorChave("11088285406"));
			System.out.println(repRegistros.buscarPorChave("44433322211"));
			System.out.println(repRegistros.buscarPorChave("11100011100"));
			System.out.println(repRegistros.buscarPorChave("12345678900") + "\n");
			
			System.out.println("----- TESTANDO O ALTERAR ------");
			repRegistros.alterar(alterar);
			
			System.out.println(repRegistros.buscarPorChave("11088285406") + "\n");
			
			repRegistros.excluir("11100011100");
			
			System.out.println("----- TESTANDO O BUSCAR TODOS -----");
			repRegistros.buscarTodos();
			System.out.println(repRegistros.toString());
		}catch(AtributoInvalidoException e1){
			System.out.println(e1.getMessage());
		}catch(ObjetoExistenteException e2){
			System.out.println(e2.getMessage());
		}catch (ObjetoInexistenteException e3) {
			System.out.println(e3.getMessage());
		}catch (QuebraDeRegraException e4) {
			System.out.println(e4.getMessage());
		}
		
		
/* Testes para ocorrência das exceções*/
		
		try {
				
				CPF cpfDoClienteFake = new CPF("12345678300");	
				Cliente clienteFake = new Cliente("Notorious Big", cpfDoClienteFake, 45, 2450, 2);
				repRegistros.excluir(clienteFake.getChave());
				
			} catch (AtributoInvalidoException e) {
				System.out.println(e.getMessage());
			} catch (ObjetoInexistenteException e) {
			System.out.println(e.getMessage());
			}
		
			
		
	}
}
