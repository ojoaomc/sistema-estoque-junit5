package com.example.danhpaiva;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SistemaEstoqueTest {
  private SistemaEstoque sistemaEstoque;

  @Before
  public void setUp() {
    sistemaEstoque = new SistemaEstoque();
  }

  @Test
  public void adicionarProduto_produtoNovo_adicionaAoEstoque() {
    sistemaEstoque.adicionarProduto("Camiseta", 10);
    assertEquals(10, sistemaEstoque.consultarEstoque("Camiseta"));
    assertEquals(1, sistemaEstoque.obterHistoricoTransacoes().size());
    assertEquals("Adicionado 10 unidade(s) de Camiseta", sistemaEstoque.obterHistoricoTransacoes().get(0));
  }

  @Test
  public void adicionarProduto_produtoExistente_incrementaEstoque() {
    sistemaEstoque.adicionarProduto("Calça", 5);
    sistemaEstoque.adicionarProduto("Calça", 3);
    assertEquals(8, sistemaEstoque.consultarEstoque("Calça"));
    assertEquals(2, sistemaEstoque.obterHistoricoTransacoes().size());
    assertEquals("Adicionado 5 unidade(s) de Calça", sistemaEstoque.obterHistoricoTransacoes().get(0));
    assertEquals("Adicionado 3 unidade(s) de Calça", sistemaEstoque.obterHistoricoTransacoes().get(1));
  }

  @Test
  void adicionarProduto_nomeNuloOuVazio_lancaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.adicionarProduto(null, 5));
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.adicionarProduto(" ", 5));
    assertEquals(0, sistemaEstoque.obterHistoricoTransacoes().size());
  }
}
