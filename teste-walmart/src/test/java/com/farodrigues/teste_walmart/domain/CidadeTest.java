package com.farodrigues.teste_walmart.domain;

import org.junit.Assert;
import org.junit.Test;

public class CidadeTest {

	@Test
	public void deveAdicionarUmaNovaConexaoNaListaDeConexoesDaCidade() {
		Cidade cidadeTeste1 = new Cidade("Cidade Teste 1");
		Cidade cidadeTeste2 = new Cidade("Cidade Teste 2");
		Conexao conexao = new Conexao(cidadeTeste1, cidadeTeste2, 10);
		
		cidadeTeste1.addConexao(conexao);
		
		Assert.assertTrue(!cidadeTeste1.getConexoes().isEmpty());
	}
}
