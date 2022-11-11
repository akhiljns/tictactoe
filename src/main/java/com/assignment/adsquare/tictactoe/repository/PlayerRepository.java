package com.assignment.adsquare.tictactoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.adsquare.tictactoe.model.Player;
import com.assignment.adsquare.tictactoe.model.PlayerType;

@Repository
public interface PlayerRepository extends JpaRepository<Player, PlayerType> {
}