package com.assignment.adsquare.tictactoe.dto;

import java.util.Map;

public class TurnResponse {
    public Boolean gameOver = false;
    public String winnerType;
    public String winnerName;
    public Map<String, String> state;

    public TurnResponse(String winnerType, String winnerName, Map<String, String> state) {
        if (winnerType == "X" || winnerType == "O") {
            this.winnerType = winnerType;
            this.winnerName = winnerName;
            this.gameOver = true;
        }
        if (winnerType.equalsIgnoreCase("done")) {
            this.winnerType="you have already played, wait for next player to finish their turn";
        }
        this.state = state;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setState(Map<String, String> state) {
        this.state = state;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public Map<String, String> getState() {
        return state;
    }

}