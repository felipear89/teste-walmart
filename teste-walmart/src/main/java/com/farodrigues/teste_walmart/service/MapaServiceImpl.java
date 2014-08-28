package com.farodrigues.teste_walmart.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farodrigues.teste_walmart.calc.ProcessaMenorRota;
import com.farodrigues.teste_walmart.domain.Mapa;
import com.farodrigues.teste_walmart.domain.Rota;
import com.farodrigues.teste_walmart.repository.MapaRepository;
import com.farodrigues.teste_walmart.repository.RotaRepository;
import com.google.common.collect.Sets;

@Service
public class MapaServiceImpl implements MapaService {
	
	private static final Logger logger = LoggerFactory.getLogger(MapaServiceImpl.class);
	
	@Autowired
	private MapaRepository mapaRepository;

	@Autowired
	private RotaRepository rotaRepository;

	@Transactional
	public Mapa adicionarMapa(List<Rota> rotas, String nome) {

		Mapa mapa = new Mapa(nome, new HashSet<Rota>(rotas));

		mapaRepository.save(mapa);
		rotas.forEach((rota) -> {
			rota.setMapa(mapa);
			rotaRepository.save(rota);
		});
		return mapa;
	}

	@Override
	public List<Rota> getMenorRota(String origem, String destino) {
		Set<Rota> rotas = Sets.newHashSet(rotaRepository.findAll());
		logger.info("Iniciando processo de cálculo de menor rota");
		ProcessaMenorRota processaMenorRota = new ProcessaMenorRota(rotas, origem, destino);
		return processaMenorRota.getMenorRota();
	}
}
