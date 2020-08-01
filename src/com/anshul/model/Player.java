package com.anshul.model;

import com.anshul.constant.Symbol;

public class Player {

    private String name;

    private Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String toString() {
        return "Player(name = " + name + ", symbol = " + symbol.getSymbol() + ")";
    }
}
