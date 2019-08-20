package com.acme.ado.classesGerais;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.ProblemaFisicoException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.classesGerais.Registro;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.conta.ContaMilhagem;
import com.acme.rn.conta.MovimentoConta;

public class RepositorioDeRegistrosIO<T extends Registro> implements MetodosRepositorioRegistros {

	private final String CAMINHO = System.getProperty("user.dir") + System.getProperty("file.separator");

	@Override
	public boolean incluir(Registro novo) throws ObjetoExistenteException, ObjetoInexistenteException, IOException {
		boolean retorno = false;
		ObjectOutputStream oos = null;
		if (novo != null) {

			String diretorio = null;
			if (novo instanceof Cliente) {
				diretorio = "Cliente" + System.getProperty("file.separator");
			} else if (novo instanceof ContaMilhagem) {
				diretorio = "Conta Milhagem" + System.getProperty("file.separator");
			} else if (novo instanceof MovimentoConta) {
				diretorio = "Movimento Conta" + System.getProperty("file.separator");
			}

			try {
				String local = CAMINHO + "Arquivos";
				File pastaRaiz = new File(local);
				if (pastaRaiz.exists() == false) {
					pastaRaiz.mkdir();
				}

				String diretorioDestino = System.getProperty("file.separator") + diretorio;
				File diretorioEspecifico = new File(local + diretorioDestino);
				if (diretorioEspecifico.exists() == false) {
					diretorioEspecifico.mkdir();
				}

				File arquivo = new File(
						local + diretorioDestino + System.getProperty("file.separator") + novo.getChave() + ".txt");
				if (arquivo.exists() == false) {
					arquivo.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(arquivo);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(novo);
				oos.flush();
			} catch (IOException e) {
				throw new ProblemaFisicoException("N�o foi poss�vel criar o arquivo", e);
			} finally {
				try {
					oos.close();
					retorno = true;
				} catch (IOException e) {
					throw new ProblemaFisicoException("O arquivo n�o p�de ser fechado.", e);
				}
			}
		}
		return retorno;
	}

	@Override
	public boolean alterar(Registro alterar) throws QuebraDeRegraException, ObjetoInexistenteException, IOException {
		boolean retorno = false;
		ObjectOutputStream oos = null;
		if (alterar != null) {

			String diretorio = null;
			if (alterar instanceof Cliente) {
				diretorio = "Cliente" + System.getProperty("file.separator");
			} else if (alterar instanceof ContaMilhagem) {
				diretorio = "Conta Milhagem" + System.getProperty("file.separator");
			} else if (alterar instanceof MovimentoConta) {
				diretorio = "Movimento Conta" + System.getProperty("file.separator");
			}

			try {
				String local = CAMINHO + "Arquivos";
				File pastaRaiz = new File(local);
				if (pastaRaiz.exists() == false) {
					pastaRaiz.mkdir();
				}

				String diretorioDestino = System.getProperty("file.separator") + diretorio;
				File diretorioEspecifico = new File(local + diretorioDestino);
				if (diretorioEspecifico.exists() == false) {
					diretorioEspecifico.mkdir();
				}

				File arquivo = new File(
						local + diretorioDestino + System.getProperty("file.separator") + alterar.getChave() + ".txt");
				if (arquivo.exists() == false) {
					throw new ProblemaFisicoException(
							"N�o foi encontrado nenhum registro com chave semelhante ao que foi passado como par�metro no diret�rio.");
				} else {
					arquivo.delete();
					arquivo = new File(local + diretorioDestino + System.getProperty("file.separator")
							+ alterar.getChave() + ".txt");
					FileOutputStream fos = new FileOutputStream(arquivo);
					oos = new ObjectOutputStream(fos);
					oos.writeObject(alterar);
					oos.flush();
				}
			} catch (IOException e) {
				throw new ProblemaFisicoException(
						"O registro n�o pertence a nenhum arquivo do banco de Dados, portanto, n�o pode ser alterado.",
						e);
			} finally {
				try {
					oos.close();
					retorno = true;
				} catch (IOException e) {
					throw new ProblemaFisicoException("O arquivo n�o p�de ser fechado.", e);
				}
			}
		}
		return retorno;
	}

	@Override
	public Registro buscarPorChave(String chave)
			throws ObjetoInexistenteException, ProblemaFisicoException, ClassNotFoundException {
		Registro retorno = null;
		ObjectInputStream ois = null;
		if (chave != null && !chave.isEmpty()) {
			String diretorio = null;
			int i = 0;
			do {
				if (i == 0) {
					diretorio = "Cliente" + System.getProperty("file.separator");
				} else if (i == 1) {
					diretorio = "Conta Milhagem" + System.getProperty("file.separator");
				} else if (i == 2) {
					diretorio = "Movimento Conta" + System.getProperty("file.separator");
				}

				try {
					String local = CAMINHO + "Arquivos";
					File pastaRaiz = new File(local);
					if (pastaRaiz.exists() == false) {
						pastaRaiz.mkdirs();
					}

					String diretorioDestino = System.getProperty("file.separator") + diretorio;
					File diretorioEspecifico = new File(local + diretorioDestino);
					if (diretorioEspecifico.exists() == false) {
						diretorioEspecifico.mkdirs();
					}

					File arquivo = new File(
							local + diretorioDestino + System.getProperty("file.separator") + chave + ".txt");
					if (arquivo.exists() == false) {
						i++;
					} else {
						try {
							FileInputStream fis = new FileInputStream(arquivo);
							ois = new ObjectInputStream(fis);
							retorno = (Registro) ois.readObject();
						} catch (FileNotFoundException e) {
							throw new ProblemaFisicoException("N�o foi poss�vel abrir o arquivo.", e);
						} catch (IOException e) {
							throw new ProblemaFisicoException("N�o foi poss�vel abrir o arquivo.", e);
						} finally {
							try {
								ois.close();
							} catch (IOException e) {
								throw new ProblemaFisicoException("N�o foi poss�vel fechar o arquivo.", e);
							}
						}

					}
				} catch (ProblemaFisicoException e) {
					System.out.println(e.getMessage());
				}
			} while (i < 3 && retorno == null);

		}
		
		return retorno;
	}

	@Override
	public boolean excluir(String chave) throws ObjetoInexistenteException {
		boolean retorno = false;
		if (chave != null && !chave.isEmpty()) {
			String diretorio = null;
			int i = 0;
			do {
				if (i == 0) {
					diretorio = "Cliente" + System.getProperty("file.separator");
				} else if (i == 1) {
					diretorio = "Conta Milhagem" + System.getProperty("file.separator");
				} else if (i == 2) {
					diretorio = "Movimento Conta" + System.getProperty("file.separator");
				}

				try {
					String local = CAMINHO + "Arquivos";
					File pastaRaiz = new File(local);
					if (pastaRaiz.exists() == false) {
						pastaRaiz.mkdirs();
					}

					String diretorioDestino = System.getProperty("file.separator") + diretorio;
					File diretorioEspecifico = new File(local + diretorioDestino);
					if (diretorioEspecifico.exists() == false) {
						diretorioEspecifico.mkdirs();
					}

					File arquivo = new File(
							local + diretorioDestino + System.getProperty("file.separator") + chave + ".txt");
					if (!arquivo.exists()) {
						i++;
					} else {
						arquivo.delete();
						retorno = true;
						break;
					}
				} catch (ProblemaFisicoException e) {
					System.out.println(e.getMessage());
				}
			} while (i < 3 && retorno == false);

		}
		if (retorno == false) {
			throw new ObjetoInexistenteException("O registro n�o p�de ser deletado, pois n�o existe no reposit�rio.");
		}
		return retorno;
	}

	@Override
	public Registro[] buscarTodos() throws ObjetoInexistenteException {
		Registro[] retornoRegistros = null;
		ArrayList<Registro> adicionar = new ArrayList<Registro>();
		String[] nomeDosArquivosCliente = null;
		String[] nomeDosArquivosContaMilhagem = null;
		String[] nomeDosArquivosMovimentoConta = null;
		try {
			
			String diretorioCliente = "Cliente" + System.getProperty("file.separator");
			String diretorioContaMilhagem = "Conta Milhagem" + System.getProperty("file.separator");
			String diretorioMovimentoConta = "Movimento Conta" + System.getProperty("file.separator");
			
			try {
				String local = CAMINHO + "Arquivos";
				File pastaRaiz = new File(local);
				if (pastaRaiz.exists() == false) {
					pastaRaiz.mkdirs();
				}

				String diretorioDestino = System.getProperty("file.separator") + diretorioCliente;
				File diretorioEspecifico = new File(local + diretorioDestino);
				if (diretorioEspecifico.exists() == false) {
					diretorioEspecifico.mkdirs();
				}

				File arquivoCliente = new File(local + diretorioDestino);
				if (arquivoCliente.isDirectory()) {
					try{
						nomeDosArquivosCliente = arquivoCliente.list();
						for(int contador = 0; contador < nomeDosArquivosCliente.length; contador++){
							nomeDosArquivosCliente[contador] = nomeDosArquivosCliente[contador].replace(".txt", "");
							try {
								adicionar.add(this.buscarPorChave(nomeDosArquivosCliente[contador]));
							} catch (ClassNotFoundException e) {
								throw new ProblemaFisicoException("O arquivo possui objetos que n�o pertencem ao .class", e);
							}
						}
					} catch(ObjetoInexistenteException e){
						throw new ObjetoInexistenteException("O registro n�o existe no reposit�rio.");
					}
				}
			} catch(ProblemaFisicoException e){
				throw new ProblemaFisicoException("N�o foi poss�vel ler os arquivos.", e);
			}
			
			try {
				String local = CAMINHO + "Arquivos";
				File pastaRaiz = new File(local);
				if (pastaRaiz.exists() == false) {
					pastaRaiz.mkdirs();
				}

				String diretorioDestino = System.getProperty("file.separator") + diretorioContaMilhagem;
				File diretorioEspecifico = new File(local + diretorioDestino);
				if (diretorioEspecifico.exists() == false) {
					diretorioEspecifico.mkdirs();
				}

				File arquivoContaMilhagem = new File(local + diretorioDestino);
				if (arquivoContaMilhagem.isDirectory()) {
					try{
						nomeDosArquivosContaMilhagem = arquivoContaMilhagem.list();
						for(int contador = 0; contador < nomeDosArquivosContaMilhagem.length; contador++){
							nomeDosArquivosContaMilhagem[contador] = nomeDosArquivosContaMilhagem[contador].replace(".txt", "");
							try {
								adicionar.add(this.buscarPorChave(nomeDosArquivosContaMilhagem[contador]));
							} catch (ClassNotFoundException e) {
								throw new ProblemaFisicoException("O arquivo possui objetos que n�o pertencem ao .class", e);
							}
						}
					} catch(ObjetoInexistenteException e){
						throw new ObjetoInexistenteException("O registro n�o existe no reposit�rio.");
					}
				}
			} catch(ProblemaFisicoException e){
				throw new ProblemaFisicoException("N�o foi poss�vel ler os arquivos.", e);
			}
			
			try {
				String local = CAMINHO + "Arquivos";
				File pastaRaiz = new File(local);
				if (pastaRaiz.exists() == false) {
					pastaRaiz.mkdirs();
				}

				String diretorioDestino = System.getProperty("file.separator") + diretorioMovimentoConta;
				File diretorioEspecifico = new File(local + diretorioDestino);
				if (diretorioEspecifico.exists() == false) {
					diretorioEspecifico.mkdirs();
				}

				File arquivoMovimentoConta = new File(local + diretorioDestino);
				if (arquivoMovimentoConta.isDirectory()) {
					try{
						nomeDosArquivosMovimentoConta = arquivoMovimentoConta.list();
						for(int contador = 0; contador < nomeDosArquivosMovimentoConta.length; contador++){
							nomeDosArquivosMovimentoConta[contador] = nomeDosArquivosMovimentoConta[contador].replace(".txt", "");
							try {
								adicionar.add(this.buscarPorChave(nomeDosArquivosMovimentoConta[contador]));
							} catch (ClassNotFoundException e) {
								throw new ProblemaFisicoException("O arquivo possui objetos que n�o pertencem ao .class", e);
							}
						}
					} catch(ObjetoInexistenteException e){
						throw new ObjetoInexistenteException("O registro n�o existe no reposit�rio.");
					}
				}
			} catch(ProblemaFisicoException e){
				throw new ProblemaFisicoException("N�o foi poss�vel ler os arquivos.", e);
			}
		} catch(ProblemaFisicoException e){
			throw new ProblemaFisicoException("N�o foi poss�vel ler os arquivos.", e);
		}
		
		retornoRegistros = new Registro[adicionar.size()];
		for (int contadorDeRegistros = 0; contadorDeRegistros < adicionar.size(); contadorDeRegistros++){
			retornoRegistros[contadorDeRegistros] = adicionar.get(contadorDeRegistros);
		}
		
		return retornoRegistros;
	}
}
