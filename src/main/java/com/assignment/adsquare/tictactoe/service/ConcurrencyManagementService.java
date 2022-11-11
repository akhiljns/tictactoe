package com.assignment.adsquare.tictactoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.adsquare.tictactoe.dto.TurnRequest;
import com.assignment.adsquare.tictactoe.dto.TurnResponse;
import com.assignment.adsquare.tictactoe.model.Player;
import com.assignment.adsquare.tictactoe.model.PlayerType;

@Service
public class ConcurrencyManagementService {

    private final GameStateService gameStateService;
    private final PlayerService playerService;

    @Autowired
    public ConcurrencyManagementService(GameStateService gameStateService, PlayerService playerService) {
        this.gameStateService = gameStateService;
        this.playerService = playerService;
    }

    private String lockedResource = "NONE";

    public TurnResponse playTurn(TurnRequest request) {
        if (gameStateService.isGameOver()) {
            System.out.println("Game is already over!");
            return new TurnResponse(findWinner().getPlayerType().name(), findWinner().getPlayerName(),
                    gameStateService.getState());
        } else {

            synchronized (lockedResource) {
                if (request.getPlayerId().equals(lockedResource)) {
                    return new TurnResponse("Done", "None",
                            gameStateService.getState());
                }
                gameStateService.updateState(String.valueOf(request.getPosition()),
                        playerService.getPlayer(PlayerType.valueOf(request.getPlayerId())));
                lockedResource = request.getPlayerId();
                return new TurnResponse(findWinner() == null ? "no winner yet" : findWinner().getPlayerType().name(),
                        findWinner() == null ? "None" : findWinner().getPlayerName(), gameStateService.getState());
            }
        }
    }

    private Player findWinner() {
        String winner = gameStateService.checkWinner();
        Player playerWinner;

        if (winner != null) {
            switch (winner) {
                case "X":
                case "O":
                    playerWinner = playerService.getPlayer(PlayerType.valueOf(winner));
                    gameStateService.endGame(true);
                    break;
                case "draw":
                    playerWinner = new Player(PlayerType.DRAW, "No one wins");
                    gameStateService.endGame(true);
                    break;
                default:
                    playerWinner = null;
            }
            return playerWinner;
        }

        return null;
    }

    public void reset() {
        lockedResource = "NONE";
    }

}