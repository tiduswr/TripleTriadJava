# SimpleTripleTriadJava

SimpleTripleTriadJava é uma implementação simples do jogo Triple Triad em Java.

## Visão Geral do Jogo

Triple Triad é um jogo de cartas jogado em um tabuleiro 3x3, onde os jogadores competem para controlar espaços no tabuleiro com suas cartas. Cada carta possui valores numéricos em cada um de seus quatro lados (topo, fundo, esquerda e direita). Os jogadores colocam suas cartas no tabuleiro, e as cartas adjacentes são comparadas para determinar a conquista de espaços no tabuleiro.

## Funcionalidades Implementadas

- Criação de cartas com valores numéricos nos lados.
- Tabuleiro 3x3 para posicionar cartas.
- Colocação de cartas no tabuleiro.
- Determinação do vencedor com base na ocupação do tabuleiro.
- Implementação básica de regras do jogo Triple Triad.

## Como Jogar

1. Execute o jogo.
2. Posicione suas cartas no tabuleiro.
3. O jogo determinará automaticamente o vencedor com base nas regras do Triple Triad.
4. Continue jogando até que um jogador vença.

## Executando o Jogo

Para executar o jogo, você pode compilar e executar a classe principal `Main` no seu ambiente Java preferido.

```bash
javac Main.java
java Main
```

## GUI via Terminal

```bash
CARD GAME

Jogada 1/9:
0---+  1---+  2---+  
|   |  |   |  |   |  
|   |  |   |  |   |  
|   |  |   |  |   |  
+---+  +---+  +---+  
3---+  4---+  5---+  
|   |  |   |  |   |  
|   |  |   |  |   |  
|   |  |   |  |   |  
+---+  +---+  +---+  
6---+  7---+  8---+  
|   |  |   |  |   |  
|   |  |   |  |   |  
|   |  |   |  |   |  
+---+  +---+  +---+  

Player 1:

0---+  1---+  2---+  3---+  4---+  
| 4 |  | 6 |  | 6 |  | 7 |  | 1 |  
|2 4|  |4 9|  |6 3|  |5 2|  |8 8|  
| 7 |  | A |  | 1 |  | 3 |  | 4 |  
+---+  +---+  +---+  +---+  +---+  

0. Death Claw
1. Seifer
2. Armadodo
3. Abyss Worm
4. Oilboyle

Faça sua jogada (carta, tabuleiro): 
```

## Contribuição

Contribuições são bem-vindas! Se você tiver sugestões de melhorias ou novas funcionalidades, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [Licença MIT](https://opensource.org/licenses/MIT).