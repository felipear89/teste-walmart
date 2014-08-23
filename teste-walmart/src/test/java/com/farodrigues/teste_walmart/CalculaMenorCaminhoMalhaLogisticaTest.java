package com.farodrigues.teste_walmart;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.farodrigues.teste_walmart.domain.Cidade;
import com.farodrigues.teste_walmart.domain.Conexao;
import com.farodrigues.teste_walmart.domain.MalhaLogistica;

public class CalculaMenorCaminhoMalhaLogisticaTest {

	@Test
	public void devePercorrerTodasAsCidades() {
		MalhaLogistica malhaLogistica = new MalhaLogistica(criarConexoes());
		CalculaMenorCaminhoMalhaLogistica calculaMenorCaminhoMalhaLogistica = new CalculaMenorCaminhoMalhaLogistica(malhaLogistica, malhaLogistica.getCidades().iterator().next());
		calculaMenorCaminhoMalhaLogistica.visitarTodasAsCidades();
		
		Assert.assertTrue("Total: " + calculaMenorCaminhoMalhaLogistica.getTotalCidadesVisitadas(), calculaMenorCaminhoMalhaLogistica.getTotalCidadesVisitadas() == 4);
	}
	
	@Test
	public void devePercorrerTodasAsCidadesEValidarMenorCaminhoDaCidade() {
		MalhaLogistica malhaLogistica = new MalhaLogistica(criarConexoes());
		CalculaMenorCaminhoMalhaLogistica calculaMenorCaminhoMalhaLogistica = new CalculaMenorCaminhoMalhaLogistica(malhaLogistica, new Cidade("Cidade Teste A"));
		calculaMenorCaminhoMalhaLogistica.visitarTodasAsCidades();
		calculaMenorCaminhoMalhaLogistica.getMenorCaminho(new Cidade("Cidade Teste D"));
		
		Assert.assertTrue("Total: " + calculaMenorCaminhoMalhaLogistica.getTotalCidadesVisitadas(), calculaMenorCaminhoMalhaLogistica.getTotalCidadesVisitadas() == 4);
	}

	/*private Set<Conexao> criarConexoes() {
		Set<Conexao> conexoes = new HashSet<Conexao>();
		Cidade cidadeTeste1 = new Cidade("Cidade Teste 1");
		Cidade cidadeTeste2 = new Cidade("Cidade Teste 2");
		Cidade cidadeTeste3 = new Cidade("Cidade Teste 3");
		Cidade cidadeTeste4 = new Cidade("Cidade Teste 4");
		Cidade cidadeTeste5 = new Cidade("Cidade Teste 5");
		Cidade cidadeTeste6 = new Cidade("Cidade Teste 6");
		Cidade cidadeTeste7 = new Cidade("Cidade Teste 7");
		Cidade cidadeTeste8 = new Cidade("Cidade Teste 8");
		Cidade cidadeTeste9 = new Cidade("Cidade Teste 9");
		Cidade cidadeTeste10 = new Cidade("Cidade Teste 10");
		Cidade cidadeTeste11 = new Cidade("Cidade Teste 11");

		Conexao conexao = new Conexao(cidadeTeste1, cidadeTeste2, 10);
		Conexao conexao2 = new Conexao(cidadeTeste1, cidadeTeste3, 20);
		Conexao conexao3 = new Conexao(cidadeTeste2, cidadeTeste4, 50);
		Conexao conexao4 = new Conexao(cidadeTeste5, cidadeTeste2, 20);
		Conexao conexao5 = new Conexao(cidadeTeste2, cidadeTeste6, 30);
		Conexao conexao6 = new Conexao(cidadeTeste3, cidadeTeste4, 10);
		Conexao conexao7 = new Conexao(cidadeTeste3, cidadeTeste6, 30);
		Conexao conexao8 = new Conexao(cidadeTeste3, cidadeTeste8, 30);
		Conexao conexao9 = new Conexao(cidadeTeste5, cidadeTeste7, 10);
		Conexao conexao10 = new Conexao(cidadeTeste6, cidadeTeste7, 30);
		Conexao conexao11 = new Conexao(cidadeTeste7, cidadeTeste8, 25);
		Conexao conexao12 = new Conexao(cidadeTeste8, cidadeTeste9, 10);
		Conexao conexao13 = new Conexao(cidadeTeste8, cidadeTeste10, 40);
		Conexao conexao14 = new Conexao(cidadeTeste10, cidadeTeste9, 300);
		Conexao conexao15 = new Conexao(cidadeTeste11, cidadeTeste10, 300);

		Collections.addAll(conexoes, conexao, conexao2, conexao3, conexao4, conexao5, conexao6, conexao7, conexao8, conexao9, conexao10,
				conexao11, conexao12, conexao13, conexao14, conexao15);
		return conexoes;
	}*/
	
	private Set<Conexao> criarConexoes() {
		Set<Conexao> conexoes = new HashSet<Conexao>();
		Cidade cidadeTeste1 = new Cidade("Cidade Teste A");
		Cidade cidadeTeste2 = new Cidade("Cidade Teste B");
		Cidade cidadeTeste3 = new Cidade("Cidade Teste C");
		Cidade cidadeTeste4 = new Cidade("Cidade Teste D");

		Conexao conexao = new Conexao(cidadeTeste1, cidadeTeste2, 20);
		Conexao conexao2 = new Conexao(cidadeTeste1, cidadeTeste3, 10);
		Conexao conexao3 = new Conexao(cidadeTeste2, cidadeTeste4, 20);
		Conexao conexao4 = new Conexao(cidadeTeste3, cidadeTeste4, 20);

		Collections.addAll(conexoes, conexao, conexao2, conexao3, conexao4);
		return conexoes;
	}
}
