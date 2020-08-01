package com.anshul.constant;

public enum Symbol {

    X('X'),
    O('O'),
    N('_');

    private char symbol;

    public char getSymbol(){
        return symbol;
    }

    Symbol(char symbol) {
        this.symbol = symbol;
    }
}
