package com.assignment.adsquare.tictactoe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.adsquare.tictactoe.model.Player;
import com.assignment.adsquare.tictactoe.model.PlayerType;
import com.assignment.adsquare.tictactoe.repository.PlayerRepository;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getCurrentPlayers() {
        return playerRepository.findAll();
    }

    public void initializePlayers(String x, String o) {
        Player playerx = new Player(PlayerType.X, x);
        Player playero = new Player(PlayerType.O, o);

        playerRepository.saveAndFlush(playerx);
        playerRepository.saveAndFlush(playero);
    }

    public Player getPlayer(PlayerType player) {
        return playerRepository.getReferenceById(player);
    }

    public void resetPlayers() {
        playerRepository.deleteAll();
    }

}