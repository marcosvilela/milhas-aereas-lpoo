package com.acme.rn.classesGerais;

import java.io.Serializable;

import com.acme.excecoes.AtributoInvalidoException;

@SuppressWarnings("serial")
public abstract class Registro implements Serializable{

/** Método getChave(), retorna uma String vazia como Chave.	
 * @return
 */
	public abstract String getChave();
		
	public abstract void validar() throws AtributoInvalidoException;
}
