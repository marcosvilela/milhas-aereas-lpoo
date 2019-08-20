package com.acme.ado.classesGerais;

import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.classesGerais.Registro;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.MovimentoConta;

public class RepositorioDeRegistros implements MetodosRepositorioRegistros {
	/**
	 * Vari�veis necess�rias para um reposit�rio de objetos do tipo
	 * Identificavel.
	 */
	private Registro[] identificaveis;
	private int limiteDeIdentificaveis;

	/**
	 * Construtor da classe, inicializa as vari�veis do reposit�rio.
	 */
	public RepositorioDeRegistros() {
		identificaveis = new Registro[150];
		limiteDeIdentificaveis = 0;
	}

	/**
	 * M�todo incluir(), testa se o limite do Array de Identificaveis j� foi
	 * atingido e adiciona o objeto do tipo Identificavel passado como par�metro
	 * no reposit�rio.
	 * @param novo
	 * @throws ObjetoInexistenteException 
	 */
	public boolean incluir(Registro novo) throws ObjetoExistenteException {
		boolean retorno = false;
		if (limiteDeIdentificaveis < identificaveis.length) {
			if(novo != null){
				try {
					if (this.buscarPorChave(novo.getChave()) == null) {
						identificaveis[limiteDeIdentificaveis] = novo;
						limiteDeIdentificaveis++;
						retorno = true;
					}else{
						throw new ObjetoExistenteException("O Registro j� existe no reposit�rio!");
					}
				} catch (ObjetoInexistenteException e) {}
			}else{
				throw new ObjetoExistenteException("O Registro j� existe no reposit�rio!");
			}
		}
		return retorno;
	}

	/**
	 * M�todo alterar(), percorre todo o Array de Identificaveis, verificando se a chave
	 * do objeto do tipo Identificavel passado como par�metro � igual � chave de algum 
	 * objeto contido no reposit�rio. Caso exista alguma, esse objeto � alterado pelo que
	 * est� sendo passado como par�metro e retorna uma valor booleano de verdade.
	 * @param alterar
	 * @return
	 * @throws QuebraDeRegraException 
	 */
	public boolean alterar(Registro alterar) throws QuebraDeRegraException, ObjetoInexistenteException {
		boolean retorno = false;
		if(alterar != null){
			for (int contador = 0; contador < limiteDeIdentificaveis; contador++) {
				if (identificaveis[contador].getChave().equalsIgnoreCase(alterar.getChave())) {
					if(alterar instanceof ContaMilhagem){
						ContaMilhagem resgate = (ContaMilhagem) this.buscarPorChave(alterar.getChave());
						if(resgate.getSaldo() > 0){
							int saldoResgate = resgate.getSaldo();
							((ContaMilhagem) alterar).creditar(saldoResgate);
						}
					}
					
					identificaveis[contador] = alterar;
					retorno = true;
					break;
				}
			}if(retorno == false){
				throw new ObjetoInexistenteException("O registro n�o existe no reposit�rio!");
			}
		}else{
			throw new ObjetoInexistenteException("O registro n�o existe no reposit�rio!");
		}
		return retorno;
	}

	/**
	 * M�todo buscarPorChave(), recebe como par�metro uma String que representa a chave
	 * do objeto do tipo Identificavel que est� querendo buscar. E verifica se a chave per-
	 * tence a algum objeto contido no reposit�rio. Caso encontre, retorna esse objeto do tipo
	 * Identificavel.
	 * @param chave
	 * @return
	 */
	public Registro buscarPorChave(String chave) throws ObjetoInexistenteException {
		Registro retorno = null;
		if(chave != null && !chave.isEmpty()){
			int contador;
			for (contador = 0; contador < this.limiteDeIdentificaveis; contador++) {
				if(identificaveis[contador] != null){
					if (identificaveis[contador].getChave().equalsIgnoreCase(chave)) {
						retorno = identificaveis[contador];
						break;
					}
				}
			}
		}
		else{
			throw new ObjetoInexistenteException("A chave n�o existe.");
		}
		return retorno;
	}

	/**
	 * M�todo excluir(), recebe uma String que representa a chave do objeto do tipo Identificavel
	 * que se deseja excluir do reposit�rio. Percorre todo o reposit�rio verificando se a chave
	 * passada como par�metro pertence a algum objeto do reposit�rio. Caso encontre, exclui esse
	 * objeto do reposit�rio e retorna um valor booleano de verdade.
	 * @param chave
	 * @return
	 */
	public boolean excluir(String chave) throws ObjetoInexistenteException {
		boolean retorno = false;
		if(chave != null && !chave.isEmpty()){
			for (int contador = 0; contador < limiteDeIdentificaveis; contador++) {
				if(identificaveis[contador] != null){
					if (identificaveis[contador].getChave().equalsIgnoreCase(chave)) {
						limiteDeIdentificaveis--;
						identificaveis[contador] = null;
						retorno = true;
						break;
					}
				}
			}if(retorno == false){
				throw new ObjetoInexistenteException("O registro n�o existe no reposit�rio!");
			}
		}else{
			throw new ObjetoInexistenteException("O registro n�o existe no reposit�rio!");
		}
		return retorno;
	}

	/**
	 * M�todo buscarTodos(), n�o recebe par�metros. Imprime na tela todas as posi��es n�o
	 * nulas do reposit�rio. E retorna um Array do tipo Identificavel com todos os objetos do reposit�rio.
	 * @return
	 */
	public Registro[] buscarTodos() {
		Registro[] todos = new Registro[limiteDeIdentificaveis];
		for (int contador = 0, contadorNaoVazio = 0; contador < identificaveis.length; contador++) {
			if (identificaveis[contador] != null) {
				todos[contador] = identificaveis[contador];
				contadorNaoVazio++;
				if (contadorNaoVazio == 1) {
					System.out.println("---- IDENTIFIC�VEIS ---- ");
				}
				if (todos[contador] instanceof Cliente) {
					System.out.println("\n[CLIENTE]");
				} else if (todos[contador] instanceof ContaMilhagem) {
					System.out.println("\n[CONTA MILHAGEM]");
				} else if (todos[contador] instanceof MovimentoConta) {
					System.out.println("\n[MOVIMENTO CONTA]");
				}
				System.out.println(todos[contador]);
			} else {
				break;
			}
		}
		return todos;
	}

	/**
	 * Sobrescrita do m�todo toString(), retorna uma String com o conte�do da classe.
	 */
	public String toString() {
		int contador;
		int contadorDeID;
		StringBuilder sb = new StringBuilder();
		for(contador = 0, contadorDeID = 0; contador < identificaveis.length; contador++){
			if(identificaveis[contador] != null){
				contadorDeID++;
				sb.append("\n---- Identific�vel ----");
				if(identificaveis[contador] instanceof Cliente)
					sb.append("[CLIENTE]\n" + identificaveis[contador] + "\n");
				else if(identificaveis[contador] instanceof ContaMilhagem)
					sb.append("[CONTA MILHAGEM]\n" + identificaveis[contador] + "\n");
				else if(identificaveis[contador] instanceof MovimentoConta)
					sb.append("[MOVIMENTO CONTA]\n" + identificaveis[contador] + "\n");
			}
		}
		return "\nIdentific�veis: " + contadorDeID + "\n\n" + sb.toString();
		
	}

}
