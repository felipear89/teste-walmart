package com.farodrigues.teste_walmart.calc;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.farodrigues.teste_walmart.domain.Rota;

public class ProcessaMenorRotaTest {

	@Test(expected=IllegalArgumentException.class)
	public void deveValidarParametrosNulos() {
		new ProcessaMenorRota(null, null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveValidarListaVaziaDeRota() {
		new ProcessaMenorRota(new HashSet<Rota>(), null, null);
	}

	@Test
	public void deveRetornarOMenorCaminho1() {
		String origem = "A";
		String destino = "D";
		Set<Rota> rotas = new HashSet<Rota>();
		rotas.add(new Rota("A", "B", 10));
		rotas.add(new Rota("B", "D", 15));
		rotas.add(new Rota("A", "C", 20));
		rotas.add(new Rota("C", "D", 30));
		rotas.add(new Rota("B", "E", 50));
		rotas.add(new Rota("D", "E", 30));

		ProcessaMenorRota processaMenorRota = new ProcessaMenorRota(rotas, origem, destino);
		List<Rota> melhorRota = processaMenorRota.getMenorRota();
		assertTrue(melhorRota.get(0).getOrigem().equals("A"));
		assertTrue(melhorRota.get(0).getDestino().equals("B"));
		assertTrue(melhorRota.get(1).getDestino().equals("D"));
	}
	
	
	@Test
	public void deveRetornarOMenorCaminho2() {
		String origem = "A";
		String destino = "F";
		Set<Rota> rotas = new HashSet<Rota>();
		
		rotas.add(new Rota("A", "B", 5));
		rotas.add(new Rota("A", "C", 10));
		
		rotas.add(new Rota("B", "D", 3));
		rotas.add(new Rota("B", "G", 10));
		
		rotas.add(new Rota("C", "D", 10));
		rotas.add(new Rota("C", "E", 10));
		
		rotas.add(new Rota("D", "F", 10));
		rotas.add(new Rota("D", "E", 5));

		rotas.add(new Rota("E", "F", 4));
		rotas.add(new Rota("E", "H", 1));

		rotas.add(new Rota("G", "F", 10));
		
		rotas.add(new Rota("H", "F", 1));
		
		

		ProcessaMenorRota processaMenorRota = new ProcessaMenorRota(rotas, origem, destino);
		List<Rota> melhorRota = processaMenorRota.getMenorRota();
		assertTrue(melhorRota.get(0).getOrigem().equals("A"));
		assertTrue(melhorRota.get(0).getDestino().equals("B"));
		assertTrue(melhorRota.get(1).getDestino().equals("D"));
		assertTrue(melhorRota.get(2).getDestino().equals("E"));
		assertTrue(melhorRota.get(3).getDestino().equals("H"));
		assertTrue(melhorRota.get(4).getDestino().equals("F"));
	}
	
}

