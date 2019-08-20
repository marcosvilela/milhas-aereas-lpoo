package com.acme.testes.conta;

import com.acme.ado.conta.RepositorioDeContasMilhagemIO;
import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.IdentificadorConta;

public class TesteRepositorioDeContasMilhagemIO {

	public static void main(String[] args) {
		
		RepositorioDeContasMilhagemIO rep = new RepositorioDeContasMilhagemIO();
		
		try{	
			long numeroDaConta = 12345678900l;
			IdentificadorConta id = new IdentificadorConta(numeroDaConta);
			ContaMilhagem contaAdicionar = new ContaMilhagem(id, new Cliente("José Augusto", new CPF("12345678900"), 14, 12.0, 2));
			ContaMilhagem conta = new ContaMilhagem(new IdentificadorConta(11122233344l), new Cliente("Marcos Vilela", new CPF("11122233344"), 17, 2000, 1));
			

			// Adicionando uma conta com um certo número
			rep.adicionar(contaAdicionar, contaAdicionar.getIdentificador());
			rep.adicionar(conta, conta.getIdentificador());		

			// Buscar uma conta pelo seu número
			if(rep.buscar(id) == null){
				System.out.println("Não foi possível encontrar o Cliente desejado!");
			} else {
				System.out.println("Conta de número " + id.getNumeroDaConta() + " encontrado com sucesso!\n");
				System.out.println(rep.buscar(id));
			}

			// TENTANDO O BUSCAR TODOS.
			rep.buscarTodos();
			
			
			}catch(AtributoInvalidoException e1){
				System.out.println(e1.getMessage());
			}catch (ObjetoInexistenteException e2) {
				System.out.println(e2.getMessage());
			}catch (ObjetoExistenteException e3) {
				System.out.println(e3.getMessage());
			}
		}

}
