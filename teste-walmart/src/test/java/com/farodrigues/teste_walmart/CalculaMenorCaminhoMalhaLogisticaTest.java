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
		
		Assert.assertTrue("Total: " + calculaMenorCaminhoMalhaLogistica.getTotalCidadesVisitadas(), calculaMenorCaminhoMalhaLogistica.getTotalCidadesVisitadas() == malhaLogistica.getCidades().size());
	}
	
	@Test
	public void devePercorrerTodasAsCidadesEValidarMenorCaminhoDaCidade() {
		MalhaLogistica malhaLogistica = new MalhaLogistica(criarConexoes());
		CalculaMenorCaminhoMalhaLogistica calculaMenorCaminhoMalhaLogistica = new CalculaMenorCaminhoMalhaLogistica(malhaLogistica, new Cidade("Cidade A"));
		calculaMenorCaminhoMalhaLogistica.getMenorCaminho(new Cidade("Cidade H"));
	}
	
	private Set<Conexao> criarConexoes() {
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
				new Conexao(cidadeG, cidadeA, 10)
				);
		return conexoes;
	}
}
