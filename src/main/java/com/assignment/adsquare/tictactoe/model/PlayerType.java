package com.assignment.adsquare.tictactoe.model;

public enum PlayerType {
    X("X"),O("O"),DRAW("DRAW");

    public final String label;

    private PlayerType(String label) {
        this.label = label;
    }
}