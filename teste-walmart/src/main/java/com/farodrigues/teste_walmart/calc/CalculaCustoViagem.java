package com.farodrigues.teste_walmart.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculaCustoViagem {

	private final Integer distancia;
	private final Double valorLitro;
	private final Double autonomia;

	public CalculaCustoViagem(Double valorLitro, Integer distancia, Double autonomia) {
		if (valorLitro == null || distancia == null || autonomia == null) {
			throw new IllegalArgumentException("Para calcular o custo é necessário valor do litro de combustivel, distância e autonomia");
		}
		this.valorLitro = valorLitro;
		this.distancia = distancia;
		this.autonomia = autonomia;
	}
	
	public Double getCustoTotal() {
		BigDecimal combustivelGasto = BigDecimal.valueOf(distancia).divide(BigDecimal.valueOf(autonomia), 2, RoundingMode.HALF_UP);
		return (combustivelGasto.multiply(BigDecimal.valueOf(valorLitro))).setScale(2).doubleValue();
	}
	
}
