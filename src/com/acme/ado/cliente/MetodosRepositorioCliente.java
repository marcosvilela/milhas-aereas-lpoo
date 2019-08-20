package com.acme.ado.cliente;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;

public interface MetodosRepositorioCliente {

	public boolean adicionar(Cliente newClient) throws ObjetoExistenteException, ObjetoInexistenteException ;
	public boolean alterar(Cliente c1) throws AtributoInvalidoException, QuebraDeRegraException, ObjetoInexistenteException;
	public boolean excluir(Cliente newClient) throws ObjetoInexistenteException;
	public Cliente buscar(CPF cpf) throws ObjetoInexistenteException;
	public Cliente[] buscarTodos() throws ObjetoInexistenteException;
}
