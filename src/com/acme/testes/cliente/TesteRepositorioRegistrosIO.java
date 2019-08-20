package com.acme.testes.cliente;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import com.acme.ado.classesGerais.RepositorioDeRegistrosIO;
import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.ProblemaFisicoException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.classesGerais.Registro;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.IdentificadorConta;
import com.acme.rn.conta.MovimentoConta;
import com.acme.rn.conta.MovimentoContaCredito;

@SuppressWarnings("unused")
public class TesteRepositorioRegistrosIO {

	public static void main(String[] args) {
	
		RepositorioDeRegistrosIO<Registro> repositorioRegistrosIO = new RepositorioDeRegistrosIO<Registro>();
		
		try{
			Cliente numeroUm = new Cliente("Severino Portoes", new CPF("11122233344"), 67, 20000, 1);
			Cliente clienteContaMilhagem = new Cliente("Marcel Souza", new CPF("11122233344"), 18, 5000, 1);
			ContaMilhagem contaUm = new ContaMilhagem(new IdentificadorConta(11088285406l), clienteContaMilhagem);
			MovimentoContaCredito movimentoUm = new MovimentoContaCredito(contaUm, 200, numeroUm.getNome(), new Date());
			
			repositorioRegistrosIO.incluir(numeroUm);
			repositorioRegistrosIO.incluir(contaUm);
			repositorioRegistrosIO.incluir(movimentoUm);
			
			Cliente alterar = new Cliente("Marcos Vilela", new CPF("11122233344"), 18, 5000, 1);
			repositorioRegistrosIO.alterar(alterar);
			
			
			System.out.println(repositorioRegistrosIO.buscarPorChave(numeroUm.getChave()));
			System.out.println(repositorioRegistrosIO.buscarPorChave(contaUm.getChave()));
			System.out.println(repositorioRegistrosIO.buscarPorChave(movimentoUm.getChave()));
			
			
			//repositorioRegistrosIO.excluir(numeroUm.getChave());
			
			
			System.out.println(Arrays.toString(repositorioRegistrosIO.buscarTodos()));
		} catch (ObjetoExistenteException e){
			System.out.println(e.getMessage());
		} catch (ObjetoInexistenteException e1){
			System.out.println(e1.getMessage());
		} catch (QuebraDeRegraException e2){
			System.out.println(e2.getMessage());
		} catch (ProblemaFisicoException e3){
			System.out.println(e3.getMessage());
		} catch (AtributoInvalidoException e4) {
			System.out.println(e4.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}


