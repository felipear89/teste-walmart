package com.farodrigues.teste_walmart.domain;

public class Conexao {
	private final Cidade cidade1;
	private final Cidade cidade2;
	private Integer distancia;

	public Conexao(Cidade cidade1, Cidade cidade2, Integer distancia) {
		if (cidade1 == null || cidade2 == null) {
			throw new IllegalArgumentException("Uma conexão não pode receber uma cidade com valor nulo");
		}
		if (cidade1 == cidade2) {
			throw new IllegalArgumentException("Uma conexão não pode receber a mesma cidade nas duas pontas.");
		}
		this.cidade1 = cidade1;
		this.cidade2 = cidade2;
		this.cidade1.addConexao(this);
		this.cidade2.addConexao(this);
		this.distancia = distancia;
	}

	public Cidade getCidadeConectada(Cidade cidade) {
		return cidade1.equals(cidade) ? cidade2 : cidade1;
	}
	
	public Integer getDistancia() {
		return distancia;
	}
	
	public void setDistancia(Integer distancia) {
		if (distancia == null) {
			throw new IllegalArgumentException("A distancia da conexão não pode ser nula");
		}
		this.distancia = distancia;
	}

	public Cidade getCidade1() {
		return cidade1;
	}

	public Cidade getCidade2() {
		return cidade2;
	}

	@Override
	public String toString() {
		return "A distancia entre a cidade " + cidade1.getNome() + " e " + cidade2.getNome() + " é de " + distancia + "km";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime * ( ((cidade1 == null) ? 0 : cidade1.hashCode()) + ((cidade2 == null) ? 0 : cidade2.hashCode()) );
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
		Conexao other = (Conexao) obj;
		if (cidade1 == null && other.cidade1 != null) {
			return false;
		} 
		if (cidade2 == null && other.cidade2 != null) {
			return false;
		}
		if (cidade1 == other.cidade1 && cidade2 == other.cidade2) {
			return true;
		}
		if (cidade1 == other.cidade2 && cidade2 == other.cidade1) {
			return true;
		}
		return false;
	}
}
