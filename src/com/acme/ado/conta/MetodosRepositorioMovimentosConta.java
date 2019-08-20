package com.acme.ado.conta;

import java.util.Date;

import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.MovimentoConta;

public interface MetodosRepositorioMovimentosConta {

	public boolean incluir(ContaMilhagem novaConta, MovimentoConta transacao, Date data) throws ObjetoExistenteException, ObjetoInexistenteException ;
	public MovimentoConta[] buscarTodos() throws ObjetoInexistenteException ;
	
}
