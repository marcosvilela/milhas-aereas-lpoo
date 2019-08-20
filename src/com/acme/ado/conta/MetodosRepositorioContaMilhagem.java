package com.acme.ado.conta;

import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.IdentificadorConta;

public interface MetodosRepositorioContaMilhagem {

	public boolean adicionar(ContaMilhagem novaConta, IdentificadorConta identificador) throws ObjetoExistenteException, ObjetoInexistenteException ;
	public boolean alterar(ContaMilhagem novaConta) throws QuebraDeRegraException, ObjetoInexistenteException;
	public boolean excluir(ContaMilhagem novaConta) throws ObjetoInexistenteException;
	public ContaMilhagem buscar(IdentificadorConta identificador) throws ObjetoInexistenteException;
	public ContaMilhagem[] buscarTodos() throws ObjetoInexistenteException ;
	
}
