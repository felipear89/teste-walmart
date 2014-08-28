package com.farodrigues.teste_walmart.calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.farodrigues.teste_walmart.domain.Rota;

public class ProcessaMenorRota {

	private static final Logger logger = LoggerFactory.getLogger(ProcessaMenorRota.class);

	private final Set<Rota> rotas;
	private final String origem;
	private final String destino;
	private final Set<String> visitados;
	private final Map<String, PontoAnteriorDistancia> menorDistancia;
	private final Queue<Rota> rotasParaVisitar;

	public ProcessaMenorRota(Set<Rota> rotas, String origem, String destino) {
		if (StringUtils.isEmpty(origem) || StringUtils.isEmpty(destino)) {
			throw new IllegalArgumentException("Para processar a menor rota é necessário origem e destino");
		}
		if (rotas == null || rotas.isEmpty()) {
			throw new IllegalArgumentException("Para processar a menor rota é necessário rotas");
		}
		this.rotas = rotas;
		this.origem = origem;
		this.destino = destino;
		this.visitados = new HashSet<String>();
		this.menorDistancia = new HashMap<String, PontoAnteriorDistancia>();
		this.rotasParaVisitar = new LinkedList<Rota>();

	}

	public List<Rota> getMenorRota() {
		addRotasParaVisitar(origem);
		initMenorDistancia();
		while (!rotasParaVisitar.isEmpty()) {

			Rota rota = rotasParaVisitar.poll();
			String destinoRota = rota.getDestino();

			visitados.add(rota.getOrigem());
			if (!visitados.contains(destinoRota)) {
				addRotasParaVisitar(destinoRota);
			}

			calcularMenorDistancia(rota);
		}
		List<Rota> menorRota = new ArrayList<Rota>();
		PontoAnteriorDistancia pontoAnteriorDistancia = menorDistancia.get(destino);
		do {
			menorRota.add(pontoAnteriorDistancia.getRota());
			pontoAnteriorDistancia = menorDistancia.get(pontoAnteriorDistancia.getRota().getOrigem());
		} while (pontoAnteriorDistancia.getRota() != null);

		Collections.reverse(menorRota);
		menorRota.forEach((r) -> logger.debug(r.getDestino()));
		return menorRota;
	}

	private void calcularMenorDistancia(Rota rota) {
		String origemRota = rota.getOrigem();
		String destinoRota = rota.getDestino();

		PontoAnteriorDistancia menorDistanciaOrigem = menorDistancia.get(origemRota);
		PontoAnteriorDistancia menorDistanciaDestino = menorDistancia.get(destinoRota);

		Integer distanciaTotal = rota.getDistancia() + menorDistanciaOrigem.getDistancia();

		if (distanciaTotal < menorDistanciaDestino.getDistancia()) {
			menorDistanciaDestino.setRota(rota);
			menorDistanciaDestino.setDistancia(distanciaTotal);
		}

	}

	private void addRotasParaVisitar(String destinoOrigem) {
		for (Rota rota : rotas) {
			if (rota.getOrigem().equals(destinoOrigem)) {
				rotasParaVisitar.add(rota);
			}
		}
	}

	private void initMenorDistancia() {
		for (Rota rota : rotas) {
			if (rota.getOrigem().equals(origem)) {
				menorDistancia.put(origem, new PontoAnteriorDistancia(null, 0));
				menorDistancia.put(rota.getDestino(), new PontoAnteriorDistancia(null, Integer.MAX_VALUE));
			} else {
				menorDistancia.put(rota.getDestino(), new PontoAnteriorDistancia(null, Integer.MAX_VALUE));
			}
		}
	}

	private static class PontoAnteriorDistancia {

		private Rota rota;

		private Integer distancia;

		public PontoAnteriorDistancia(Rota rota, Integer distancia) {
			this.rota = rota;
			this.distancia = distancia;
		}

		public Integer getDistancia() {
			return distancia;
		}

		public void setDistancia(Integer distancia) {
			this.distancia = distancia;
		}

		public Rota getRota() {
			return rota;
		}

		public void setRota(Rota rota) {
			this.rota = rota;
		}
	}

}
