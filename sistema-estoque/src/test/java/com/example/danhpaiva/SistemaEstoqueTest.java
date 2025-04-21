package com.example.danhpaiva;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.List;
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
  public void adicionarProduto_nomeNuloOuVazio_lancaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.adicionarProduto(null, 5));
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.adicionarProduto(" ", 5));
    assertEquals(0, sistemaEstoque.obterHistoricoTransacoes().size());
  }

  @Test
  public void adicionarProduto_quantidadeZeroOuNegativa_lancaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.adicionarProduto("Meia", 0));
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.adicionarProduto("Meia", -2));
    assertEquals(0, sistemaEstoque.obterHistoricoTransacoes().size());
  }

  @Test
  public void removerProduto_produtoExistenteComEstoqueSuficiente_decrementaEstoque() {
    sistemaEstoque.adicionarProduto("Sapato", 15);
    sistemaEstoque.removerProduto("Sapato", 7);
    assertEquals(8, sistemaEstoque.consultarEstoque("Sapato"));
    assertEquals(2, sistemaEstoque.obterHistoricoTransacoes().size());
    assertEquals("Adicionado 15 unidade(s) de Sapato", sistemaEstoque.obterHistoricoTransacoes().get(0));
    assertEquals("Removido 7 unidade(s) de Sapato", sistemaEstoque.obterHistoricoTransacoes().get(1));
  }

  @Test
  public void removerProduto_produtoNaoExistente_lancaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto("Jaqueta", 2));
    assertEquals(0, sistemaEstoque.obterHistoricoTransacoes().size());
  }

  @Test
  public void removerProduto_estoqueInsuficiente_lancaIllegalArgumentException() {
    sistemaEstoque.adicionarProduto("Luva", 5);
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto("Luva", 10));
    assertEquals(5, sistemaEstoque.consultarEstoque("Luva")); // Verifica se o estoque não foi alterado
    assertEquals(1, sistemaEstoque.obterHistoricoTransacoes().size());
    assertEquals("Adicionado 5 unidade(s) de Luva", sistemaEstoque.obterHistoricoTransacoes().get(0));
  }

  @Test
  public void removerProduto_nomeNuloOuVazio_lancaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto(null, 1));
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto(" ", 1));
    assertEquals(0, sistemaEstoque.obterHistoricoTransacoes().size());
  }

  @Test
  public void removerProduto_quantidadeZeroOuNegativa_lancaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto("Cinto", 0));
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto("Cinto", -1));
    assertEquals(0, sistemaEstoque.obterHistoricoTransacoes().size());
  }

  @Test
  public void consultarEstoque_produtoExistente_retornaQuantidade() {
    sistemaEstoque.adicionarProduto("Mochila", 7);
    assertEquals(7, sistemaEstoque.consultarEstoque("Mochila"));
  }

  @Test
  public void consultarEstoque_produtoNaoExistente_retornaZero() {
    assertEquals(0, sistemaEstoque.consultarEstoque("Livro"));
  }

  @Test
  public void consultarEstoque_nomeNuloOuVazio_lancaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.consultarEstoque(null));
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.consultarEstoque(" "));
  }

  @Test
  public void obterHistoricoTransacoes_realizaTransacoes_retornaHistorico() {
    sistemaEstoque.adicionarProduto("Caderno", 20);
    List<String> historico = sistemaEstoque.obterHistoricoTransacoes();
    assertEquals(1, historico.size());
    assertEquals("Adicionado 20 unidade(s) de Caderno", historico.get(0));
  }

  @Test
  public void obterHistoricoTransacoes_nenhumaTransacao_retornaListaVazia() {
    assertTrue(sistemaEstoque.obterHistoricoTransacoes().isEmpty());
  }

  @Test
  public void verificarDisponibilidade_produtoExistenteComEstoqueSuficiente_retornaTrue() {
    sistemaEstoque.adicionarProduto("Agenda", 12);
    assertTrue(sistemaEstoque.verificarDisponibilidade("Agenda", 5));
  }

  @Test
  public void verificarDisponibilidade_produtoExistenteComEstoqueInsuficiente_retornaFalse() {
    sistemaEstoque.adicionarProduto("Agenda", 12);
    assertFalse(sistemaEstoque.verificarDisponibilidade("Agenda", 15));
  }

  @Test
  public void verificarDisponibilidade_produtoNaoExistente_retornaFalse() {
    assertFalse(sistemaEstoque.verificarDisponibilidade("Teclado", 3));
  }

  @Test
  public void verificarDisponibilidade_nomeNuloOuVazio_lancaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.verificarDisponibilidade(null, 2));
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.verificarDisponibilidade(" ", 2));
  }

  @Test
  public void verificarDisponibilidade_quantidadeZeroOuNegativa_lancaIllegalArgumentException() {
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.verificarDisponibilidade("Mouse", 0));
    assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.verificarDisponibilidade("Mouse", -1));
  }

  @Test
  public void removerProduto_removerTodoEstoque_produtoRemovidoDoEstoque() {
    sistemaEstoque.adicionarProduto("Boné", 3);
    sistemaEstoque.removerProduto("Boné", 3);
    assertEquals(0, sistemaEstoque.consultarEstoque("Boné"));
    assertFalse(sistemaEstoque.estoque.containsKey("Boné")); // Acessando diretamente para verificar a remoção
    assertEquals(2, sistemaEstoque.obterHistoricoTransacoes().size());
    assertEquals("Adicionado 3 unidade(s) de Boné", sistemaEstoque.obterHistoricoTransacoes().get(0));
    assertEquals("Removido 3 unidade(s) de Boné", sistemaEstoque.obterHistoricoTransacoes().get(1));
  }
}
