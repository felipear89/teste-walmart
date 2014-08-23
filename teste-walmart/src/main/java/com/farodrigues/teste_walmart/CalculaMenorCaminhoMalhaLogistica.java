package com.farodrigues.teste_walmart;

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

import com.farodrigues.teste_walmart.domain.Cidade;
import com.farodrigues.teste_walmart.domain.Conexao;
import com.farodrigues.teste_walmart.domain.MalhaLogistica;
import com.farodrigues.teste_walmart.utils.CollectionUtils;

public class CalculaMenorCaminhoMalhaLogistica {

	private static final Logger logger = LoggerFactory.getLogger(CalculaMenorCaminhoMalhaLogistica.class);

	private final MalhaLogistica malhaLogistica;
	private Set<Cidade> cidadesVisitadas;
	private Cidade cidadeAnterior;
	private Queue<Cidade> filaCidadesParaVisitar;

	// Armazena a melhor cidade anterior.
	private Map<Cidade, MelhorCidadeAnteriorDistancia> menorCaminho = new HashMap<Cidade, MelhorCidadeAnteriorDistancia>();
	private final Cidade cidadeOrigem;

	public CalculaMenorCaminhoMalhaLogistica(MalhaLogistica malhaLogistica, Cidade cidadeOrigem) {
		// Garante que a cidade base no cálculo de menor caminho será da malha
		// logistica
		if (malhaLogistica.getCidades().contains(cidadeOrigem)) {
			cidadeOrigem = CollectionUtils.find(malhaLogistica.getCidades(), cidadeOrigem);
		} else {
			throw new IllegalArgumentException("A cidade " + cidadeOrigem.getNome() + " não está na malha logística");
		}
		this.malhaLogistica = malhaLogistica;
		this.cidadeOrigem = cidadeOrigem;
		for (Cidade c : this.malhaLogistica.getCidades()) {
			if (c.equals(cidadeOrigem)) {
				menorCaminho.put(c, new MelhorCidadeAnteriorDistancia(null, 0));
			} else {
				menorCaminho.put(c, new MelhorCidadeAnteriorDistancia(null, Integer.MAX_VALUE));
			}
		}
	}

	public void visitarTodasAsCidades() {
		logger.info("Iniciando a visita nas cidades. Primeira cidade: " + cidadeOrigem.toString());
		
		initVisitacao();

		while(!filaCidadesParaVisitar.isEmpty()) {
			Cidade cidadeAtual = filaCidadesParaVisitar.poll();
			cidadesVisitadas.add(cidadeAtual);
			for (Conexao conexao : cidadeAtual.getConexoes()) {
				Cidade cidadeConectada = conexao.getCidadeConectada(cidadeAtual);
				if (!cidadesVisitadas.contains(cidadeConectada)) {
					filaCidadesParaVisitar.add(cidadeConectada);
				}
				processar(cidadeConectada, conexao);
			}
		}

		
		logger.info("Todas as cidades foram visitadas");
	}

	private void initVisitacao() {
		cidadesVisitadas = new HashSet<Cidade>();
		filaCidadesParaVisitar = new LinkedList<Cidade>();
		filaCidadesParaVisitar.add(cidadeOrigem);
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
		visitarTodasAsCidades();
		logger.info("O menor caminho da cidade " + cidadeOrigem + " até a cidade " + cidadeDestino + " é:");
		Cidade cidadeLoop = cidadeDestino;
		MelhorCidadeAnteriorDistancia melhorCidadeAnteriorDistancia = null;
		List<Cidade> caminho = new ArrayList<Cidade>();
		do {
			melhorCidadeAnteriorDistancia = menorCaminho.get(cidadeLoop);
			//logger.info(cidadeLoop + " distância: " + melhorCidadeAnteriorDistancia.getMelhorDistanciaTotal() + "km");
			caminho.add(cidadeLoop);
			cidadeLoop = melhorCidadeAnteriorDistancia.getCidade();
		} while (cidadeOrigem != cidadeLoop);
		caminho.add(cidadeOrigem);
		
		Collections.reverse(caminho);
		
		int total = 0;
		for(Cidade cidade : caminho) {
			MelhorCidadeAnteriorDistancia melhorCaminho = menorCaminho.get(cidade);
			if (melhorCaminho != null) {				
				logger.info("Para: " + cidade.toString() + "  " + melhorCaminho.getMelhorDistanciaTotal());
				total += melhorCaminho.getMelhorDistanciaTotal();
			} else {
				logger.info("Saindo de: " + cidade.toString());
			}
		}
	}
}
