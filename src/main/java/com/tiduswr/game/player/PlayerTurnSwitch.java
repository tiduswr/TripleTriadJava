package com.tiduswr.game.player;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class PlayerTurnSwitch {

    private final LinkedList<Player> playersList;
    private int currentPlayerIndex;

    public PlayerTurnSwitch(Player ...players){
        if (players.length == 0) {
            throw new NoSuchElementException("Pelo menos um jogador deve ser fornecido.");
        }

        playersList = new LinkedList<>(List.of(players));
        Collections.shuffle(playersList);
        currentPlayerIndex = 0;
    }

    public Player next() {
        Player currentPlayer = playersList.get(currentPlayerIndex);
        currentPlayerIndex = (currentPlayerIndex + 1) % playersList.size(); // Avança para o próximo jogador circularmente
        return currentPlayer;
    }
}
