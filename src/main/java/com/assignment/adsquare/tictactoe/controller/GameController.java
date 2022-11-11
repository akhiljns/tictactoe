package com.assignment.adsquare.tictactoe.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.adsquare.tictactoe.dto.TurnRequest;
import com.assignment.adsquare.tictactoe.dto.TurnResponse;
import com.assignment.adsquare.tictactoe.model.Player;
import com.assignment.adsquare.tictactoe.service.ConcurrencyManagementService;
import com.assignment.adsquare.tictactoe.service.GameStateService;
import com.assignment.adsquare.tictactoe.service.PlayerService;

@RestController
public class GameController {

    private final GameStateService gameStateService;
    private final PlayerService playerService;
    private final ConcurrencyManagementService managementService;

    @Autowired
    public GameController(GameStateService gameStateService, PlayerService playerService,
            ConcurrencyManagementService managementService) {
        this.gameStateService = gameStateService;
        this.playerService = playerService;
        this.managementService = managementService;
    }

    @PostMapping("/initialize")
    public void initializePlayers(@RequestParam String playerx, @RequestParam String playero) {
        playerService.initializePlayers(playerx, playero);
        gameStateService.initializeBoard();
    }

    @GetMapping("/players")
    public List<Player> getPlayers() {
        return playerService.getCurrentPlayers();
    }

    @GetMapping("/state")
    public Map<String, String> getState() {
        return gameStateService.getState();
    }

    @PostMapping("/turn")
    public TurnResponse turn(@RequestBody @Valid TurnRequest request) {
        return managementService.playTurn(request);
    }

    @PostMapping("/reset")
    public void resetGame() {
        gameStateService.resetGame();
        playerService.resetPlayers();
        managementService.reset();
    }
}