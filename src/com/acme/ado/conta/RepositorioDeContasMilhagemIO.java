package com.acme.ado.conta;

import java.io.IOException;

import com.acme.ado.classesGerais.RepositorioDeRegistrosIO;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.ProblemaFisicoException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.classesGerais.Registro;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.IdentificadorConta;

public class RepositorioDeContasMilhagemIO implements MetodosRepositorioContaMilhagem{
	
	RepositorioDeRegistrosIO<Registro> rep = new RepositorioDeRegistrosIO<Registro>();
	
	@Override
	public boolean adicionar(ContaMilhagem novaConta, IdentificadorConta identificador)
			throws ObjetoExistenteException, ObjetoInexistenteException {
		boolean retorno = false;
		if(novaConta != null && identificador != null){
			try {
				rep.incluir(novaConta);
			} catch (IOException e) {
				throw new ProblemaFisicoException("O arquivo não pôde ser criado.", e);
			}
			retorno = true;
		}
		return retorno;
	}

	@Override
	public boolean alterar(ContaMilhagem novaConta) throws QuebraDeRegraException, ObjetoInexistenteException {
		boolean retorno = false;
		try {
			if(rep.alterar(novaConta)){
				retorno = true;
			}
		} catch (IOException e) {
			throw new ProblemaFisicoException("O arquivo não pôde ser criado.", e);
		}
		return retorno;
	}

	@Override
	public boolean excluir(ContaMilhagem novaConta) throws ObjetoInexistenteException {
		boolean retorno = false;
		if(rep.excluir(novaConta.getChave())){
			retorno = true;
		}
		return retorno;
	}

	@Override
	public ContaMilhagem buscar(IdentificadorConta identificador) throws ObjetoInexistenteException {
		ContaMilhagem retorno = null;
		if(identificador != null){
			try {
				retorno = (ContaMilhagem) rep.buscarPorChave(Long.toString(identificador.getNumeroDaConta()));
			} catch (ProblemaFisicoException e) {
				throw new ProblemaFisicoException("O arquivo não pôde ser criado.", e);
			} catch (ClassNotFoundException e) {
				throw new ProblemaFisicoException("O arquivo possui objetos que não pertencem ao .class", e);
			}
		}
		return retorno;
	}

	@Override
	public ContaMilhagem[] buscarTodos() throws ObjetoInexistenteException {
		int contador;
		int contadorNaoVazio;
		Registro[] todosID;
		ContaMilhagem[] contas = null;
		try {
			todosID = rep.buscarTodos();
			contas = new ContaMilhagem[todosID.length];
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
		} catch (ObjetoInexistenteException e) {
			throw new ObjetoInexistenteException("O arquivo não existe no repositório.");
		}
		
		return contas;
	}

}
