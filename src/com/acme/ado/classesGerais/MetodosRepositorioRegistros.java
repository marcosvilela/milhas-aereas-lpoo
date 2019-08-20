package com.acme.ado.classesGerais;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.ProblemaFisicoException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.classesGerais.Registro;

public interface MetodosRepositorioRegistros {
	public boolean incluir(Registro novo) throws ObjetoExistenteException, ObjetoInexistenteException, FileNotFoundException, IOException;
	public boolean alterar(Registro alterar) throws QuebraDeRegraException, ObjetoInexistenteException, FileNotFoundException, IOException ;
	public Registro buscarPorChave(String chave) throws ObjetoInexistenteException, ProblemaFisicoException, ClassNotFoundException;
	public boolean excluir(String chave) throws ObjetoInexistenteException;
	public Registro[] buscarTodos() throws ObjetoInexistenteException;
	
}
