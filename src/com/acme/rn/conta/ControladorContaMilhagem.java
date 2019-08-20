package com.acme.rn.conta;

import java.util.Date;

import com.acme.ado.cliente.RepositorioDeClientesIO;
import com.acme.ado.conta.MetodosRepositorioContaMilhagem;
import com.acme.ado.conta.RepositorioDeContasMilhagem;
import com.acme.ado.conta.RepositorioDeContasMilhagemIO;
import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.cliente.*;



public class ControladorContaMilhagem {
	/**
	 * Repositorio est�tico criado para armazenar as Contas criadas
	 */
	private static MetodosRepositorioContaMilhagem repContasMilhagem = new RepositorioDeContasMilhagem(); 

	
	/**
	 * M�todo est�tico inserir(), cria e insere uma Conta associada a um Cliente com um CPF n�o-nulo passado como par�metro. Se o Cliente associado � tal Conta for encontrado, e n�o for nulo
	 *  a ContaMilhagem � criada tendo seu identificador igual ao CPF do Cliente. Ao fim, a Conta � adicionada a seu respectivo reposit�rio.
	 * @param cpf
	 * @throws AtributoInvalidoException 
	 * @throws ObjetoInexistenteException 
	 * @throws ObjetoExistenteException 
	 * @throws NumberFormatException 
	 */
	public static void inserir(CPF cpf) throws AtributoInvalidoException, ObjetoInexistenteException, ObjetoExistenteException { 		
		if (cpf == null) {		
			System.out.println("O cpf � nulo!\n");		
		} else {
			RepositorioDeClientesIO rep = new RepositorioDeClientesIO();
			Cliente retorno = rep.buscar(cpf);
			if(retorno != null){
				try{
					retorno.validar();
				}catch(AtributoInvalidoException e){
					System.out.println(e.getMessage());
				}
					ContaMilhagem novaConta = new ContaMilhagem(new IdentificadorConta(Long.parseLong(cpf.getCPF())), retorno);
					repContasMilhagem.adicionar(novaConta, novaConta.getIdentificador());
			}
			
		}
	}

	/**
	 * M�todo est�tico creditar(), adiciona um valor passado como par�metro a uma Conta com um Identificador tamb�m passado como par�metro, utilizando o m�todo Creditar da Classe ContaMilhagem.
	 * Ao fim, atualiza a Conta associada excluindo-a e adicionando-a novamente. Gera um nome extrato e adiciona a transa��o em um reposit�rio de MovimentosConta.
	 * @param idConta
	 * @param valor
	 * @throws QuebraDeRegraException 
	 * @throws ObjetoInexistenteException 
	 * @throws ObjetoExistenteException 
	 */
	public static void creditar(IdentificadorConta idConta, int valor) throws QuebraDeRegraException, ObjetoInexistenteException, ObjetoExistenteException {							
		if (idConta == null) {			
			System.out.println("O identificador da conta � nulo!\n");		
		} else {	
			ContaMilhagem conta = repContasMilhagem.buscar(idConta);
			try{
				conta.validar();
			}catch(AtributoInvalidoException erro){
				System.out.println(erro.getMessage());
			}
			conta.creditar(valor);
			RepositorioDeContasMilhagemIO rep = new RepositorioDeContasMilhagemIO();
			rep.alterar(conta);
			
			String aux = null;
			if(conta.getNewClient().getSexo() == 1){
				aux = "MR.";
			}else{
				aux = "MRS.";
			}
			MovimentoContaCredito transacao = new MovimentoContaCredito(conta, valor, conta.getNewClient().getUltimoNome() + ", " + conta.getNewClient().getPrimeiroNome() + " " + aux, new Date());
			ControladorMovimentoConta.inserir(transacao);
			}
	}

	/**
	 * M�todo est�tico debitar(), que recebe um identificador Conta. Cria uma Conta associada a esse identificador e debita um valor passado como par�metro no saldo dessa Conta. Ao final,
	 * gera um nome de Extrato e atualiza a Conta, excluindo-a e adicionando-a novamente ao reposit�rio. Cria tamb�m uma nova Movimenta��o na Conta e a insere em seu respectivo reposit�rio.
	 * @param idConta
	 * @param valor
	 * @throws QuebraDeRegraException 
	 * @throws ObjetoInexistenteException 
	 * @throws ObjetoExistenteException 
	 */
	public static void debitar(IdentificadorConta idConta, int valor) throws QuebraDeRegraException, ObjetoInexistenteException, ObjetoExistenteException {				
		if (idConta == null) {		
			System.out.println("O identificador da conta � nulo!\n");		
		} else {		
			ContaMilhagem conta = repContasMilhagem.buscar(idConta);
			try{
				conta.validar();
			}catch(AtributoInvalidoException erro){
				erro.printStackTrace();
				System.out.println(erro.getMessage());
			}
			conta.debitar(valor);		
			RepositorioDeContasMilhagemIO rep = new RepositorioDeContasMilhagemIO();
			rep.alterar(conta);
			
			String tratamento;		
			String retorno = null;

			if (conta.getNewClient().getSexo() == 1) {
				tratamento = "MR";
				retorno = conta.getNewClient().getUltimoNome() + ", " + conta.getNewClient().getPrimeiroNome() + " "
						+ tratamento + ".";
			} else if (conta.getNewClient().getSexo() == 2) {
				tratamento = "MRS";
				retorno = conta.getNewClient().getUltimoNome() + ", " + conta.getNewClient().getPrimeiroNome() + " "
						+ tratamento + ".";
			} else {
				System.out.println("Op��o escolhida inv�lida!");
			}
			MovimentoContaDebito novaTransacao = new MovimentoContaDebito(conta, valor, retorno, new Date());		
			ControladorMovimentoConta.inserir(novaTransacao);		
		}
	}

