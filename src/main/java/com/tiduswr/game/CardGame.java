package com.tiduswr.game;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tiduswr.game.board.Board;
import com.tiduswr.game.card.GameCards;
import com.tiduswr.game.gui.CardColor;
import com.tiduswr.game.gui.GUI;
import com.tiduswr.game.player.Player;
import com.tiduswr.game.player.PlayerPoints;
import com.tiduswr.game.player.PlayerTurnSwitch;
import com.tiduswr.game.player.PlayerWithRandomDeck;
import com.tiduswr.game.utils.SoundPlayer;

public class CardGame {
    
    private final Player P1, P2;
    private final Board BOARD;
    private final GameCards CARDS;    
    private final GUI gui;
    private final Scanner scanner;
    private final int STARTING_POINTS;
    private final SoundPlayer musicTheme;
    private final SoundPlayer winTheme;
    private final String INPUT_MASK;

    private int jogadas;

    public CardGame() throws IOException{
        scanner = new Scanner(System.in);
        INPUT_MASK = "(\\d+),\\s*(\\d+)";        
        CARDS = new GameCards();
        STARTING_POINTS = 5;
        jogadas = 0;
        P1 = new PlayerWithRandomDeck(CARDS, "Player 1", CardColor.BLUE, new PlayerPoints(STARTING_POINTS));
        P2 = new PlayerWithRandomDeck(CARDS, "Player 2", CardColor.GREEN, new PlayerPoints(STARTING_POINTS));
        BOARD = new Board();
        musicTheme = new SoundPlayer("theme.wav");
        winTheme = new SoundPlayer("win.wav");
        gui = new GUI(this);

        BOARD.addBoardListener(new GameLogic());
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
        PlayerTurnSwitch playerTurn = new PlayerTurnSwitch(P1, P2);
        boolean lastInputWasError = false;
        boolean boardPlaceHasCard = false;

        Player player = playerTurn.next();
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

            Pattern pattern = Pattern.compile(INPUT_MASK);
            Matcher matcher = pattern.matcher(response);

            if (matcher.find()) {
                int cardIndex = Integer.parseInt(matcher.group(1));
                int tabuleiro = Integer.parseInt(matcher.group(2));
                
                if(tabuleiro >= (BOARD.getBoardSize() * BOARD.getBoardSize()) || cardIndex >= player.getDeck().handSize()){
                    lastInputWasError = true;
                }else{
                    var boardCard = BOARD.getCardFromIndex(tabuleiro);
                    if(boardCard != null){
                        boardPlaceHasCard = true;
                    }else{
                        var selectedCard = player.getDeck().retrieveCardByIndex(cardIndex);

                        BOARD.setCardFromIndex(tabuleiro, selectedCard);

                        jogadas++;
                        player = playerTurn.next();
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
                System.out.println("\nO " + gui.formatStringByPlayerColor(winner, winner.getName()) + " ganhou a partida 🎉🎉🎉");
                playAndStopWinTheme();
            }
        }
    }

    private void waitAndStopTheme() throws InterruptedException{
        Thread.sleep(5000);
        musicTheme.stop();
    }

    private void playAndStopWinTheme() throws InterruptedException{
        musicTheme.stop();
        winTheme.play();
        Thread.sleep(10500);
        winTheme.stop();
    }

    private Player checkWinner(){
        if(P1.getPoints().checkPoints() > P2.getPoints().checkPoints()){
            return P1;
        }else if(P2.getPoints().checkPoints() > P1.getPoints().checkPoints()){
            return P2;
        }

        return null;
    }

}
