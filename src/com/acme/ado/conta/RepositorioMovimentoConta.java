package com.acme.ado.conta;

import java.util.Date;

import com.acme.ado.classesGerais.RepositorioDeRegistros;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.rn.classesGerais.Registro;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.MovimentoConta;

public class RepositorioMovimentoConta implements MetodosRepositorioMovimentosConta {

	/**
	 * Vari�vel gen�rica do tipo Reposit�rio de Identific�veis, usada por toda a Classe.
	 */
	private RepositorioDeRegistros rep;

	/**
	 * Construtor da classe, inicializa a vari�vel do tipo Reposit�rio de Identificaveis
	 */
	public RepositorioMovimentoConta() {
		rep = new RepositorioDeRegistros();
	}

	/**
	 * M�todo booleano incluir(), recebe como par�metro um objeto do tipo ContaMilhagem, um objeto do tipo MovimentoConta e um objeto do tipo Date.
	 * Caso nenhum dos objetos passados como par�metro seja nulo, o m�todo chama o incluir() do Reposit�rio de Identific�veis. Caso a opera��o seja conclu�da, retorna TRUE. 
	 * Caso contr�rio, retorna FALSE.
	 * @param novaConta
	 * @param transacao
	 * @param data
	 * @return boolean
	 * @throws ObjetoInexistenteException 
	 * @throws ObjetoExistenteException 
	 */
	public boolean incluir(ContaMilhagem novaConta, MovimentoConta transacao, Date data) throws ObjetoExistenteException, ObjetoInexistenteException {
		boolean retorno = false;
		if (transacao != null && !transacao.equals(data)){
			rep.incluir(transacao);
			retorno = true;
		}
		else{
			System.out.println("J� possui uma movimenta��o de conta nesta data!");
		}
		return retorno;
	}

	/**
	 * M�todo buscarTodos(), n�o recebe par�metros. Retorna um Array com todas os Identific�veis referentes aos MovimentosConta j� inclu�dos, e os printa na tela.
	 * @return
	 */
	public MovimentoConta[] buscarTodos() {
		int contador;
		int contadorNaoVazio;
		Registro[] todosId = rep.buscarTodos();
		MovimentoConta[] transacoes = new MovimentoConta[todosId.length];
		for (contador = 0, contadorNaoVazio = 0; contador < transacoes.length; contador++) {
			if (todosId[contador] != null && todosId[contador] instanceof MovimentoConta) {
				transacoes[contador] = (MovimentoConta) todosId[contador];
				contadorNaoVazio++;
				if (contadorNaoVazio == 1) {
					System.out.println("--- Transa��es ---");
				}
				System.out.println(transacoes[contador] + "\n");
			} else {
				break;
			}
		}
		return transacoes;
	}

	/**
	 * M�todo toString(), n�o recebe par�metros e retorna uma String com os dados da classe.
	 */
	public String toString() {
		return rep.toString();
	}

}