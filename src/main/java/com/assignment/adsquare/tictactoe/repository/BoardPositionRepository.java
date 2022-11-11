package com.assignment.adsquare.tictactoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.adsquare.tictactoe.model.BoardPosition;

@Repository
public interface BoardPositionRepository extends JpaRepository<BoardPosition, String> {}