package com.acme.ado.conta;

import com.acme.ado.classesGerais.RepositorioDeRegistros;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.classesGerais.Registro;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.IdentificadorConta;

public class RepositorioDeContasMilhagem implements MetodosRepositorioContaMilhagem{

	/**
	 * Variável genérica do tipo Repositório de Identificáveis, usada por toda a Classe.
	 *
	 */
	private RepositorioDeRegistros rep;
	
	/**
	 * Construtor da classe, inicializa a variável do tipo Repositório de Identificaveis
	 */
	public RepositorioDeContasMilhagem() {
		rep = new RepositorioDeRegistros();
		
	}

	/**
	 * Método booleano adicionar(), recebe uma Conta Milhagem e um Identificador. Caso nenhum dos dois parâmetros seja nulo, o método usa o método incluir() do Repositório de Identificáveis. 
	 * Caso seja possível concluir a operação, retorna TRUE. Caso contrário, retorna FALSE.
	 * @param novaConta
	 * @param identificador
	 * @return boolean
	 * @throws ObjetoInexistenteException 
	 * @throws ObjetoExistenteException 
	 */
	public boolean adicionar(ContaMilhagem novaConta, IdentificadorConta identificador) throws ObjetoExistenteException, ObjetoInexistenteException { 
		boolean retorno = false;
		if(novaConta != null && identificador != null){
			rep.incluir(novaConta);
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Método booleano alterar(), recebe como parâmetro um objeto do tipo ContaMilhagem. Chama o alterar do Repositório de Identificáveis e, caso a operação seja concluída, retorna TRUE.
	 * Caso contrário, retorna FALSE.
	 *
	 * @param novaConta
	 * @return boolean
	 * @throws QuebraDeRegraException 
	 * @throws ObjetoInexistenteException 
	 */
	public boolean alterar(ContaMilhagem novaConta) throws QuebraDeRegraException, ObjetoInexistenteException {
		boolean retorno = false;
		if(rep.alterar(novaConta)){
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Método booleano excluir(), recebe um objeto do tipo ContaMilhagem. Chama o excluir do Repositório de Identificáveis e, caso a operação seja concluída, retorna TRUE. Caso contrário, retorna FALSE.
	 * @param novaConta
	 * @return boolean
	 * @throws ObjetoInexistenteException 
	 */
	public boolean excluir(ContaMilhagem novaConta) throws ObjetoInexistenteException {
		boolean retorno = false;
		if(rep.excluir(novaConta.getChave())){
			retorno = true;
		}
		return retorno;
	}

	/**
	 * Método buscar(), recebe como parâmetro um objeto do tipo IdentificadorConta. Retorna um Cliente associado àquele identificador. Caso o identificador não seja nulo, o Cliente de retorno assume o valor do Cliente
	 * retornado pelo buscarPorChave() do Repositóro de Identificáveis.
	 * 
	 * @param identificador
	 * @return Cliente
	 * @throws ObjetoInexistenteException 
	 */
	public ContaMilhagem buscar(IdentificadorConta identificador) throws ObjetoInexistenteException {
		ContaMilhagem retorno = null;
		if(identificador != null){
			retorno = (ContaMilhagem) rep.buscarPorChave(Long.toString(identificador.getNumeroDaConta()));
		}
		return retorno;
	}

	/**
	 * Método buscarTodos(), não recebe parâmetros.
	 * Retorna um Array com todas os Identificáveis referentes às Contas Milhagem já incluídos, e printá-los na tela.
	 * @return
	 */
	public ContaMilhagem[] buscarTodos() {
		int contador;
		int contadorNaoVazio;
		Registro[] todosID = rep.buscarTodos();
		ContaMilhagem[] contas = new ContaMilhagem[todosID.length];
		for (contador = 0, contadorNaoVazio = 0; contador < contas.length; contador++) {
			if (todosID[contador] != null && todosID[contador] instanceof ContaMilhagem) {
				contas[contador] = (ContaMilhagem) todosID[contador];
				contadorNaoVazio++;
				if (contadorNaoVazio == 1) {
					System.out.println("--- Contas milhagem --- ");
				}
				System.out.println(contas[contador] + "\n");
			} else
				break;
		}
		return contas;
	}

	/**
	 * Método toString(), não recebe parâmetros.
	 * Transaforma todos os dados da classe em uma String e retorna essa String.
	 */
	public String toString() {
		return rep.toString();
	}

}
