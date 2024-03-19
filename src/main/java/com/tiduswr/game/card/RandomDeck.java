package com.tiduswr.game.card;

import java.util.Random;

import com.tiduswr.game.player.Player;

public class RandomDeck extends Deck{
    
    public RandomDeck(GameCards gameCards, Player player){
        generate(gameCards, player);
    }

    private void generate(GameCards gameCards, Player player){
        final var r = new Random();
        final var cardsSize = gameCards.getCards().size();

        for(int i = 0; i < 5; i++){
            final int randomIndex = r.nextInt(cardsSize);
            final var randomCard = gameCards.getCards().get(randomIndex).copy(player);
            addCard(randomCard);
        }
    }

}
