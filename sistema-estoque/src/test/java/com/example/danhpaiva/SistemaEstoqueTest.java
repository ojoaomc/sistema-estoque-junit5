package com.example.danhpaiva;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SistemaEstoqueTest {
  private SistemaEstoque sistemaEstoque;

  @Before
  void setUp() {
    sistemaEstoque = new SistemaEstoque();
  }

  @Test
  void adicionarProduto_produtoNovo_adicionaAoEstoque() {
    sistemaEstoque.adicionarProduto("Camiseta", 10);
    assertEquals(10, sistemaEstoque.consultarEstoque("Camiseta"));
    assertEquals(1, sistemaEstoque.obterHistoricoTransacoes().size());
    assertEquals("Adicionado 10 unidade(s) de Camiseta", sistemaEstoque.obterHistoricoTransacoes().get(0));
  }
}
