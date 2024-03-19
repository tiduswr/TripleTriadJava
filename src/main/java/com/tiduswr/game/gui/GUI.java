package com.tiduswr.game.gui;

import com.tiduswr.game.CardGame;
import com.tiduswr.game.player.Player;
import com.tiduswr.game.utils.PrintUtils;

public class GUI {
    
    private final CardGame cardGame;

    public GUI(CardGame cardGame){
        this.cardGame = cardGame;
    }

    public void drawBoard(){
        System.out.println("\n🃏🃏🃏🃏 CARD GAME 🃏🃏🃏🃏\n\nJogada %s/9:".replace("%s", String.valueOf(cardGame.getJogadaAtual())));

        int placeCardIndex = 0;
        for(int i = 0; i < cardGame.getBoard().getBoardSize(); i++){
            String top = "";
            String bodyr1 = "";
            String bodyr2 = "";
            String bodyr3 = "";
            String bottom = "";

            for(int j = 0; j < cardGame.getBoard().getBoardSize(); j++){
                var card = cardGame.getBoard().getCard(i, j);

                if(card == null){
                    top    += placeCardIndex + "---+  ";
                    bodyr1 += "|   |  ";
                    bodyr2 += "|   |  ";
                    bodyr3 += "|   |  ";
                    bottom += "+---+  ";
                }else{

                    switch (card.cardOwner().getPlayer().getCardColor()) {
                        case BLUE:
                            top    += PrintUtils.formatBlue(placeCardIndex + "---+  ");
                            bodyr1 += PrintUtils.formatBlue("| %t |  ".replace("%t", convertValue(card.attackTop())));
                            bodyr2 += PrintUtils.formatBlue("|%l %r|  ".replace("%l", convertValue(card.attackLeft()))
                                .replace("%r", convertValue(card.attackRight())));
                            bodyr3 += PrintUtils.formatBlue("| %b |  ".replace("%b", convertValue(card.attackBottom())));
                            bottom += PrintUtils.formatBlue("+---+  ");
                            break;
                        case GREEN:
                            top    += PrintUtils.formatGreen(placeCardIndex + "---+  ");
                            bodyr1 += PrintUtils.formatGreen("| %t |  ".replace("%t", convertValue(card.attackTop())));
                            bodyr2 += PrintUtils.formatGreen("|%l %r|  ".replace("%l", convertValue(card.attackLeft()))
                                .replace("%r", convertValue(card.attackRight())));
                            bodyr3 += PrintUtils.formatGreen("| %b |  ".replace("%b", convertValue(card.attackBottom())));
                            bottom += PrintUtils.formatGreen("+---+  ");
                            break;
                        default:
                            top    += placeCardIndex + "---+  ";
                            bodyr1 += "| %t |  ".replace("%t", convertValue(card.attackTop()));
                            bodyr2 += "|%l %r|  ".replace("%l", convertValue(card.attackLeft()))
                                .replace("%r", convertValue(card.attackRight()));
                            bodyr3 += "| %b |  ".replace("%b", convertValue(card.attackBottom()));
                            bottom += "+---+  ";
                            break;
                    }

                }

                placeCardIndex++;                
            }

            top += "\n";
            bottom += "\n"; 
            var body = bodyr1 + "\n" + bodyr2 + "\n" + bodyr3 + "\n";
            System.out.print(top + body + bottom);
        }
    }

    public void drawPlacar(){
        var p1 = cardGame.getPlayer1();
        var p2 = cardGame.getPlayer2();

        var prefixAndSufix = " ".repeat(8);
        var placar = prefixAndSufix + 
            formatStringByPlayerColor(p1,String.valueOf(p1.getPoints().checkPoints())) 
            + ":" + 
            formatStringByPlayerColor(p2, String.valueOf(p2.getPoints().checkPoints())) 
            + prefixAndSufix;

        System.out.println(placar);
    }

    public String formatStringByPlayerColor(Player p, String s){
        switch (p.getCardColor()) {
            case BLUE:
                return PrintUtils.formatBlue(s);
            case GREEN:
                return PrintUtils.formatGreen(s);
            default:
                return s;
        }
    }

    public void drawPlayerCards(Player p){

        var playerLabel = "\n" + p.getName() + ":\n\n";
        switch (p.getCardColor()) {
            case BLUE:
                PrintUtils.printBlue(playerLabel);
                break;
            case GREEN:
                PrintUtils.printGreen(playerLabel);
                break;
            default:
                System.out.println(playerLabel);
                break;
        }

        String top = "";
        String bodyr1 = "";
        String bodyr2 = "";
        String bodyr3 = "";
        String bottom = "";

        for(int i = 0; i < p.getDeck().handSize(); i++){
            var card = p.getDeck().checkCardByIndex(i);

            top    += i + "---+  ";
            bodyr1 += "| %t |  ".replace("%t", convertValue(card.attackTop()));
            bodyr2 += "|%l %r|  ".replace("%l", convertValue(card.attackLeft()))
                .replace("%r", convertValue(card.attackRight()));
            bodyr3 += "| %b |  ".replace("%b", convertValue(card.attackBottom()));
            bottom += "+---+  ";
        }

        top += "\n";
        bottom += "\n"; 
        var body = bodyr1 + "\n" + bodyr2 + "\n" + bodyr3 + "\n";
        var playerCard = top + body + bottom;
        switch (p.getCardColor()) {
            case BLUE:
                PrintUtils.printBlue(playerCard);
                break;
            case GREEN:
                PrintUtils.printGreen(playerCard);
                break;
            default:
                System.out.print(playerCard);
                break;
        }
        System.out.println();

        var cards = p.getDeck().cards();
        for(int i = 0; i < cards.size(); i++){
            var cardName = i + ". " + cards.get(i).name();
            switch (p.getCardColor()) {
                case BLUE:
                    PrintUtils.printBlue(cardName);
                    break;
                case GREEN:
                    PrintUtils.printGreen(cardName);
                    break;
                default:
                    System.out.print(cardName);
                    break;
            }

            System.out.println();
            
        }

    }

    private String convertValue(int x){
        return x == 10 ? "A" : String.valueOf(x);
    }

}
