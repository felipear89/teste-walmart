package com.farodrigues.teste_walmart.domain;

import java.util.HashSet;
import java.util.Set;

public class MalhaLogistica {

	private final Set<Conexao> conexoes;
	private final Set<Cidade> cidades;

	public MalhaLogistica(Set<Conexao> conexoes) {
		this.conexoes = conexoes;
		this.cidades = new HashSet<Cidade>();
		for (Conexao c : conexoes) {
			cidades.add(c.getCidade1());
			cidades.add(c.getCidade2());
		}
	}

	public Set<Conexao> getConexoes() {
		return conexoes;
	}

	public Set<Cidade> getCidades() {
		return cidades;
	}

	public int getTotalDeCidades() {
		return cidades.size();
	}
}