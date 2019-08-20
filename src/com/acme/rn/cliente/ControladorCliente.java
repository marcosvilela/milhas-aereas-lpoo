package com.acme.rn.cliente;

import com.acme.ado.cliente.RepositorioDeClientesIO;
import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.conta.ControladorContaMilhagem;

public class ControladorCliente {

	private static RepositorioDeClientesIO repClientes = new RepositorioDeClientesIO(); //Instancia de um Reposit�rioDeClientes em uma vari�vel do tipo Reposit�rioDeClientes.

	/**
	 * M�todo est�tico incluir(), recebe como par�metro um objeto do tipo Cliente.
	 * Verifica se o objeto passado como par�metro � nulo. Caso seja, exibe mensagem pertinente no console. Caso n�o seja, Cria uma ContaMihagem com os dados desse Cliente,
	 * armazena ele no reposit�rio de clientes e armazena a ContaMilhagem do cliente no reposit�rio de contas milhagem.
	 * @param novoCliente
	 * @throws AtributoInvalidoException 
	 * @throws ObjetoInexistenteException 
	 * @throws ObjetoExistenteException 
	 * @throws NumberFormatException 
	 */
	public static void incluir(Cliente novoCliente) throws AtributoInvalidoException, ObjetoExistenteException, ObjetoInexistenteException {
		try{
			novoCliente.validar();
		}catch(AtributoInvalidoException erro){
			System.out.println(erro.getMessage());
		}
		if(buscar(novoCliente.getCpf()) == null){
			repClientes.adicionar(novoCliente);
			ControladorContaMilhagem.inserir(novoCliente.getCpf());
			
		} 
	}

	/**
	 * M�todo est�tico alterar(), recebe como par�metro um objeto do tipo Cliente.
	 * Verifica se o objeto passado como par�metro � nulo. Caso seja, exibe mensagem pertinente no console. Caso n�o seja, busca um cliente armazenado no reposit�rio atrav�s
	 * do m�todo buscar do reposit�rio de clientes e caso o m�todo retorne um cliente, ele altera essa cliente atrav�s do m�todo alterar do reposit�rio de clientes.
	 * @param newClient
	 * @throws QuebraDeRegraException 
	 * @throws ObjetoInexistenteException 
	 */
	public static void alterar(Cliente newClient) throws AtributoInvalidoException, QuebraDeRegraException, ObjetoInexistenteException {
		try{
			newClient.validar();
		}catch(AtributoInvalidoException erro){
			System.out.println(erro.getMessage());
		}
		if(repClientes.buscar(newClient.getCpf()) != null){
			repClientes.alterar(newClient);
			RepositorioDeClientesIO rep = new RepositorioDeClientesIO();
			rep.alterar(newClient);
		}
		
	}

	/**
	 * M�todo est�tico excluir(), recebe como par�metro um objeto do tipo CPF.
	 * Verifica se o objeto passado como par�metro � nulo. Caso seja, exibe mensagem pertinente no console. Caso n�o seja, utiliza o m�todo buscar do reposit�rio de cliente
	 * para buscar um Cliente com cpf igual ao passado como par�metro. Se a busca der retorno. Os dados desse cliente s�o passados para um objeto gen�rico do tipo Cliente
	 * que simboliza o Cliente do reposit�rio que ent�o � excluido do reposit�rio atrav�s do m�todo excluir do reposit�rio de clientes.
	 * @param cpf
	 * @throws ObjetoInexistenteException 
	 */
	public static void excluir(CPF cpf) throws ObjetoInexistenteException, AtributoInvalidoException {
		Cliente retorno = null; 
		if (cpf == null) {
			System.out.println("O CPF � nulo!\n"); 
		} else {
			retorno = repClientes.buscar(cpf);
				
					retorno.validar();
					repClientes.excluir(retorno);
					
					RepositorioDeClientesIO rep = new RepositorioDeClientesIO();
					rep.excluir(retorno);
		}
	}

	/**
	 * M�todo est�tico buscar(), recebe como par�metro um objeto do tipo CPF.
	 * Verifica se o objeto passado como par�metro � nulo. Caso seja, exibe mensagem pertinente no console. Caso n�o seja, faz uma busca no reposit�rio de clientes com o cpf
	 * passado como par�metro, caso haja retorno, os dados do cliente retornado da busca � passado como par�metro para um objeto gen�rico do tipo Cliente que simboliza o cliente
	 * do reposit�rio. O m�todo retorna esse cliente gen�rico.
	 * @param cpf
	 * @return
	 * @throws ObjetoInexistenteException 
	 */
	public static Cliente buscar(CPF cpf) throws ObjetoInexistenteException {
		Cliente retorno = null;
		if (cpf == null) {
			System.out.println("O cpf � nulo!\n");
		} else {
			if (repClientes.buscar(cpf) != null) {
				retorno = repClientes.buscar(cpf);
				
			}
		}
		return retorno;
	}

	/**
	 * M�todo est�tico buscarTodos(), n�o recebe par�metros.
	 * Retorna um Array do tipo Cliente[] atrav�s do m�todo buscarTodos() do reposit�rio de clientes.
	 * @return
	 * @throws ObjetoInexistenteException 
	 */
	public static Cliente[] buscarTodos() throws ObjetoInexistenteException {
		Cliente[] retorno = null;
		try{
			RepositorioDeClientesIO rep = new RepositorioDeClientesIO();
			retorno = rep.buscarTodos();
		} catch(ObjetoInexistenteException e){
			throw new ObjetoInexistenteException("O arquivo n�o existe no reposit�rio.");
		}
		return retorno;
	}

}
