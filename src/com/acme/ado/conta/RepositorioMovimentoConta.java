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
	 * Variável genérica do tipo Repositório de Identificáveis, usada por toda a Classe.
	 */
	private RepositorioDeRegistros rep;

	/**
	 * Construtor da classe, inicializa a variável do tipo Repositório de Identificaveis
	 */
	public RepositorioMovimentoConta() {
		rep = new RepositorioDeRegistros();
	}

	/**
	 * Método booleano incluir(), recebe como parâmetro um objeto do tipo ContaMilhagem, um objeto do tipo MovimentoConta e um objeto do tipo Date.
	 * Caso nenhum dos objetos passados como parâmetro seja nulo, o método chama o incluir() do Repositório de Identificáveis. Caso a operação seja concluída, retorna TRUE. 
	 * Caso contrário, retorna FALSE.
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
			System.out.println("Já possui uma movimentação de conta nesta data!");
		}
		return retorno;
	}

	/**
	 * Método buscarTodos(), não recebe parâmetros. Retorna um Array com todas os Identificáveis referentes aos MovimentosConta já incluídos, e os printa na tela.
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
					System.out.println("--- Transações ---");
				}
				System.out.println(transacoes[contador] + "\n");
			} else {
				break;
			}
		}
		return transacoes;
	}

	/**
	 * Método toString(), não recebe parâmetros e retorna uma String com os dados da classe.
	 */
	public String toString() {
		return rep.toString();
	}

}