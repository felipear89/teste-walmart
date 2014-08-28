package com.farodrigues.teste_walmart.calc;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CalculaCustoViagemTest {

	@Test
	public void deveRetornarCustoViagem() {
		Integer distancia = 100;
		Double valorLitro = 2.5;
		Double autonomia = 10.0;
		CalculaCustoViagem calculaCustoViagem = new CalculaCustoViagem(valorLitro, distancia, autonomia);
		Double custoTotal = calculaCustoViagem.getCustoTotal();
		assertTrue("Custo Total: " + custoTotal, custoTotal.equals(25.0));
	}
	
	@Test
	public void deveRetornarCustoViagemValoresDecimais() {
		Integer distancia = 33;
		Double valorLitro = 2.5;
		Double autonomia = 7.3;
		CalculaCustoViagem calculaCustoViagem = new CalculaCustoViagem(valorLitro, distancia, autonomia);
		Double custoTotal = calculaCustoViagem.getCustoTotal();
		assertTrue("Custo Total: " + custoTotal, custoTotal.equals(11.3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void deveValidarParametroNulo() {
		Integer distancia = 33;
		Double valorLitro = null;
		Double autonomia = 7.3;
		CalculaCustoViagem calculaCustoViagem = new CalculaCustoViagem(valorLitro, distancia, autonomia);
		calculaCustoViagem.getCustoTotal();
	}
}
