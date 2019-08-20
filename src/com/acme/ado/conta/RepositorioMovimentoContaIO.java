package com.acme.ado.conta;

import java.io.IOException;
import java.util.Date;

import com.acme.ado.classesGerais.RepositorioDeRegistrosIO;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.ProblemaFisicoException;
import com.acme.rn.classesGerais.Registro;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.MovimentoConta;

public class RepositorioMovimentoContaIO implements MetodosRepositorioMovimentosConta{

	RepositorioDeRegistrosIO<Registro> rep = new RepositorioDeRegistrosIO<Registro>();
	
	@Override
	public boolean incluir(ContaMilhagem novaConta, MovimentoConta transacao, Date data)
			throws ObjetoExistenteException, ObjetoInexistenteException {
		boolean retorno = false;
		if (transacao != null && !transacao.equals(data)){
			try {
				rep.incluir(transacao);
			} catch (IOException e) {
				throw new ProblemaFisicoException("O arquivo n�o p�de ser criado.", e);
			}
			retorno = true;
		}
		else{
			System.out.println("J� possui uma movimenta��o de conta nesta data!");
		}
		return retorno;
	}

	@Override
	public MovimentoConta[] buscarTodos() throws ObjetoInexistenteException {
		int contador;
		int contadorNaoVazio;
		Registro[] todosId;
		MovimentoConta[] transacoes = null;
		try {
			todosId = rep.buscarTodos();
			transacoes = new MovimentoConta[todosId.length];
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
		} catch (ObjetoInexistenteException e) {
			throw new ObjetoInexistenteException("O arquivo n�o existe no reposit[�rio.");
		}
		
		return transacoes;
	}
	
}
