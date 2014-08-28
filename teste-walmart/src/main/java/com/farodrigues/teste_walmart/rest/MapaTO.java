package com.farodrigues.teste_walmart.rest;

import java.io.Serializable;
import java.util.List;

public class MapaTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	
	private List<RotaTO> rotas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<RotaTO> getRotas() {
		return rotas;
	}

	public void setRotas(List<RotaTO> rotas) {
		this.rotas = rotas;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}
