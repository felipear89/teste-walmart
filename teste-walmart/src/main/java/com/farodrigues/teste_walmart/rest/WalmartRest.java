package com.farodrigues.teste_walmart.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.farodrigues.teste_walmart.calc.CalculaCustoViagem;
import com.farodrigues.teste_walmart.domain.Mapa;
import com.farodrigues.teste_walmart.domain.Rota;
import com.farodrigues.teste_walmart.service.MapaService;

@Controller
public class WalmartRest {

	@Autowired
	private MapaService mapaService;

	private static final Logger logger = LoggerFactory.getLogger(WalmartRest.class);

	@RequestMapping(value = "/novoMapa", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> novoMapa(@RequestBody MapaTO mapaTO) {

		if (mapaTO == null || mapaTO.getRotas() == null || mapaTO.getRotas().isEmpty() || mapaTO.getNome() == null || mapaTO.getNome().isEmpty()) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		logger.debug("Cadastrando nova malha logística " + mapaTO.getNome());

		List<RotaTO> rotasTO = mapaTO.getRotas();
		logger.debug("Total de " + mapaTO.getRotas().size() + " rotas");

		List<Rota> rotas = new ArrayList<Rota>();
		
		for (RotaTO rotaTO : rotasTO) {
			Rota rota = new Rota(rotaTO.getOrigem(), rotaTO.getDestino(), rotaTO.getDistancia());
			rotas.add(rota);
		}
		
		Mapa mapa = mapaService.adicionarMapa(rotas, mapaTO.getNome());
		
		logger.debug("Mapa cadastrado. ID: " + mapa.getId());
		return new ResponseEntity<String>(HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/menorValorDeEntrega", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MenorValorDeEntrega menorRota(@RequestBody EntregaInfoTO entregaInfoTO) {
		
		MenorValorDeEntrega menorValorDeEntrega = new MenorValorDeEntrega();
		List<Rota> menorRota = mapaService.getMenorRota(entregaInfoTO.getOrigem(), entregaInfoTO.getDestino());
		Integer distanciaTotal = 0;
		menorValorDeEntrega.getPontos().add(menorRota.get(0).getOrigem());
		for (Rota rota : menorRota) {
			menorValorDeEntrega.getPontos().add(rota.getDestino());
			distanciaTotal += rota.getDistancia();
		};
		
		CalculaCustoViagem calculaCustoViagem = new CalculaCustoViagem(entregaInfoTO.getValorLitroCombustivel(), distanciaTotal, entregaInfoTO.getAutonomia());
		menorValorDeEntrega.setValor(calculaCustoViagem.getCustoTotal());
		return menorValorDeEntrega;
	}
}