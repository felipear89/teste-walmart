package com.farodrigues.teste_walmart.domain;


import org.junit.Assert;
import org.junit.Test;

public class ConexaoTest {

	@Test
	public void deveVincularAConexaoNasDuasCidades() {
		Cidade cidadeTeste1 = new Cidade("Cidade Teste 1");
		Cidade cidadeTeste2 = new Cidade("Cidade Teste 2");
		
		new Conexao(cidadeTeste1, cidadeTeste2, 10);
		
		Assert.assertTrue(cidadeTeste1.getNumeroDeConexoes() > 0);
		Assert.assertTrue(cidadeTeste2.getNumeroDeConexoes() > 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveValidarConexaoRepetida() {
		Cidade cidadeTeste1 = new Cidade("Cidade Teste 1");
		Cidade cidadeTeste2 = new Cidade("Cidade Teste 2");
		
		new Conexao(cidadeTeste1, cidadeTeste2, 10);
		new Conexao(cidadeTeste2, cidadeTeste1, 20);
	}
	
	@Test
	public void deveRetornarAOutraCidadeDaConexao() {
		Cidade cidadeTeste1 = new Cidade("Cidade Teste 1");
		Cidade cidadeTeste2 = new Cidade("Cidade Teste 2");
		Conexao conexao = new Conexao(cidadeTeste1, cidadeTeste2, 10);
		
		Cidade cidadeNaOutraPonta = conexao.getCidadeConectada(cidadeTeste1);
		Assert.assertEquals(cidadeNaOutraPonta, cidadeTeste2);
		
		cidadeNaOutraPonta = conexao.getCidadeConectada(cidadeTeste2);
		Assert.assertEquals(cidadeNaOutraPonta, cidadeTeste1);
	}
}
