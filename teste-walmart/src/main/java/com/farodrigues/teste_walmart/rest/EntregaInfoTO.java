package com.farodrigues.teste_walmart.rest;

import java.io.Serializable;

public class EntregaInfoTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String origem;

	private String destino;

	private Double autonomia;

	private Double valorLitroCombustivel;

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public Double getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(Double autonomia) {
		this.autonomia = autonomia;
	}

	public Double getValorLitroCombustivel() {
		return valorLitroCombustivel;
	}

	public void setValorLitroCombustivel(Double valorLitroCombustivel) {
		this.valorLitroCombustivel = valorLitroCombustivel;
	}

}
