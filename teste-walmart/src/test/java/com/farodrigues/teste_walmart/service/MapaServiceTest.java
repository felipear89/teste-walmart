package com.farodrigues.teste_walmart.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.farodrigues.teste_walmart.AppConfTest;
import com.farodrigues.teste_walmart.domain.Mapa;
import com.farodrigues.teste_walmart.domain.Rota;
import com.farodrigues.teste_walmart.repository.MapaRepository;
import com.farodrigues.teste_walmart.repository.RotaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class MapaServiceTest extends AppConfTest {

	@Autowired
	private MapaService mapaService;

	@Autowired
	private MapaRepository mapaRepository;
	
	@Autowired
	private RotaRepository rotaRepository;

	@Test
	public void deveAdicionarMapa() {
		List<Rota> rotas = new ArrayList<Rota>();
		Mapa mapa = mapaService.adicionarMapa(rotas, "Novo Mapa");

		Mapa mapaBD = mapaRepository.findOne(mapa.getId());
		assertTrue(mapaBD.getNome().equals(mapa.getNome()));
	}

	
	@Test
	public void deveAdicionarRotasNoMapa() {
		List<Rota> rotas = new ArrayList<Rota>();
		for (int i = 0; i < 50; i++) {
			rotas.add(new Rota(String.valueOf(i), String.valueOf(i+1), i+100));
		}
		mapaService.adicionarMapa(rotas, "Novo Mapa 2");

		List<Rota> rotasBD = (List<Rota>) rotaRepository.findAll();
		assertTrue(rotasBD.size() == rotas.size());
	}
}
