package com.farodrigues.teste_walmart.domain;

import java.util.HashSet;
import java.util.Set;

public class Cidade {
	private final String nome;
	private Set<Conexao> conexoes;

	public Cidade(String nome) {
		this.nome = nome;
	}

	public Set<Conexao> getConexoes() {
		if (conexoes == null) {
			conexoes = new HashSet<Conexao>();
		}
		return conexoes;
	}

	public void addConexao(Conexao conexao) {
		if (getConexoes().contains(conexao)) {
			throw new IllegalArgumentException("Esta cidade já possui essa conexão");
		}
		getConexoes().add(conexao);
	}
	
	public int getNumeroDeConexoes() {
		return getConexoes().size();
	}

	public String getNome() {
		return nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
