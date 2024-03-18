package com.tiduswr.game.gui;

import com.tiduswr.game.Card;
import com.tiduswr.game.CardGame;
import com.tiduswr.game.Player;

public class GUI {
    
    private final CardGame cardGame;

    public GUI(CardGame cardGame){
        this.cardGame = cardGame;
    }

    public void printBoard(){
        System.out.println("\nCARD GAME\n\nJogada %s/9:".replace("%s", String.valueOf(cardGame.getJogadaAtual())));

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

                    switch (card.cardOwner().getPlayer().cardColor()) {
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

    public void drawPlayerCards(Player p){

        var playerLabel = "\n" + p.name() + ":\n\n";
        switch (p.cardColor()) {
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

        for(int i = 0; i < p.deck().handSize(); i++){
            var card = p.deck().checkCardByIndex(i);

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
        switch (p.cardColor()) {
            case BLUE:
                PrintUtils.printBlue(top + body + bottom);
                break;
            case GREEN:
                PrintUtils.printGreen(top + body + bottom);
                break;
            default:
                System.out.print(top + body + bottom);
                break;
        }
        System.out.println();

        var cards = p.deck().cards();
        for(int i = 0; i < cards.size(); i++){
            switch (p.cardColor()) {
                case BLUE:
                    PrintUtils.printBlue(i + ". " + cards.get(i).name());
                    break;
                case GREEN:
                    PrintUtils.printGreen(i + ". " + cards.get(i).name());
                    break;
                default:
                    System.out.print(i + ". " + cards.get(i).name());
                    break;
            }

            System.out.println();
            
        }

    }

    private String convertValue(int x){
        return x == 10 ? "A" : String.valueOf(x);
    }

}
