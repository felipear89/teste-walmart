package com.farodrigues.teste_walmart.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import com.farodrigues.teste_walmart.AppConfWebTest;
import com.farodrigues.teste_walmart.domain.Rota;
import com.farodrigues.teste_walmart.service.MapaService;

@RunWith(SpringJUnit4ClassRunner.class)
public class WalmartRestTest extends AppConfWebTest {
	
	@Autowired
	private MapaService mapaService;

	@Test
	public void deveCadastrarMalhaLogistica() throws Exception {

		MapaTO mapaTO = new MapaTO();
		mapaTO.setNome("MAPA DE SP");

		List<RotaTO> rotas = new ArrayList<RotaTO>();
		rotas.add(new RotaTO("São Paulo", "Santos", 80));
		rotas.add(new RotaTO("São Paulo", "Bragança Paulista", 87));
		rotas.add(new RotaTO("São Paulo", "Ourinhos", 380));

		mapaTO.setRotas(rotas);
		this.mvc.perform(
				post("/services/novoMapa")
				.contentType(APPLICATION_JSON_UTF8)
				.content(gson.toJson((mapaTO))))
				.andExpect(status().isOk());
	}
	
	@Test
	public void deveInvalidarCadastroDeMapaSemNome() throws Exception {
		MapaTO mapaTO = new MapaTO();
		this.mvc.perform(
				post("/services/novoMapa")
				.contentType(APPLICATION_JSON_UTF8)
				.content(gson.toJson((mapaTO))))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void deveInvalidarCadastroDeMapaSemRota() throws Exception {
		MapaTO mapaTO = new MapaTO();
		mapaTO.setNome("Mapa zero");
		this.mvc.perform(
				post("/services/novoMapa")
				.contentType(APPLICATION_JSON_UTF8)
				.content(gson.toJson((mapaTO))))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void deveRetornarAmenorValorDeEntrega() throws Exception {
		popularRotas();
		
		MvcResult mvcReturn = this.mvc.perform(
				get("/services/menorValorDeEntrega")
				.param("origem", "A")
				.param("destino", "D")
				.param("autonomia", "10")
				.param("valorLitroCombustivel", "2.5")
				).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andReturn();
		
		MenorValorDeEntrega menorValorDeEntrega = gson.fromJson(mvcReturn.getResponse().getContentAsString(), MenorValorDeEntrega.class);
		
		Assert.isTrue(menorValorDeEntrega.getValor().equals(6.25));
		Assert.isTrue(menorValorDeEntrega.getPontos().get(1).equals("B"));
				
	}
	
	private void popularRotas() {
		List<Rota> rotas = new ArrayList<Rota>();
		rotas.add(new Rota("A", "B", 10));
		rotas.add(new Rota("B", "D", 15));
		rotas.add(new Rota("A", "C", 20));
		rotas.add(new Rota("C", "D", 30));
		rotas.add(new Rota("B", "E", 50));
		rotas.add(new Rota("D", "E", 30));
		mapaService.adicionarMapa(rotas, "Mapa de teste");
	}
}
