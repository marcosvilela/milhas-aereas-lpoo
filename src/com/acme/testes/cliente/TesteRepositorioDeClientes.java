package com.acme.testes.cliente;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.cliente.ControladorCliente;

public class TesteRepositorioDeClientes {

	public static void main(String[] args) {

		try{
		// CRIAÇÃO DO REPOSITÓRIO E DOS CLIENTES.
		Cliente newClient = new Cliente("Marcel Souza", new CPF("11088285406"), 17, 1400.0, 1);
		Cliente newClient2 = new Cliente("Marcos Vilela", new CPF("12345678900"), 17, 2500.0, 1);
		Cliente newClient3 = new Cliente("Airton Paz", new CPF("33020264472"), 52, 6500.0, 1);
		Cliente alterar = new Cliente("Severino Portões", new CPF("12345678900"), 54, 200000.0, 1);

		// ADICIONANDO OS CLIENTES.
		ControladorCliente.incluir(newClient);
		ControladorCliente.incluir(newClient2);
		ControladorCliente.incluir(newClient3);

		// TESTANDO O BUSCAR.
		if(ControladorCliente.buscar(new CPF("12345678900")) == null){
			System.out.println("O Cliente não pôde ser encontrado, sentimos muito!\n");
		} else {
			System.out.println("Cliente " + newClient2.getPrimeiroNome() + " encontrado com sucesso!\n");
		};


		// TESTANDO O ALTERAR.
		ControladorCliente.alterar(alterar);
		// TESTANDO O EXCLUIR.
		ControladorCliente.excluir(new CPF("33020264472"));

		// TESTANDO O BUSCAR TODOS.
		ControladorCliente.buscarTodos();
		}catch(AtributoInvalidoException e1){
			System.out.println(e1.getMessage());
		}catch(ObjetoExistenteException e2){
			System.out.println(e2.getMessage());
		}catch (ObjetoInexistenteException e3) {
			System.out.println(e3.getMessage());
		}catch (QuebraDeRegraException e4) {
			System.out.println(e4.getMessage());
		}
		
		try{
			Cliente clienterrado = new Cliente(null,new CPF(null),800,-50.0,4);
			ControladorCliente.incluir(clienterrado);
			ControladorCliente.buscar(null);
		}catch(AtributoInvalidoException e){
			System.out.println(e.getMessage());
		}catch(ObjetoInexistenteException erro){
			System.out.println(erro.getMessage());
		} catch (ObjetoExistenteException erroa) {
			System.out.println(erroa.getMessage());
		}
	
	}
}
