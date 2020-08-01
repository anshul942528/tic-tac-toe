package com.anshul.input;

import com.anshul.model.Board;
import com.anshul.constant.GameConstant;
import com.anshul.constant.Symbol;
import com.anshul.model.Move;

import java.util.Scanner;

public class UserInteraction implements GameConstant {

    private static final UserInteraction userInteraction = new UserInteraction();

    private UserInteraction() {

    }

    public static UserInteraction getInstance() {
        return userInteraction;
    }

    private Scanner scanner = new Scanner(System.in);

    public String getString(String message) {
        System.out.print(message + DEL);
        return scanner.nextLine();
    }

    public int getInt(String message) {
        System.out.print(message + DEL);
        try {
            return scanner.nextInt();
        } catch (Exception ex) {
            scanner.nextLine();
            return 0;
        }
    }

    public Move getMove(Board board, int size) {
        String userInput = userInteraction.getString("Enter , separated numbers for cell where want to put symbol");
        while(!validateRowCol(userInput, board, size))
            userInput = userInteraction.getString("Enter , separated numbers for cell where want to put symbol");
        String[] cell = userInput.split(",");
        return new Move(Integer.parseInt(cell[0]), Integer.parseInt(cell[1]));
    }

    private boolean validateRowCol(String userInput, Board board, int size) {
        String[] cell = userInput.split(",");
        if(cell.length != 2){
            System.out.println("Invalid input, re-enter cell where want to put symbol");
            return false;
        }

        try {
            int row = Integer.parseInt(cell[0]);
            int col = Integer.parseInt(cell[1]);
            if(row < 0 || row >= size || col < 0 || col >= size) {
                System.out.println("Invalid input, re-enter cell where want to put symbol");
                return false;
            }
            if(board.getCell(row, col) != Symbol.N){
                System.out.println("Cell already filled, re-enter cell where want to put symbol");
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Invalid input, re-enter cell where want to put symbol");
            return false;
        }
        return true;
    }

    public int getTurn() {
        int turn = userInteraction.getInt("Who will go first Player 1/2");
        while (turn < 1 || turn > 2) {
            System.out.println("Invalid selection please select from 1/2");
            turn = userInteraction.getInt("Who will go first Player 1/2");
        }
        return turn;
    }

    public Symbol getSymbol() {
        String symbol = userInteraction.getString("Choose Symbol from X/O");
        while(!validateSymbol(symbol))
            symbol = userInteraction.getString("Choose Symbol from X,O");
        return symbol.toUpperCase().equals("X") ? Symbol.X : Symbol.O;
    }

    private boolean validateSymbol(String symbol) {
        if(symbol.length() != 1) {
            System.out.println("Invalid Symbol");
            return false;
        }

        if(symbol.toUpperCase().equals("X") || symbol.toUpperCase().equals("O"))
            return true;
        else {
            System.out.println("Invalid Symbol");
            return false;
        }
    }

    public String getGameChoice() {
        String input = userInteraction.getString("Want to start new game Y/N");
        while (!input.toUpperCase().equals("Y") && !input.toUpperCase().equals("N")){
            System.out.println("Invalid Selection please select Y/N");
            input = userInteraction.getString("Want to start new game Y/N");
        }
        return input.toUpperCase();
    }

    public void goNextLine() {
        scanner.nextLine();
    }
}
