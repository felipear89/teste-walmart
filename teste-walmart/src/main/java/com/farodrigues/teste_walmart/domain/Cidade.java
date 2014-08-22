package com.farodrigues.teste_walmart.domain;

import java.util.ArrayList;
import java.util.List;

public class Cidade {
	private List<Conexao> conexoes;

	public List<Conexao> getConexoes() {
		if (conexoes == null) {
			conexoes = new ArrayList<Conexao>();
		}
		return conexoes;
	}
}
