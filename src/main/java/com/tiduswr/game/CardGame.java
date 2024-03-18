package com.tiduswr.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tiduswr.game.gui.CardColor;
import com.tiduswr.game.gui.GUI;

public class CardGame {
    
    public final Player P1, P2;
    private final List<Card> CARDS;
    private final Board BOARD;
    private final String CARDS_FILE = "Cards.csv";
    private int jogadas;
    private final GUI gui;
    private Scanner scanner;

    public CardGame() throws IOException{
        scanner = new Scanner(System.in);
        jogadas = 0;
        gui = new GUI(this);
        CARDS = new ArrayList<>();
        BOARD = new Board();
        BOARD.addBoardListener(new GameLogic());
        loadCards();
        P1 = new Player(new Deck(), "Player 1", CardColor.BLUE);
        P2 = new Player(new Deck(), "Player 2", CardColor.GREEN);
        generateRandomDecks();
    }

    public int getJogadaAtual(){
        return jogadas + 1;
    }

    public Board getBoard(){
        return BOARD;
    }

    public void start(){
        CircularLinkedList<Player> playerTurn = new CircularLinkedList<>();
        boolean lastInputWasError = false;
        boolean boardPlaceHasCard = false;

        playerTurn.add(P1);
        playerTurn.add(P2);

        Collections.shuffle(playerTurn);

        Player player = playerTurn.removeFirst();

        while(jogadas < 9){
            gui.printBoard();
            gui.drawPlayerCards(player);

            if(lastInputWasError){
                System.out.println("Entrada inválida. Por favor, insira um inteiro referente ao index da carta separado por " +
                    "virgula de um inteiro referente ao tabuleiro. Ambas as entradas devem estar dentro dos limites do board(até 10) e dos limites das cartas na mão");
                lastInputWasError = false;
            }else if(boardPlaceHasCard){
                System.out.println("Esse local do tabuleiro ja possui uma carta!");
                    boardPlaceHasCard = false;
            }

            System.out.print("\nFaça sua jogada (carta, tabuleiro): ");
            String response = scanner.nextLine();

            Pattern pattern = Pattern.compile("(\\d+),\\s*(\\d+)");
            Matcher matcher = pattern.matcher(response);

            if (matcher.find()) {
                int cardIndex = Integer.parseInt(matcher.group(1));
                int tabuleiro = Integer.parseInt(matcher.group(2));
                
                if(tabuleiro >= (BOARD.getBoardSize() * BOARD.getBoardSize()) || cardIndex >= player.deck().handSize()){
                    lastInputWasError = true;
                }else{
                    var boardCard = BOARD.getCardFromIndex(tabuleiro);
                    if(boardCard != null){
                        boardPlaceHasCard = true;
                    }else{
                        var selectedCard = player.deck().retrieveCardByName(cardIndex);

                        BOARD.setCardFromIndex(tabuleiro, selectedCard);

                        jogadas++;
                        player = playerTurn.removeFirst();
                    }
                }
            } else {
                lastInputWasError = true;
            }
        }
        jogadas--;

        var winner = BOARD.checkWinner();
        gui.printBoard();
        
        if(winner == null){
            System.out.println("\nO jogo acabou em empate!");
        }else{
            System.out.println("\nO " + winner.name() + " ganhou a partida!");
        }

    }

    private void generateRandomDecks(){
        Player[] players = {P1, P2};

        for(var p : players){
            for(int i = 0; i < 5; i++){
                var card = CARDS.remove(0);
                card.cardOwner().setPlayer(p);
                p.deck().addCard(card);
            }
        }
    }

    private void loadCards() throws IOException{
        var is = this.getClass().getClassLoader().getResourceAsStream(CARDS_FILE);
        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(is))){
            String line;
            while((line = buffer.readLine()) != null){
                String[] row = line.split(",");

                Card card = new Card(row[0], 
                    Integer.parseInt(row[1]), 
                    Integer.parseInt(row[2]), 
                    Integer.parseInt(row[4]), 
                    Integer.parseInt(row[3]),
                    new CardOwner(null)
                );
                CARDS.add(card);
            }
        }

        Collections.shuffle(CARDS);
    }

}
