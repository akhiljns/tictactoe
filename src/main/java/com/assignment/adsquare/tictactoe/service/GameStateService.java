package com.assignment.adsquare.tictactoe.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.adsquare.tictactoe.exception.IllegalMoveException;
import com.assignment.adsquare.tictactoe.model.BoardPosition;
import com.assignment.adsquare.tictactoe.model.Player;
import com.assignment.adsquare.tictactoe.repository.BoardPositionRepository;

@Service
public class GameStateService {

    public Boolean gameOver;
    private final BoardPositionRepository boardPositionRepository;

    @Autowired
    public GameStateService(BoardPositionRepository boardPositionRepository) {
        this.boardPositionRepository = boardPositionRepository;
        this.gameOver = false;
    }

    public Boolean isGameOver() {
        return gameOver;
    }

    public void endGame(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void initializeBoard() {
        for (int i = 0; i < 9; i++) {
            boardPositionRepository.saveAndFlush(new BoardPosition(Integer.toString(i), null));
        }
    }

    public void resetGame() {
        boardPositionRepository.deleteAll();
        initializeBoard();
    }

    public Map<String, String> getState() {
        Map<String, String> boardMap = new HashMap<>();
        for (BoardPosition b : boardPositionRepository.findAll()) {
            boardMap.put(b.getPosition(), b.getPlayedValue());
        }
        return boardMap;
    }

    public void updateState(String position, Player player) {
        BoardPosition boardPosition = boardPositionRepository.getReferenceById(position);
        if (boardPositionRepository.getReferenceById(position).getPlayedValue() != null) {
            throw new IllegalMoveException("box already filled");
        }
        boardPosition.setPlayedValue(player.getPlayerType().name());
        boardPositionRepository.save(boardPosition);
    }

    public String checkWinner() {
        List<BoardPosition> board = boardPositionRepository.findAll();
        for (int a = 0; a < 8; a++) {
            String line = "";
            switch (a) {
                case 0:
                    line = board.get(0).getPlayedValue() + board.get(1).getPlayedValue()
                            + board.get(2).getPlayedValue();
                    break;
                case 1:
                    line = board.get(3).getPlayedValue() + board.get(4).getPlayedValue()
                            + board.get(5).getPlayedValue();
                    break;
                case 2:
                    line = board.get(6).getPlayedValue() + board.get(7).getPlayedValue()
                            + board.get(8).getPlayedValue();
                    break;
                case 3:
                    line = board.get(0).getPlayedValue() + board.get(3).getPlayedValue()
                            + board.get(6).getPlayedValue();
                    break;
                case 4:
                    line = board.get(1).getPlayedValue() + board.get(4).getPlayedValue()
                            + board.get(7).getPlayedValue();
                    break;
                case 5:
                    line = board.get(2).getPlayedValue() + board.get(5).getPlayedValue()
                            + board.get(8).getPlayedValue();
                    break;
                case 6:
                    line = board.get(0).getPlayedValue() + board.get(4).getPlayedValue()
                            + board.get(8).getPlayedValue();
                    break;
                case 7:
                    line = board.get(2).getPlayedValue() + board.get(4).getPlayedValue()
                            + board.get(6).getPlayedValue();
                    break;
            }
            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        if (board.stream().filter(Objects::nonNull).collect(Collectors.toList()).size() == 8) {
            return "draw";
        }

        return null;
    }
}