package com.anshul.game;

import com.anshul.model.Board;
import com.anshul.constant.GameStatus;
import com.anshul.constant.Symbol;
import com.anshul.input.UserInteraction;
import com.anshul.model.Move;
import com.anshul.model.Player;

public class Game {

    private int size;

    private int inputCount = 0;

    private Board board;

    private Player player1;

    private Player player2;

    private Player currentPlayer;

    private GameStatus gameStatus;

    private UserInteraction userInteraction;

    public Game(int size) {
        this.size = size;
        this.board = new Board(size);
        this.userInteraction = UserInteraction.getInstance();
    }

    public void loadGame() {
        String firstPlayerName = userInteraction.getString("Enter first player name");
        Symbol firstPlayerSymbol = userInteraction.getSymbol();
        String secondPlayerName = userInteraction.getString("Enter second player name");
        Symbol secondPlayerSymbol = firstPlayerSymbol == Symbol.X ? Symbol.O : Symbol.X;

        player1 = new Player(firstPlayerName, firstPlayerSymbol);
        player2 = new Player(secondPlayerName, secondPlayerSymbol);

        int firstTurn = userInteraction.getTurn();
        currentPlayer = firstTurn == 1 ? player1 : player2;
        gameStatus = GameStatus.PLAYING;
        printGameState();
    }

    private void printGameState() {
        System.out.println("===================================");
        Player other = currentPlayer == player1 ? player2 : player1;
        System.out.println(currentPlayer + " <");
        System.out.println(other);
        System.out.println("GameStatus : " + gameStatus);
        System.out.println("-----------------------------------");
        board.printBoard();
        System.out.println("===================================");
    }

    public void playGame() {
        userInteraction.goNextLine();
        while (true) {
            //1. get a valid User Move
            Move move = userInteraction.getMove(board, size);
            inputCount++;

            //2. place the symbol on board
            board.placeSymbol(currentPlayer, move);

            //3. check game status
            checkGameStatus(move);

            //4. switch user turn if play is on
            if(gameStatus.equals(GameStatus.PLAYING))
                switchTurn();

            if(gameStatus.equals(GameStatus.RESTART))
                gameStatus = GameStatus.PLAYING;
        }
    }

    private void checkGameStatus(Move move) {
        boolean gameOver = false;
        if(board.checkBoard(move, currentPlayer)) {
            gameOver = true;
            gameStatus = currentPlayer.getSymbol() == Symbol.X ? GameStatus.X_WINS : GameStatus.O_WINS;
            System.out.println("Player " + currentPlayer.getName() + " with symbol " + currentPlayer.getSymbol() + " won");
        }

        if(inputCount == size * size) {
            gameOver = true;
            gameStatus = GameStatus.TIE;
            System.out.println("No more moves possible game TIE");
        }

        if(gameOver){
            String breakContinue = userInteraction.getGameChoice();
            if (breakContinue.equals("Y")) {
                inputCount = 0;
                board.resetBoard();
                int firstTurn = userInteraction.getTurn();
                currentPlayer = firstTurn == 1 ? player1 : player2;
                gameStatus = GameStatus.RESTART;
                printGameState();
            } else
                System.exit(0);
        }
    }

    private void switchTurn() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }
}
