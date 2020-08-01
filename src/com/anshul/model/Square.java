package com.anshul.model;

import com.anshul.constant.Symbol;

public class Square {

    private int row;

    private int col;

    private Symbol symbol;

    public Square(int row, int col, Symbol symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return symbol.getSymbol() + "";
    }
}
