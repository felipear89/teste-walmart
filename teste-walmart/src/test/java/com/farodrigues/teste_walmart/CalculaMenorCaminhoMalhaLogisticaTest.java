package com.farodrigues.teste_walmart;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.farodrigues.teste_walmart.domain.Cidade;
import com.farodrigues.teste_walmart.domain.Conexao;
import com.farodrigues.teste_walmart.domain.MalhaLogistica;

public class CalculaMenorCaminhoMalhaLogisticaTest {

	@Test
	public void devePercorrerTodasAsCidades() {
		MalhaLogistica malhaLogistica = new MalhaLogistica(criarConexoesParaTest());
		CalculaMenorCaminhoMalhaLogistica calculaMenorCaminhoMalhaLogistica = new CalculaMenorCaminhoMalhaLogistica(malhaLogistica, malhaLogistica.getCidades().iterator().next());
		calculaMenorCaminhoMalhaLogistica.visitarTodasAsCidades();
		Assert.assertTrue("Total: " + calculaMenorCaminhoMalhaLogistica.getTotalCidadesVisitadas(), calculaMenorCaminhoMalhaLogistica.getTotalCidadesVisitadas() == malhaLogistica.getCidades().size());
	}
	
	@Test
	public void deveRetornarMenorCaminho() {
		MalhaLogistica malhaLogistica = new MalhaLogistica(criarConexoesParaTest());
		CalculaMenorCaminhoMalhaLogistica calculaMenorCaminhoMalhaLogistica = new CalculaMenorCaminhoMalhaLogistica(malhaLogistica, new Cidade("Cidade A"));
		List<Cidade> menorCaminho = calculaMenorCaminhoMalhaLogistica.getMenorCaminho(new Cidade("Cidade H"));
		
		Assert.assertTrue(menorCaminho.get(0).getNome().equals("Cidade A"));
		Assert.assertTrue(menorCaminho.get(1).getNome().equals("Cidade C"));
		Assert.assertTrue(menorCaminho.get(2).getNome().equals("Cidade D"));
		Assert.assertTrue(menorCaminho.get(3).getNome().equals("Cidade I"));
		Assert.assertTrue(menorCaminho.get(4).getNome().equals("Cidade H"));
	}
	
	@Test
	public void deveRetornarMenorCaminho2() {
		MalhaLogistica malhaLogistica = new MalhaLogistica(criarConexoesParaTest());
		CalculaMenorCaminhoMalhaLogistica calculaMenorCaminhoMalhaLogistica = new CalculaMenorCaminhoMalhaLogistica(malhaLogistica, new Cidade("Cidade A"));
		List<Cidade> menorCaminho = calculaMenorCaminhoMalhaLogistica.getMenorCaminho(new Cidade("Cidade G"));
		
		Assert.assertTrue(menorCaminho.get(0).getNome().equals("Cidade A"));
		Assert.assertTrue(menorCaminho.get(1).getNome().equals("Cidade C"));
		Assert.assertTrue(menorCaminho.get(2).getNome().equals("Cidade E"));
		Assert.assertTrue(menorCaminho.get(3).getNome().equals("Cidade G"));
	}
	
	@Test
	public void deveRetornarMenorCaminho3() {
		MalhaLogistica malhaLogistica = new MalhaLogistica(criarConexoesParaTest());
		CalculaMenorCaminhoMalhaLogistica calculaMenorCaminhoMalhaLogistica = new CalculaMenorCaminhoMalhaLogistica(malhaLogistica, new Cidade("Cidade A"));
		List<Cidade> menorCaminho = calculaMenorCaminhoMalhaLogistica.getMenorCaminho(new Cidade("Cidade J"));
		
		Assert.assertTrue(menorCaminho.get(0).getNome().equals("Cidade A"));
		Assert.assertTrue(menorCaminho.get(1).getNome().equals("Cidade B"));
		Assert.assertTrue(menorCaminho.get(2).getNome().equals("Cidade J"));
	}

	private Set<Conexao> criarConexoesParaTest() {
		Set<Conexao> conexoes = new HashSet<Conexao>();
		Cidade cidadeA = new Cidade("Cidade A");
		Cidade cidadeB = new Cidade("Cidade B");
		Cidade cidadeC = new Cidade("Cidade C");
		Cidade cidadeD = new Cidade("Cidade D");
		Cidade cidadeE = new Cidade("Cidade E");
		Cidade cidadeF = new Cidade("Cidade F");
		Cidade cidadeG = new Cidade("Cidade G");
		Cidade cidadeH = new Cidade("Cidade H");
		Cidade cidadeI = new Cidade("Cidade I");
		Cidade cidadeJ = new Cidade("Cidade J");
		
		Collections.addAll(conexoes,
				new Conexao(cidadeA, cidadeB, 10), 
				new Conexao(cidadeA, cidadeC, 5),
				new Conexao(cidadeB, cidadeD, 10),
				new Conexao(cidadeD, cidadeH, 10),
				new Conexao(cidadeC, cidadeD, 5),
				new Conexao(cidadeH, cidadeI, 5),
				new Conexao(cidadeI, cidadeF, 1),
				new Conexao(cidadeI, cidadeD, 2),
				new Conexao(cidadeC, cidadeE, 3),
				new Conexao(cidadeF, cidadeC, 15),
				new Conexao(cidadeE, cidadeG, 1),
				new Conexao(cidadeG, cidadeA, 10),
				new Conexao(cidadeJ, cidadeB, 20),
				new Conexao(cidadeJ, cidadeA, 35)
				);
		return conexoes;
	}
}
