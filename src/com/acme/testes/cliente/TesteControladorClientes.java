package com.acme.testes.cliente;

import com.acme.excecoes.*;
import com.acme.rn.cliente.*;


public class TesteControladorClientes {

	public static void main(String[] args) {
		
		try{		
			Cliente c1 = new Cliente("Jaílson Mendes", new CPF("11744621479"), 52, 4000.0, 1);
			Cliente c2 = new Cliente("Marcelo Gomes", new CPF("14721478914"), 42, 10000.0, 1);
			Cliente c3 = new Cliente("Mariana Rios", new CPF("11427841278"), 42, 10000.0, 2);
			Cliente alterar = new Cliente("Severino Portões", new CPF("14721478914"), 54, 200000.0, 1);
			
			
			ControladorCliente.incluir(c1);         
	        ControladorCliente.incluir(c2);           //Incluindo os clientes a partir do Controlador
		    ControladorCliente.incluir(c3);
			
			System.out.println(ControladorCliente.buscar(new CPF("11744621479")).toString() + "\n");    //Buscando o primeiro cliente cadastrado anteriormente
			System.out.println(ControladorCliente.buscar(new CPF("14721478914")).toString() + "\n");     //Buscando o segundo cliente cadastrado anteriormente
			System.out.println(ControladorCliente.buscar(new CPF("11427841278")).toString() + "\n");    //Buscando o terceiro cliente cadastrado anteriormente
			
			ControladorCliente.alterar(alterar);	
			
			ControladorCliente.excluir(new CPF("11744621479"));
			ControladorCliente.buscarTodos();             
			

		}catch(AtributoInvalidoException e1){
			System.out.println(e1.getMessage());
		}catch(ObjetoExistenteException e2){
			System.out.println(e2.getMessage());
		}catch(ObjetoInexistenteException e3){
			System.out.println(e3.getMessage());
		}catch(QuebraDeRegraException e4){
			System.out.println(e4.getMessage());
		}	

		
		
		try{
			Cliente fake = new Cliente("Josebias Adalberto", new CPF("11740214578"),800,700.0,1);
			
			ControladorCliente.incluir(fake);
			
		}catch(AtributoInvalidoException erro){
			System.out.println(erro.getMessage());
		} catch (ObjetoExistenteException erroa) {
			System.out.println(erroa.getMessage());
		} catch (ObjetoInexistenteException errob) {
			System.out.println(errob.getMessage());
		}
		
	}


}
