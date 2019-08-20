package com.acme.ado.cliente;

import java.io.IOException;

import com.acme.ado.classesGerais.RepositorioDeRegistrosIO;
import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.ProblemaFisicoException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.classesGerais.Registro;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;

public class RepositorioDeClientesIO implements MetodosRepositorioCliente{
	
	RepositorioDeRegistrosIO<Cliente> rep = new RepositorioDeRegistrosIO<Cliente>();
	
	@Override
	public boolean adicionar(Cliente newClient) throws ObjetoExistenteException, ObjetoInexistenteException {
		boolean retorno = false;
		try {
			if(rep.incluir(newClient)){
				retorno = true;
			}
		} catch (IOException e) {
			throw new ProblemaFisicoException("O arquivo não pôde ser criado.", e);
		}
		return retorno;
	}

	@Override
	public boolean alterar(Cliente c1)
			throws AtributoInvalidoException, QuebraDeRegraException, ObjetoInexistenteException {
		boolean retorno = false;
		try {
			if(rep.alterar(c1)){
				retorno = true;
			}
		} catch (IOException e) {
			throw new ProblemaFisicoException("O arquivo não pôde ser alterado.", e);
		}
		return retorno;
	}

	@Override
	public boolean excluir(Cliente newClient) throws ObjetoInexistenteException {
		boolean retorno = false;
		if(rep.excluir(newClient.getChave())){
			retorno = true;
		}
		return retorno;
	}

	@Override
	public Cliente buscar(CPF cpf) throws ObjetoInexistenteException {
		Cliente retorno = null;
		if(cpf != null){
			try {
				retorno = (Cliente) rep.buscarPorChave(cpf.getCPF());
			} catch (ProblemaFisicoException e) {
				throw new ProblemaFisicoException("O arquivo não pôde ser criado.", e);
			} catch (ClassNotFoundException e) {
				throw new ProblemaFisicoException("O arquivo possui objetos que não pertencem ao .class", e);
			}
		}
		return retorno;
	}

	@Override
	public Cliente[] buscarTodos() throws ObjetoInexistenteException {
		int contador;
		int contadorNaoVazio;
		Registro[] todosID;
		Cliente[] todos = null;
		try {
			todosID = rep.buscarTodos();
			todos = new Cliente[todosID.length];
			for(contador = 0, contadorNaoVazio = 0; contador < todos.length; contador++){
				if (todosID[contador] != null && todosID[contador] instanceof Cliente) {	
					todos[contador] = (Cliente) todosID[contador];
					contadorNaoVazio++;
					if (contadorNaoVazio == 1) {
						System.out.println("Clientes: ");
					}
					System.out.println(todos[contador]);
				} else {
					break;
				}
			}
		} catch (ObjetoInexistenteException e) {
			throw new ObjetoInexistenteException("O arquivo não existe no repositório.");
		}
		
		return todos;
	}

}