	/**
	 * M�todo est�tico transferir(), recebe dois identificadores n�o-nulos e um valor a ser transferido. Associa uma ContaMilhagem a cada Identificador e faz a transfer�ncia:
	 * Debita da Conta de Origem e Credita na Conta de Destino. Ao fim da opera��o, ambas as Contas s�o atualizadas e um Extrato � gerado. A movimenta��o � ent�o criada e adicionada
	 * ao reposit�rio de MovimentosConta.
	 * @param idContaOrigem
	 * @param idContaDestino
	 * @param valor
	 * @throws QuebraDeRegraException 
	 * @throws ObjetoInexistenteException 
	 * @throws ObjetoExistenteException 
	 */
	public static void transferir(IdentificadorConta idContaOrigem, IdentificadorConta idContaDestino, int valor) throws QuebraDeRegraException, ObjetoInexistenteException, ObjetoExistenteException {		
		if (idContaOrigem == null || idContaDestino == null) {		
			System.out.println("Pelo menos um dos identificadores da conta � nulo!\n");		
		} else {		
			ContaMilhagem contaOrigem = repContasMilhagem.buscar(idContaOrigem);
			ContaMilhagem contaDestino = repContasMilhagem.buscar(idContaDestino);
			try{
				contaDestino.validar();
				contaOrigem.validar();
			}catch(AtributoInvalidoException erro){
				System.out.println(erro.getMessage());
			}
			String tratamento1 = null;
			String retorno1 = null;

			if (contaOrigem.getNewClient().getSexo() == 1) {
				tratamento1 = "MR";
				retorno1 = contaOrigem.getNewClient().getUltimoNome() + ", "
						+ contaOrigem.getNewClient().getPrimeiroNome() + " " + tratamento1 + ".";
			} else if (contaOrigem.getNewClient().getSexo() == 2) {
				tratamento1 = "MRS";
				retorno1 = contaOrigem.getNewClient().getUltimoNome() + ", "
						+ contaOrigem.getNewClient().getPrimeiroNome() + " " + tratamento1 + ".";
			} else {
				System.out.println("Op��o escolhida inv�lida!");
			}

			String tratamento2 = null;
			String retorno2 = null;

			if (contaOrigem.getNewClient().getSexo() == 1) {
				tratamento2 = "MR";
				retorno2 = contaOrigem.getNewClient().getUltimoNome() + ", "
						+ contaOrigem.getNewClient().getPrimeiroNome() + " " + tratamento2 + ".";
			} else if (contaOrigem.getNewClient().getSexo() == 2) {
				tratamento2 = "MRS";
				retorno2 = contaOrigem.getNewClient().getUltimoNome() + ", "
						+ contaOrigem.getNewClient().getPrimeiroNome() + " " + tratamento2 + ".";
			} else {
				System.out.println("Op��o escolhida inv�lida!");
			}

			String extratoTransferencia = retorno2 + " para " + retorno1;

			MovimentoContaTransferencia novaTransacao = new MovimentoContaTransferencia(contaOrigem, contaDestino, valor, extratoTransferencia, new Date());
			ControladorMovimentoConta.inserir(novaTransacao);			
		}
	}

	/**
	 * M�todo est�tico buscar(), recebe um identificador Conta e busca uma ContaMilhagem associado �quele Identificador, retornando-a.
	 * @param idconta
	 * @return
	 * @throws ObjetoInexistenteException 
	 */
	public static ContaMilhagem buscar(IdentificadorConta id, int valor) throws ObjetoInexistenteException{
		ContaMilhagem retorno = null;
		if(id == null){
			System.out.println("Identificador conta � nulo!");
		}
		else{
			RepositorioDeContasMilhagemIO rep = new RepositorioDeContasMilhagemIO();
			rep.buscar(id);
			retorno = repContasMilhagem.buscar(id);
		}
		return retorno;
	}
 
	/**
	 * M�todo est�tico buscarTodos(), retorna um Array com todas as ContasMilhagem j� inseridas no Reposit�rio.
	 * @return
	 * @throws ObjetoInexistenteException 
	 */
	public static ContaMilhagem[] buscarTodos() throws ObjetoInexistenteException {	
		ContaMilhagem[] retorno = null;
		try{
			RepositorioDeContasMilhagemIO rep = new RepositorioDeContasMilhagemIO();
			retorno = rep.buscarTodos();
		} catch(ObjetoInexistenteException e){
			throw new ObjetoInexistenteException("O arquivo n�o existe no reposit�rio.");
		}
		return retorno;
	}

}
