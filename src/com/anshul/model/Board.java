package com.anshul.model;

import com.anshul.constant.Symbol;
import com.anshul.model.Move;
import com.anshul.model.Player;
import com.anshul.model.Square;

public class Board {

    private Square[][] board;

    public Board(int size) {

        board = new Square[size][size];
        for(int row = 0; row < size; row++) {
            for(int col = 0; col < size; col++)
                board[row][col] = new Square(row, col, Symbol.N);
        }
    }

    public void resetBoard() {
        if (board == null) {
            System.out.println("Board is not yet initialized");
            return;
        }

        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board.length; col++)
                board[row][col] = new Square(row, col, Symbol.N);
        }
    }

    public void printBoard(){
        if (board == null) {
            System.out.println("Board is not yet initialized");
            return;
        }

        for(int row = 0; row < board.length; row++) {
            System.out.print("\t\t\t");
            for(int col = 0; col < board.length; col++)
                System.out.print(board[row][col] + "\t");
            System.out.println();
        }
    }

    public void placeSymbol(Player currentPlayer, Move move) {
        board[move.getRow()][move.getCol()].setSymbol(currentPlayer.getSymbol());
        printBoard();
    }

    public Symbol getCell(int row, int col) {
        return board[row][col].getSymbol();
    }

    public boolean checkBoard(Move move, Player player) {
        boolean rowCheck = true;
        boolean colCheck = true;
        boolean diagonalCheck1 = false;
        boolean diagonalCheck2 = false;
        for(int col = 0; col < board.length; col++) {
            if (board[move.getRow()][col].getSymbol() != player.getSymbol()) {
                rowCheck = false;
                break;
            }
        }

        for (int row = 0; row < board.length; row++){
            if(board[row][move.getCol()].getSymbol() != player.getSymbol()){
                colCheck = false;
                break;
            }
        }

        if(move.getRow() == move.getCol() || ((move.getRow() + move.getCol()) == board.length - 1)){
            diagonalCheck1 = true;
            diagonalCheck2 = true;
            for(int row = 0; row < board.length; row++) {
                int col = board.length - 1 - row;
                if(board[row][row].getSymbol() != player.getSymbol())
                    diagonalCheck1 = false;
                if(board[row][col].getSymbol() != player.getSymbol())
                    diagonalCheck2 = false;
            }
        }
        return rowCheck || colCheck || diagonalCheck1 || diagonalCheck2;
    }
}
