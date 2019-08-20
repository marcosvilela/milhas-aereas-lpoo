package com.acme.ado.cliente;

import com.acme.ado.classesGerais.RepositorioDeRegistros;
import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.classesGerais.*;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;

public class RepositorioDeClientes implements MetodosRepositorioCliente {

	/**
	 * Variável genérica do Repositório de Identificáveis, usada para todas as funcionalidades do repositório de Clientes
	 */
	private RepositorioDeRegistros rep;
	

	/**
	 * Contrutor da classe, inicializa um novo Repositório de Identificáveis
	 */
	public RepositorioDeClientes() {
		rep = new RepositorioDeRegistros();
		
	}

	/**
	 * Método adicionar(), recebe como parâmetro um objeto do tipo Cliente e possui retorno booleano. Chama o incluir do Repositório de Identificáveis
	 * passando o Cliente recebido como parâmetro. Caso a operação seja concluída, o método retorna TRUE. Caso contrário, FALSE.
	 * 
	 * @param newClient
	 * @return boolean
	 * @throws ObjetoInexistenteException 
	 */
	public boolean adicionar(Cliente newClient) throws ObjetoExistenteException, ObjetoInexistenteException {
		boolean retorno = false;
		if(rep.incluir(newClient)){
			retorno = true;
		}
		else{
			throw new ObjetoExistenteException("Cliente já existente no repositório!");
		}
		return retorno;
	}

	/**
	 * Método alterar(), recebe como parâmetro um objeto do tipo Cliente e possui um retorno booleano. Chama o alterar do Repositório de Identificáveis
	 * passando como parâmetro o Cliente recebido. Caso a operação seja concluída, o método retorna TRUE. Caso contrário, retorna FALSE.
	 * @param c1
	 * @return boolean
	 * @throws QuebraDeRegraException 
	 */
	public boolean alterar(Cliente c1) throws QuebraDeRegraException, AtributoInvalidoException, ObjetoInexistenteException {
		boolean retorno = false;
		if(rep.alterar(c1)){
			retorno = true;
		}else{
			throw new ObjetoInexistenteException("O cliente não existe no repositório!");
		}

		return retorno;
	}

	/**
	 * Método excluir(), recebe como parâmetro um objeto do tipo Cliente e possui um retorno booleano. Chama o excluir do Repositório de Identificáveis
	 * passando como parâmetro o Cliente recebido. Caso a operação seja concluída, o método retorna TRUE. Caso contrário, retorna FALSE.
	 * @param newClient
	 * @return boolean
	 */
	public boolean excluir(Cliente newClient) throws ObjetoInexistenteException{
		boolean retorno = false;
		if(rep.excluir(newClient.getChave())){
			retorno = true;
		}else{
			throw new ObjetoInexistenteException("O cliente não existe no repositório!");
		}
		return retorno;
	}

	/**
	 * Método buscar(), recebe como parâmetro um objeto do tipo CPF e retorna um objeto do tipo Cliente associado àquele CPF. Caso o CPF passado
	 * como parâmetro seja não-nulo, o Cliente de retorno recebe o cliente retornado pelo BuscarPorChave do Repositório de Identificáveis. 
	 * @param cpf
	 * @return Cliente
	 */
	public Cliente buscar(CPF cpf) throws ObjetoInexistenteException {
		Cliente retorno = null;
		if(cpf != null){
			retorno = (Cliente) rep.buscarPorChave(cpf.getCPF());
		}else{
			throw new ObjetoInexistenteException("O cliente não existe no repositório!");
		}
		return retorno;
	}

	/**
	 * Método buscarTodos(), não recebe parâmetros. Retorna um array de Clientes com todos os Clientes já incluídos anteriormente, e os printa na tela.
	 * 
	 * @return Cliente[]
	 */
	public Cliente[] buscarTodos() {
		int contador;
		int contadorNaoVazio;
		Registro[] todosID = rep.buscarTodos();
		Cliente[] todos = new Cliente[todosID.length];
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
		return todos;

	}

	/**
	 * Método toString(), não recebe parâmetros.
	 * Transforma todos os dados da classe em uma String e retorna essa String, usando o toString() do Repositório de Identificáveis
	 */
	public String toString() {
		return rep.toString();
	}
}
