package com.acme.testes.conta;

import java.util.Scanner;

import com.acme.excecoes.AtributoInvalidoException;
import com.acme.excecoes.ObjetoExistenteException;
import com.acme.excecoes.ObjetoInexistenteException;
import com.acme.excecoes.QuebraDeRegraException;
import com.acme.rn.cliente.*;
import com.acme.rn.conta.*;

public class TesteControladorContaMilhagem {
	public static void main(String[] args) {

		/*
		 * Inicializando os dados do primeiro cliente.
		 */
		Scanner sc = new Scanner(System.in);
		try {
			int opcao;
			int outraOperacao = 2;

			CPF cpfDoCliente1 = new CPF("11111111111");
			Cliente cliente1 = new Cliente("Marcos Vilela", cpfDoCliente1, 19, 2500.0, 1);
			IdentificadorConta identificadorDaConta1 = new IdentificadorConta(Long.parseLong(cpfDoCliente1.getCPF()));
			ControladorCliente.incluir(cliente1);
			/*
			 * Inicializando os dados do segundo cliente.
			 */
			CPF cpfDoCliente2 = new CPF("22222222222");
			Cliente cliente2 = new Cliente("Marcel Souza", cpfDoCliente2, 19, 6500.0, 1);
			IdentificadorConta identificadorDaConta2 = new IdentificadorConta(Long.parseLong(cpfDoCliente2.getCPF()));
			ControladorCliente.incluir(cliente2);

			do {
				System.out.println("--- CONTROLADOR CONTA MILHAGEM ---\n\n");
				System.out.println("Escolha o que deseja fazer: ");
				System.out.println(
						"1 - Buscar\n2 - Creditar na conta\n3 - Debitar na conta\n4 - Transferir para outra conta\n5 - Buscar todos");
				opcao = sc.nextInt();

				switch (opcao) {
				case 1:
					System.out.println("Informe a Conta: (1 ou 2)");
					int conta = sc.nextInt();
					if(conta == 1){
						System.out.println(ControladorContaMilhagem.buscar(identificadorDaConta1, 0));
						System.out.println("\n\nDeseja fazer outra operação? (1 - Sim, 2 - Não)");
						outraOperacao = sc.nextInt();
						break;
						}else if(conta == 2){
							System.out.println(ControladorContaMilhagem.buscar(identificadorDaConta2, 0));
							System.out.println("\n\nDeseja fazer outra operação? (1 - Sim, 2 - Não)");
							outraOperacao = sc.nextInt();
							break;
						}else{
							System.out.println("Opção inválida!");
							System.out.println("\n\nDeseja fazer outra operação? (1 - Sim, 2 - Não)");
							outraOperacao = sc.nextInt();
							break;
						}
				case 2:
					System.out.println("Informe a conta  (1 ou 2):");
					int contaCreditar = sc.nextInt(); 
					if(contaCreditar == 1){
						System.out.println("Saldo antes do crédito: ");
						System.out.println("Conta " + identificadorDaConta1 + "; " 	+ ControladorContaMilhagem.buscar(identificadorDaConta1, 0).getSaldo() + " R$");
						System.out.println("Digite o valor inteiro a ser creditado: ");
						int valor = sc.nextInt();
						ControladorContaMilhagem.creditar(identificadorDaConta1, valor);
						System.out.println("Saldo após o crédito: ");
						System.out.println("Conta " + identificadorDaConta1 + "; " 	+ ControladorContaMilhagem.buscar(identificadorDaConta1, 0).getSaldo() + " R$");
					}else if(contaCreditar == 2){
						System.out.println("Saldo antes do crédito: ");
						System.out.println("Conta " + identificadorDaConta2 + "; "	 + ControladorContaMilhagem.buscar(identificadorDaConta2, 0).getSaldo() + " R$");
						System.out.println("Digite o valor inteiro a ser creditado: ");
						int valor2 = sc.nextInt();
						ControladorContaMilhagem.creditar(identificadorDaConta2, valor2);
						System.out.println("Saldo após o crédito: ");
						System.out.println("Conta " + identificadorDaConta2 + "; " 	+ ControladorContaMilhagem.buscar(identificadorDaConta2, 0).getSaldo() + " R$");

					}
					System.out.println("\n\nDeseja fazer outra operação? (1 - Sim, 2 - Não)");
					outraOperacao = sc.nextInt();
					break;
					
				case 3:
					System.out.println("Informe a conta (1 ou 2):  ");
					int contaDebitar = sc.nextInt();
					if(contaDebitar == 1){
						System.out.println("\n\nSaldo antes do débito: ");
						System.out.println("Conta " + identificadorDaConta1 + "; " 	+ ControladorContaMilhagem.buscar(identificadorDaConta1, 0).getSaldo() + " R$");
						System.out.println("Digite o valor a ser debitado: ");
						int valordebitar = sc.nextInt();
						ControladorContaMilhagem.debitar(identificadorDaConta1, valordebitar);
						System.out.println("Saldo após o débito: ");
						System.out.println("Conta " + identificadorDaConta1 + "; "	 + ControladorContaMilhagem.buscar(identificadorDaConta1, 0).getSaldo() + " R$");
					}else if(contaDebitar == 2){
						System.out.println("Saldo antes do débito: ");
						System.out.println("Conta " + identificadorDaConta2 + "; " + ControladorContaMilhagem.buscar(identificadorDaConta2, 0).getSaldo() + " R$");
						System.out.println("Digite o valor a ser debitado: ");
						int valordebitar2 = sc.nextInt();
						ControladorContaMilhagem.debitar(identificadorDaConta2, valordebitar2);
						System.out.println("Saldo após o débito: ");
						System.out.println("Conta " + identificadorDaConta2 + "; " 	+ ControladorContaMilhagem.buscar(identificadorDaConta2, 0).getSaldo() + " R$");
					}
					System.out.println("\n\nDeseja fazer outra operação? (1 - Sim, 2 - Não)");
					outraOperacao = sc.nextInt();
					break;
				case 4:
					System.out.println("Informe a conta de origem (1 ou 2): ");
					int contaOrigem = sc.nextInt();
					if(contaOrigem == 1){
						System.out.println("Informe valor a ser transferido: ");
						int valorT = sc.nextInt();
						System.out.println("\n Saldo da conta de origem: "+ ControladorContaMilhagem.buscar(identificadorDaConta1, 0).getSaldo());
						System.out.println("\n Saldo da Conta de Destino: "	+ ControladorContaMilhagem.buscar(identificadorDaConta2, 0).getSaldo());
					
						ControladorContaMilhagem.transferir(identificadorDaConta1, identificadorDaConta2, valorT);
						
						System.out.println("\n Saldo da conta de origem: " + ControladorContaMilhagem.buscar(identificadorDaConta1, 0).getSaldo());
						System.out.println("\n Saldo da Conta de Destino: " +ControladorContaMilhagem.buscar(identificadorDaConta2, 0).getSaldo());
					} else if(contaOrigem == 2){
						
						System.out.println("Informe valor a ser transferido: ");
						int valorT2 = sc.nextInt();
						System.out.println("\n Saldo da conta de origem: "+ ControladorContaMilhagem.buscar(identificadorDaConta2, 0).getSaldo());
						System.out.println("\n Saldo da Conta de Destino: "	+ ControladorContaMilhagem.buscar(identificadorDaConta1, 0).getSaldo());
					
						ControladorContaMilhagem.transferir(identificadorDaConta2, identificadorDaConta1, valorT2);
						
						System.out.println("\n Saldo da conta de origem: " + ControladorContaMilhagem.buscar(identificadorDaConta2, 0).getSaldo());
						System.out.println("\n Saldo da Conta de Destino: " +ControladorContaMilhagem.buscar(identificadorDaConta1, 0).getSaldo());
					}
					System.out.println("\n\nDeseja fazer outra operação? (1 - Sim, 2 - Não)");
					outraOperacao = sc.nextInt();
					break;
				case 5:
					System.out.println(ControladorContaMilhagem.buscarTodos());
					System.out.println("\n\nDeseja fazer outra operação? (1 - Sim, 2 - Não)");
					outraOperacao = sc.nextInt();
					break;
				
				}
				sc.close();
			} while (opcao < 1 || opcao > 5 || outraOperacao == 1);



		} catch (AtributoInvalidoException e1) {
			e1.printStackTrace();
			System.out.println(e1.getMessage());
		} catch (ObjetoExistenteException e2) {
			e2.printStackTrace();
			System.out.println(e2.getMessage());
		} catch (ObjetoInexistenteException e3) {
			e3.printStackTrace();
			System.out.println(e3.getMessage());
		} catch (QuebraDeRegraException e4) {
			e4.printStackTrace();
			System.out.println(e4.getMessage());
		}
	}
}
