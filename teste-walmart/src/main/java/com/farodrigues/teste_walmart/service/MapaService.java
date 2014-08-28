package com.farodrigues.teste_walmart.service;

import java.util.List;

import com.farodrigues.teste_walmart.domain.Mapa;
import com.farodrigues.teste_walmart.domain.Rota;

public interface MapaService {

	public Mapa adicionarMapa(List<Rota> rotas, String nome);

	public List<Rota> getMenorRota(String origem, String destino);

}
