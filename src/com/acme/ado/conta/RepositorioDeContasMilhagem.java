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
	 * Vari�vel gen�rica do tipo Reposit�rio de Identific�veis, usada por toda a Classe.
	 *
	 */
	private RepositorioDeRegistros rep;
	
	/**
	 * Construtor da classe, inicializa a vari�vel do tipo Reposit�rio de Identificaveis
	 */
	public RepositorioDeContasMilhagem() {
		rep = new RepositorioDeRegistros();
		
	}

	/**
	 * M�todo booleano adicionar(), recebe uma Conta Milhagem e um Identificador. Caso nenhum dos dois par�metros seja nulo, o m�todo usa o m�todo incluir() do Reposit�rio de Identific�veis. 
	 * Caso seja poss�vel concluir a opera��o, retorna TRUE. Caso contr�rio, retorna FALSE.
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
	 * M�todo booleano alterar(), recebe como par�metro um objeto do tipo ContaMilhagem. Chama o alterar do Reposit�rio de Identific�veis e, caso a opera��o seja conclu�da, retorna TRUE.
	 * Caso contr�rio, retorna FALSE.
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
	 * M�todo booleano excluir(), recebe um objeto do tipo ContaMilhagem. Chama o excluir do Reposit�rio de Identific�veis e, caso a opera��o seja conclu�da, retorna TRUE. Caso contr�rio, retorna FALSE.
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
	 * M�todo buscar(), recebe como par�metro um objeto do tipo IdentificadorConta. Retorna um Cliente associado �quele identificador. Caso o identificador n�o seja nulo, o Cliente de retorno assume o valor do Cliente
	 * retornado pelo buscarPorChave() do Reposit�ro de Identific�veis.
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
	 * M�todo buscarTodos(), n�o recebe par�metros.
	 * Retorna um Array com todas os Identific�veis referentes �s Contas Milhagem j� inclu�dos, e print�-los na tela.
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
	 * M�todo toString(), n�o recebe par�metros.
	 * Transaforma todos os dados da classe em uma String e retorna essa String.
	 */
	public String toString() {
		return rep.toString();
	}

}
