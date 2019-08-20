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
	 * Vari�vel gen�rica do Reposit�rio de Identific�veis, usada para todas as funcionalidades do reposit�rio de Clientes
	 */
	private RepositorioDeRegistros rep;
	

	/**
	 * Contrutor da classe, inicializa um novo Reposit�rio de Identific�veis
	 */
	public RepositorioDeClientes() {
		rep = new RepositorioDeRegistros();
		
	}

	/**
	 * M�todo adicionar(), recebe como par�metro um objeto do tipo Cliente e possui retorno booleano. Chama o incluir do Reposit�rio de Identific�veis
	 * passando o Cliente recebido como par�metro. Caso a opera��o seja conclu�da, o m�todo retorna TRUE. Caso contr�rio, FALSE.
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
			throw new ObjetoExistenteException("Cliente j� existente no reposit�rio!");
		}
		return retorno;
	}

	/**
	 * M�todo alterar(), recebe como par�metro um objeto do tipo Cliente e possui um retorno booleano. Chama o alterar do Reposit�rio de Identific�veis
	 * passando como par�metro o Cliente recebido. Caso a opera��o seja conclu�da, o m�todo retorna TRUE. Caso contr�rio, retorna FALSE.
	 * @param c1
	 * @return boolean
	 * @throws QuebraDeRegraException 
	 */
	public boolean alterar(Cliente c1) throws QuebraDeRegraException, AtributoInvalidoException, ObjetoInexistenteException {
		boolean retorno = false;
		if(rep.alterar(c1)){
			retorno = true;
		}else{
			throw new ObjetoInexistenteException("O cliente n�o existe no reposit�rio!");
		}

		return retorno;
	}

	/**
	 * M�todo excluir(), recebe como par�metro um objeto do tipo Cliente e possui um retorno booleano. Chama o excluir do Reposit�rio de Identific�veis
	 * passando como par�metro o Cliente recebido. Caso a opera��o seja conclu�da, o m�todo retorna TRUE. Caso contr�rio, retorna FALSE.
	 * @param newClient
	 * @return boolean
	 */
	public boolean excluir(Cliente newClient) throws ObjetoInexistenteException{
		boolean retorno = false;
		if(rep.excluir(newClient.getChave())){
			retorno = true;
		}else{
			throw new ObjetoInexistenteException("O cliente n�o existe no reposit�rio!");
		}
		return retorno;
	}

	/**
	 * M�todo buscar(), recebe como par�metro um objeto do tipo CPF e retorna um objeto do tipo Cliente associado �quele CPF. Caso o CPF passado
	 * como par�metro seja n�o-nulo, o Cliente de retorno recebe o cliente retornado pelo BuscarPorChave do Reposit�rio de Identific�veis. 
	 * @param cpf
	 * @return Cliente
	 */
	public Cliente buscar(CPF cpf) throws ObjetoInexistenteException {
		Cliente retorno = null;
		if(cpf != null){
			retorno = (Cliente) rep.buscarPorChave(cpf.getCPF());
		}else{
			throw new ObjetoInexistenteException("O cliente n�o existe no reposit�rio!");
		}
		return retorno;
	}

	/**
	 * M�todo buscarTodos(), n�o recebe par�metros. Retorna um array de Clientes com todos os Clientes j� inclu�dos anteriormente, e os printa na tela.
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
	 * M�todo toString(), n�o recebe par�metros.
	 * Transforma todos os dados da classe em uma String e retorna essa String, usando o toString() do Reposit�rio de Identific�veis
	 */
	public String toString() {
		return rep.toString();
	}
}
