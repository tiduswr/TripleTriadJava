package com.tiduswr.game;

import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tiduswr.game.board.Board;
import com.tiduswr.game.card.Cards;
import com.tiduswr.game.card.Deck;
import com.tiduswr.game.gui.CardColor;
import com.tiduswr.game.gui.GUI;
import com.tiduswr.game.player.Player;
import com.tiduswr.game.player.PlayerPoints;
import com.tiduswr.game.utils.CircularLinkedList;
import com.tiduswr.game.utils.SoundPlayer;

public class CardGame {
    
    private final Player P1, P2;
    private final Board BOARD;
    private final Cards CARDS;    
    private final GUI gui;
    private final Scanner scanner;
    private final int STARTING_POINTS;
    private final SoundPlayer musicTheme;
    private final SoundPlayer winTheme;

    private int jogadas;

    public CardGame() throws IOException{
        scanner = new Scanner(System.in);        
        CARDS = new Cards();
        STARTING_POINTS = 5;
        gui = new GUI(this);
        BOARD = new Board();
        P1 = new Player(new Deck(), "Player 1", CardColor.BLUE, new PlayerPoints(STARTING_POINTS));
        P2 = new Player(new Deck(), "Player 2", CardColor.GREEN, new PlayerPoints(STARTING_POINTS));
        musicTheme = new SoundPlayer("theme.wav");
        winTheme = new SoundPlayer("win.wav");
        jogadas = 0;

        BOARD.addBoardListener(new GameLogic());
        generateRandomDecks();
    }

    public int getJogadaAtual(){
        return jogadas + 1;
    }

    public Board getBoard(){
        return BOARD;
    }

    public Player getPlayer1() {
        return P1;
    }

    public Player getPlayer2() {
        return P2;
    }

    public void start() throws InterruptedException{
        CircularLinkedList<Player> playerTurn = new CircularLinkedList<>();
        boolean lastInputWasError = false;
        boolean boardPlaceHasCard = false;

        playerTurn.add(P1);
        playerTurn.add(P2);

        Collections.shuffle(playerTurn);

        Player player = playerTurn.removeFirst();
        musicTheme.loop();

        while(jogadas < 9){
            musicTheme.play();
            gui.drawBoard();
            gui.drawPlacar();
            gui.drawPlayerCards(player);

            if(lastInputWasError){
                System.out.println("⚠️ Entrada inválida. Por favor, insira um inteiro referente ao index da carta separado por " +
                    "virgula de um inteiro referente ao tabuleiro. Ambas as entradas devem estar dentro dos limites do board(até 10) e dos limites das cartas na mão");
                lastInputWasError = false;
            }else if(boardPlaceHasCard){
                System.out.println("⚠️ Esse local do tabuleiro ja possui uma carta!");
                    boardPlaceHasCard = false;
            }

            System.out.print("\n🃏 Faça sua jogada (carta, tabuleiro) ou insira 'q' para sair: ");
            String response = scanner.nextLine();

            if(response.equalsIgnoreCase("q")) break;

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
                        var selectedCard = player.deck().retrieveCardByIndex(cardIndex);

                        BOARD.setCardFromIndex(tabuleiro, selectedCard);

                        jogadas++;
                        player = playerTurn.removeFirst();
                    }
                }
            } else {
                lastInputWasError = true;
            }
        }
        
        if(jogadas == 9){
            jogadas--;            

            var winner = checkWinner();
            gui.drawBoard();
            gui.drawPlacar();
            
            if(winner == null){
                System.out.println("\nO jogo acabou em empate 😐😐😐");
                waitAndStopTheme();
            }else{
                System.out.println("\nO " + gui.formatStringByPlayerColor(player, winner.name()) + " ganhou a partida 🎉🎉🎉");
                playAndStopWinTheme();
            }
        }
    }

    private void waitAndStopTheme() throws InterruptedException{
        Thread.sleep(10500);
        musicTheme.stop();
    }

    private void playAndStopWinTheme() throws InterruptedException{
        musicTheme.stop();
        winTheme.play();
        Thread.sleep(10500);
        winTheme.stop();
    }

    private Player checkWinner(){
        if(P1.points().checkPoints() > P2.points().checkPoints()){
            return P1;
        }else if(P2.points().checkPoints() > P1.points().checkPoints()){
            return P2;
        }

        return null;
    }

    private void generateRandomDecks(){
        Player[] players = {P1, P2};

        for(var p : players){
            for(int i = 0; i < 5; i++){
                var card = CARDS.getCards().remove(0);
                card.cardOwner().setPlayer(p);
                p.deck().addCard(card);
            }
        }
    }

}
