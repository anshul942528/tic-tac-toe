package com.anshul.driver;

import com.anshul.game.Game;

public class Driver {

    public static void main(String[] args) {

        Game game = new Game(3);
        game.loadGame();

        game.playGame();
    }
}
