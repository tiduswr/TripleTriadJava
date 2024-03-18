# SimpleTripleTriadJava

SimpleTripleTriadJava é uma implementação simples do jogo Triple Triad em Java.

## Visão Geral do Jogo

Triple Triad é um jogo de cartas jogado em um tabuleiro 3x3, onde os jogadores competem para controlar espaços no tabuleiro com suas cartas. Cada carta possui valores numéricos em cada um de seus quatro lados (topo, fundo, esquerda e direita). Os jogadores colocam suas cartas no tabuleiro, e as cartas adjacentes são comparadas para determinar a conquista de espaços no tabuleiro.

## Funcionalidades Implementadas

- Criação de cartas com valores numéricos nos lados.
- Tabuleiro 3x3 para posicionar cartas.
- Colocação de cartas no tabuleiro.
- Implementação básica de regras do jogo Triple Triad.

## Como Jogar

1. Execute o jogo.
2. Posicione suas cartas no tabuleiro.
3. O jogo determinará automaticamente o vencedor com base nas regras do Triple Triad padrão.
4. Continue jogando até que um jogador vença ou saia digitando 'q'.

## Executando o Jogo

Para executar o jogo, você pode compilar e executar via maven.

```bash
cd SimpleTripleTriadJava
mvn clean package
java -jar target/card-game.jar
```

## GUI via Terminal

As cartas são coloridas de acordo com o Player.

```
🃏🃏🃏🃏 CARD GAME 🃏🃏🃏🃏

Jogada 3/9:
0---+  1---+  2---+  
| 6 |  |   |  |   |  
|3 2|  |   |  |   |  
| 2 |  |   |  |   |  
+---+  +---+  +---+  
3---+  4---+  5---+  
|   |  | 4 |  |   |  
|   |  |6 8|  |   |  
|   |  | 5 |  |   |  
+---+  +---+  +---+  
6---+  7---+  8---+  
|   |  |   |  |   |  
|   |  |   |  |   |  
|   |  |   |  |   |  
+---+  +---+  +---+  
        5:5        

Player 1:

0---+  1---+  2---+  3---+  
| A |  | 7 |  | 6 |  | A |  
|7 1|  |8 7|  |3 1|  |8 7|  
| 7 |  | 2 |  | 4 |  | 2 |  
+---+  +---+  +---+  +---+  

0. Pandemona
1. Ultima Weapon
2. Glacial Eye
3. Ward

🃏 Faça sua jogada (carta, tabuleiro) ou insira 'q' para sair:
```

## Contribuição

Contribuições são bem-vindas! Se você tiver sugestões de melhorias ou novas funcionalidades, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença

Este projeto está licenciado sob a [Licença MIT](https://opensource.org/licenses/MIT).