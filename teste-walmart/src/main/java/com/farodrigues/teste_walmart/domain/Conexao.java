package com.farodrigues.teste_walmart.domain;

public class Conexao {
	private final Cidade cidade;
	private final Integer distancia;

	public Conexao(Cidade cidade, Integer distancia) {
		this.cidade = cidade;
		this.distancia = distancia;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public Integer getDistancia() {
		return distancia;
	}
	
}
