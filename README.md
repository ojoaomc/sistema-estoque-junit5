# Sistema de Estoque
Exemplo para avaliarmos a cobertura de testes de 0% a +70%

Explicação do Código:

- SistemaEstoque Class: Representa o sistema de controle de estoque.

- estoque (Map): Utiliza um HashMap para armazenar os produtos e suas respectivas quantidades em estoque. 
  A chave é o nome do produto (String) e o valor é a quantidade (Integer).
  
- historicoTransacoes (List): Uma ArrayList para registrar as operações de adição e remoção de produtos, facilitando o rastreamento das movimentações.

- adicionarProduto(String nomeProduto, int quantidade):
  Adiciona uma determinada quantidade de um produto ao estoque.
  Se o produto já existir, incrementa a quantidade.
  Registra a transação no histórico.
  Lança uma IllegalArgumentException se o nome do produto for nulo ou vazio, ou se a quantidade for menor ou igual a zero.

- removerProduto(String nomeProduto, int quantidade):
  Remove uma determinada quantidade de um produto do estoque.
  Verifica se o produto existe e se há estoque suficiente.
  Registra a transação no histórico.
  Remove o produto do estoque se a quantidade após a remoção for zero.
  Lança uma IllegalArgumentException se o nome do produto for nulo ou vazio, se a quantidade for menor ou igual a zero, ou se não houver estoque suficiente.

- consultarEstoque(String nomeProduto):
  Retorna a quantidade atual em estoque de um determinado produto.
  Retorna 0 se o produto não estiver cadastrado.
  Lança uma IllegalArgumentException se o nome do produto for nulo ou vazio.

- obterHistoricoTransacoes():
  Retorna uma cópia da lista de histórico de transações.
  verificarDisponibilidade(String nomeProduto, int quantidadeNecessaria):
  Verifica se há quantidade suficiente de um determinado produto em estoque para atender a uma necessidade.
  Lança uma IllegalArgumentException se o nome do produto for nulo ou vazio, ou se a quantidade necessária for menor ou igual a zero.