import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class SistemaEstoqueTest {

    private SistemaEstoque sistemaEstoque;

    @BeforeEach
    public void setUp() {
        sistemaEstoque = new SistemaEstoque();
    }

    @Test
    public void AdicionarProdutoComSucesso() {
        sistemaEstoque.adicionarProduto("Caneta", 10);
        Map<String, Integer> estoque = sistemaEstoque.getEstoque();
        assertEquals(10, estoque.get("Caneta"));
        assertTrue(sistemaEstoque.getHistoricoTransacoes().contains("Adicionado 10 unidade(s) de Caneta"));
    }

    @Test
    public void AdicionarProdutoComNomeInvalido() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.adicionarProduto("", 5);
        });
        assertEquals("Nome do produto não pode ser nulo ou vazio.", ex.getMessage());
    }

    @Test
    public void AdicionarProdutoComQuantidadeInvalida() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.adicionarProduto("Lápis", 0);
        });
        assertEquals("A quantidade deve ser maior que zero.", ex.getMessage());
    }

    @Test
    public void RemoverProdutoComSucesso() {
        sistemaEstoque.adicionarProduto("Caderno", 5);
        sistemaEstoque.removerProduto("Caderno", 3);
        assertEquals(2, sistemaEstoque.getEstoque().get("Caderno"));
        assertTrue(sistemaEstoque.getHistoricoTransacoes().contains("Removido 3 unidade(s) de Caderno"));
    }

    @Test
    public void RemoverProdutoComQuantidadeMaiorQueEstoque() {
        sistemaEstoque.adicionarProduto("Borracha", 2);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.removerProduto("Borracha", 5);
        });
        assertTrue(ex.getMessage().contains("Estoque insuficiente"));
    }

    @Test
    public void ConsultarEstoqueProdutoExistente() {
        sistemaEstoque.adicionarProduto("Grampeador", 7);
        int quantidade = sistemaEstoque.consultarEstoque("Grampeador");
        assertEquals(7, quantidade);
    }

    @Test
    public void ConsultarEstoqueProdutoInexistente() {
        int quantidade = sistemaEstoque.consultarEstoque("Inexistente");
        assertEquals(0, quantidade);
    }

    @Test
    public void VerificarDisponibilidadeComEstoqueSuficiente() {
        sistemaEstoque.adicionarProduto("Papel", 15);
        assertTrue(sistemaEstoque.verificarDisponibilidade("Papel", 10));
    }

    @Test
    public void VerificarDisponibilidadeComEstoqueInsuficiente() {
        sistemaEstoque.adicionarProduto("Papel", 4);
        assertFalse(sistemaEstoque.verificarDisponibilidade("Papel", 5));
    }

  }
