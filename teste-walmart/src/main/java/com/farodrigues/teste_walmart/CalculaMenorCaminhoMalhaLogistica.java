package com.farodrigues.teste_walmart;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.farodrigues.teste_walmart.domain.Cidade;
import com.farodrigues.teste_walmart.domain.Conexao;
import com.farodrigues.teste_walmart.domain.MalhaLogistica;
import com.farodrigues.teste_walmart.utils.CollectionUtils;

public class CalculaMenorCaminhoMalhaLogistica {

	private static final Logger logger = LoggerFactory.getLogger(CalculaMenorCaminhoMalhaLogistica.class);

	private final MalhaLogistica malhaLogistica;
	private Set<Cidade> cidadesVisitadas;
	private Cidade cidadeAnterior;

	// Armazena a melhor cidade anterior.
	private Map<Cidade, MelhorCidadeAnteriorDistancia> menorCaminho = new HashMap<Cidade, MelhorCidadeAnteriorDistancia>();
	private final Cidade primeiraCidade;

	public CalculaMenorCaminhoMalhaLogistica(MalhaLogistica malhaLogistica, Cidade primeiraCidade) {
		// Garante que a cidade base no cálculo de menor caminho será da malha
		// logistica
		if (malhaLogistica.getCidades().contains(primeiraCidade)) {
			primeiraCidade = CollectionUtils.find(malhaLogistica.getCidades(), primeiraCidade);
		} else {
			throw new IllegalArgumentException("A cidade " + primeiraCidade.getNome() + " não está na malha logística");
		}
		this.malhaLogistica = malhaLogistica;
		this.primeiraCidade = primeiraCidade;
		for (Cidade c : this.malhaLogistica.getCidades()) {
			if (c.equals(primeiraCidade)) {
				menorCaminho.put(c, new MelhorCidadeAnteriorDistancia(null, 0));
			} else {
				menorCaminho.put(c, new MelhorCidadeAnteriorDistancia(null, Integer.MAX_VALUE));
			}
		}
	}

	public void visitarTodasAsCidades() {
		logger.info("Iniciando a visita nas cidades. Primeira cidade: " + primeiraCidade.toString());
		cidadesVisitadas = new HashSet<Cidade>();
		visitarTodasAsCidades(primeiraCidade);
	}

	public void visitarTodasAsCidades(Cidade cidadeInicial) {

		Set<Conexao> conexoesLocal = cidadeInicial.getConexoes();
		for (Conexao c : conexoesLocal) {
			Cidade cidadeConectada = c.getCidadeConectada(cidadeInicial);

			if (cidadeAnterior != null && cidadeConectada == cidadeAnterior) {
				continue;
			}
			
			logger.info("Indo de " + cidadeInicial + " para " + cidadeConectada);

			processar(cidadeConectada, c);
			if (!cidadesVisitadas.contains(cidadeConectada)) {
				cidadeAnterior = cidadeInicial;
				cidadesVisitadas.add(cidadeConectada);
				visitarTodasAsCidades(cidadeConectada);
			}
		}
	}

	private void processar(Cidade cidadeAtual, Conexao conexao) {
		Cidade outraCidade = conexao.getCidadeConectada(cidadeAtual);
		Integer distanciaTotalAnterior = menorCaminho.get(outraCidade).getMelhorDistanciaTotal();
		Integer distanciaTotalAPartirDaPrimeiraCidade = conexao.getDistancia() + distanciaTotalAnterior;
		//logger.info("A distancia entre " + cidadeAtual + " e " + outraCidade + " é " + conexao.getDistancia());
		//logger.info("Atualmente a menor distancia da " + cidadeAtual + " é de: " + distanciaTotalAPartirDaPrimeiraCidade);
		if (distanciaTotalAPartirDaPrimeiraCidade < menorCaminho.get(cidadeAtual).getMelhorDistanciaTotal()) {
			menorCaminho.get(cidadeAtual).atualizar(outraCidade, distanciaTotalAPartirDaPrimeiraCidade);
		}
	}

	private static class MelhorCidadeAnteriorDistancia {
		private Cidade cidade;
		private Integer distanciaTotal;

		public MelhorCidadeAnteriorDistancia(Cidade cidade, Integer distanciaTotal) {
			this.cidade = cidade;
			this.distanciaTotal = distanciaTotal;
		}

		public Integer getMelhorDistanciaTotal() {
			return distanciaTotal;
		}

		public void atualizar(Cidade cidade, Integer distanciaTotal) {
			this.cidade = cidade;
			this.distanciaTotal = distanciaTotal;
		}

		public Cidade getCidade() {
			return this.cidade;
		}

		@Override
		public String toString() {
			return "A menor distancia entre " + cidade + " e a cidade origem é de: " + distanciaTotal + "km";
		}
	}

	public int getTotalCidadesVisitadas() {
		return cidadesVisitadas.size();
	}

	public void getMenorCaminho(Cidade cidadeDestino) {
		logger.info("###################################################################");
		logger.info("");
		Cidade cidadeLoop = cidadeDestino;
		MelhorCidadeAnteriorDistancia melhorCidadeAnteriorDistancia = null;
		do {
			melhorCidadeAnteriorDistancia = menorCaminho.get(cidadeLoop);
			logger.info(cidadeLoop + " distância: " + melhorCidadeAnteriorDistancia.getMelhorDistanciaTotal() + "km");
			cidadeLoop = melhorCidadeAnteriorDistancia.getCidade();
		} while (primeiraCidade != cidadeLoop);
		logger.info("fim: " + primeiraCidade);

	}

}
