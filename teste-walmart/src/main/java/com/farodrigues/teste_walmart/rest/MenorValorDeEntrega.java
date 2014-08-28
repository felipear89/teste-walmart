package com.farodrigues.teste_walmart.rest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenorValorDeEntrega implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double valor;

	private List<String> pontos;

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public List<String> getPontos() {
		if (pontos == null) {
			pontos = new ArrayList<String>();
		}
		return pontos;
	}
}