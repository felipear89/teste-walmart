package com.farodrigues.teste_walmart.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class MalhaLogisticaTest {

	@Test
	public void devePopularTodasAsCidadesNaMalhaLogistica() {
		Cidade cidadeTeste1 = new Cidade("Cidade Teste 1");
		Cidade cidadeTeste2 = new Cidade("Cidade Teste 2");
		Cidade cidadeTeste3 = new Cidade("Cidade Teste 3");
		Cidade cidadeTeste4 = new Cidade("Cidade Teste 4");

		Conexao conexao = new Conexao(cidadeTeste1, cidadeTeste2, 10);
		Conexao conexao2 = new Conexao(cidadeTeste1, cidadeTeste3, 20);
		Conexao conexao3 = new Conexao(cidadeTeste2, cidadeTeste4, 50);
		Conexao conexao4 = new Conexao(cidadeTeste3, cidadeTeste4, 20);

		Set<Conexao> conexoes = new HashSet<Conexao>();
		Collections.addAll(conexoes, conexao, conexao2, conexao3, conexao4);

		MalhaLogistica malhaLogistica = new MalhaLogistica(conexoes);
		Assert.assertTrue(malhaLogistica.getTotalDeCidades() == 4);
	}

}
