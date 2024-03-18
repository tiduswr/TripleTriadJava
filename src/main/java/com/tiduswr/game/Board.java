package com.tiduswr.game;

import java.util.ArrayList;
import java.util.List;

public class Board {
    
    private final Card[][] BOARD;
    private final int BOARD_SIZE;
    private final List<BoardListeners> BOARD_LISTENERS;

    public Board(){
        BOARD_SIZE = 3;
        BOARD = new Card[BOARD_SIZE][BOARD_SIZE];
        BOARD_LISTENERS = new ArrayList<>();
    }

    public int getBoardSize(){
        return BOARD_SIZE;
    }

    public Card getCardFromIndex(int index){
        if(index >= 0 && index < BOARD_SIZE * BOARD_SIZE) {
            int row = index / BOARD_SIZE;
            int col = index % BOARD_SIZE;
            return getCard(row, col);
        } else {
            return null;
        }
    }

    public void setCardFromIndex(int index, Card card){
        if(index >= 0 && index < BOARD_SIZE * BOARD_SIZE) {
            int row = index / BOARD_SIZE;
            int col = index % BOARD_SIZE;
            putCard(row, col, card);
        }
    }

    public void putCard(int row, int col, Card card){
        if(rowAndColIsInBoard(row, col) && currPositionIsNull(row, col)) {
            BOARD[row][col] = card;
            notifyBoardListeners(row, col);
        }
    }

    public Card getCard(int row, int col){
        return rowAndColIsInBoard(row, col) ? BOARD[row][col] : null;
    }

    public void addBoardListener(BoardListeners boardListener){
        if(!BOARD_LISTENERS.contains(boardListener))
            BOARD_LISTENERS.add(boardListener);
    }

    private boolean rowAndColIsInBoard(int row, int col){
        return (row >= 0 && col >= 0) && (row < BOARD_SIZE && col < BOARD_SIZE);
    }

    private boolean currPositionIsNull(int row, int col){
        return BOARD[row][col] == null;
    }

    private void notifyBoardListeners(int row, int col){
        BOARD_LISTENERS.forEach(listener -> {
            listener.update(
                new CardPlacedEvent(
                    getCard(row - 1, col),
                    getCard(row + 1, col),
                    getCard(row, col - 1),
                    getCard(row, col + 1),
                    getCard(row, col)
                )
            );
        });
    }

}
