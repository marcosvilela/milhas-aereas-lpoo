package com.acme.ado.tela;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.acme.ado.cliente.RepositorioDeClientesIO;
import com.acme.ado.conta.RepositorioMovimentoContaIO;
import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.cliente.CPF;
import com.acme.rn.cliente.Cliente;
import com.acme.rn.cliente.ControladorCliente;
import com.acme.rn.conta.ControladorContaMilhagem;
import com.acme.rn.conta.ControladorMovimentoConta;
import com.acme.rn.conta.IdentificadorConta;

public class TelaSistema {

	public static int telaPrincipal() {
		String[] options = { "CADASTRO DE CLIENTES", "MANIPULA��O DE CONTA MILHAGEM", "CANCELAR" };
		int i = JOptionPane.showOptionDialog(null, "O que deseja fazer?", "BEM VINDO", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return i;
	}

	public static int telaCliente() {
		String[] optionsClientes = { "INCLUS�O", "ALTERA��O", "EXCLUS�O", "CONSULTA" };
		int i = JOptionPane.showOptionDialog(null, "O que deseja fazer?", "CADASTRO DE CLIENTES",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsClientes,
				optionsClientes[0]);
		return i;
	}
	
	public static int telaConta() {
		String[] optionsConta = { "CREDITO", "DEBITO", "TRANSFERENCIA", "CONSULTAR", "EXTRATO DA CONTA"};
		int i = JOptionPane.showOptionDialog(null, "O que deseja fazer?", "CADASTRO DE CLIENTES",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsConta,
				optionsConta[0]);
		return i;
	}

	public static void main(String[] args) throws AtributoInvalidoException, ObjetoInexistenteException,
			ObjetoExistenteException, QuebraDeRegraException, IOException {
		int i = TelaSistema.telaPrincipal();;
		do {
			switch (i) {
			case 0:
				i = TelaSistema.telaCliente();
				if (i == 0) {

					String name = JOptionPane.showInputDialog("Digite o Nome");
					String cpf = JOptionPane.showInputDialog("Digite o n�mero do CPF");
					String idade = JOptionPane.showInputDialog("Digite a idade");
					int idadeDoCliente = Integer.parseInt(idade);
					String renda = JOptionPane.showInputDialog("Digite a sua renda");
					double rendaDoCliente = Double.parseDouble(renda);
					String sexo = JOptionPane.showInputDialog("Digite o sexo");
					int sexoDoCliente = Integer.parseInt(sexo);

					try {
						Cliente incluir = new Cliente(name, new CPF(cpf), idadeDoCliente, rendaDoCliente, sexoDoCliente);
						ControladorCliente.incluir(incluir);
								
					
						JOptionPane.showMessageDialog(null, "Cliente criado com sucesso!");
					} catch (AtributoInvalidoException e) {
						System.out.println(e.getMessage());
					} catch (ObjetoInexistenteException e) {
						System.out.println(e.getMessage());
					} catch (ObjetoExistenteException e) {
						System.out.println(e.getMessage());
					}
					break;
				}else if (i == 1) {
					String name = JOptionPane.showInputDialog("Digite o Nome");
					String cpf = JOptionPane.showInputDialog("Digite o numero do CPF");
					String idade = JOptionPane.showInputDialog("Digite a idade");
					int idadeDoCliente = Integer.parseInt(idade);
					String renda = JOptionPane.showInputDialog("Digite a sua renda");
					double rendaDoCliente = Double.parseDouble(renda);
					String sexo = JOptionPane.showInputDialog("Digite o sexo");
					int sexoDoCliente = Integer.parseInt(sexo);

					try {
						Cliente alterar = new Cliente(name, new CPF(cpf), idadeDoCliente, rendaDoCliente, sexoDoCliente);
						ControladorCliente.alterar(alterar);
						
						JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
					} catch (AtributoInvalidoException e) {
						System.out.println(e.getMessage());
					} catch (ObjetoInexistenteException e) {
						System.out.println(e.getMessage());
					}
					break;
				} else if(i == 2){
					
					String cpf = JOptionPane.showInputDialog("Digite o numero do CPF");
					
					try{
						
						
						ControladorCliente.excluir(new CPF(cpf));
						JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
					} catch(RuntimeException e){}
					
					break;
				}else if(i == 3){
					String cpf = JOptionPane.showInputDialog("Digite o n�mero do CPF");
					
					try{
						ControladorCliente.buscar(new CPF(cpf));
						RepositorioDeClientesIO rep = new RepositorioDeClientesIO();
						rep.buscar(new CPF(cpf));
						JOptionPane.showMessageDialog(null, "Cliente encontrado.");
						JOptionPane.showMessageDialog(null, rep.buscar(new CPF(cpf)));
						TelaSistema.telaPrincipal();
					} catch(AtributoInvalidoException e){
						System.out.println(e.getMessage());
					} 
					break;
					
				}
			case 1:
				i = TelaSistema.telaConta();
				if(i == 0){
					String id = JOptionPane.showInputDialog("Digite o numero do Identificador da conta");
					long idConta = Long.parseLong(id);
					String v = JOptionPane.showInputDialog("Digite o valor a ser creditado");
					int valor = Integer.parseInt(v);
					
					try{
						ControladorContaMilhagem.creditar(new IdentificadorConta(idConta), valor);
						
						JOptionPane.showMessageDialog(null, "Saldo: " + ControladorContaMilhagem.buscar(new IdentificadorConta(idConta),0).getSaldo() + "R$\n\nDeseja voltar?");	
					} catch(QuebraDeRegraException e){
						System.out.println(e.getMessage());
					}
					break;
				} else if(i == 1){
					String id = JOptionPane.showInputDialog("Digite o numero do Identificador da conta");
					long idConta = Long.parseLong(id);
					String v = JOptionPane.showInputDialog("Digite o valor a ser creditado");
					int valor = Integer.parseInt(v);
					
					try{
						ControladorContaMilhagem.debitar(new IdentificadorConta(idConta), valor);
						
						JOptionPane.showMessageDialog(null, "Saldo: " + ControladorContaMilhagem.buscar(new IdentificadorConta(idConta),0).getSaldo() + "R$\n\nDeseja voltar?");	
					} catch(QuebraDeRegraException e){
						System.out.println(e.getMessage());
					}
					break;
				} else if(i == 2){
					String id1 = JOptionPane.showInputDialog("Digite o numero do Identificador da conta de origem");
					long idConta1 = Long.parseLong(id1);
					String id2 = JOptionPane.showInputDialog("Digite o numero do Identificador da conta de destino");
					long idConta2 = Long.parseLong(id2);
					String v = JOptionPane.showInputDialog("Digite o numero do Identificador da conta");
					int valor = Integer.parseInt(v);
					
					try{
						ControladorContaMilhagem.transferir(new IdentificadorConta(idConta1), new IdentificadorConta(idConta2), valor);
						
						JOptionPane.showMessageDialog(null, "Saldo da conta: " + ControladorContaMilhagem.buscar(new IdentificadorConta(idConta2),0).getSaldo() + "R$\n\nDeseja voltar?");
					} catch(QuebraDeRegraException e){
						System.out.println(e.getMessage());	
					}
					break;
				} else if(i == 3){
					String id1 = JOptionPane.showInputDialog("Digite o numero do Identificador da conta de origem");
					long idConta1 = Long.parseLong(id1);
					
					try{
						ControladorContaMilhagem.buscar(new IdentificadorConta(idConta1), 0);
						
						JOptionPane.showMessageDialog(null, ControladorContaMilhagem.buscar(new IdentificadorConta(idConta1),0) + "\n\nDeseja voltar?");
					} catch (ObjetoInexistenteException e){
						System.out.println(e.getMessage());
					}
					break;
				} else if( i == 4){
					String id1 = JOptionPane.showInputDialog("Digite o numero do Identificador da conta de origem");
					long idConta1 = Long.parseLong(id1);
					
					try{
						ControladorMovimentoConta.buscarPorConta(new IdentificadorConta(idConta1));
						RepositorioMovimentoContaIO rep = new RepositorioMovimentoContaIO();
						JOptionPane.showMessageDialog(null, rep.buscarTodos());
					} catch(ObjetoInexistenteException e){
						System.out.println(e.getMessage());
					}
				}
			default:
				break;
			}
		} while (TelaSistema.telaPrincipal() != 2);
	}
}
