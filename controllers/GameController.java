package controllers;

import models.Game;
import models.GameState;
import models.Player;
import models.PlayerType;
import strategies.WinningStrategy;

import java.util.List;
public class GameController {

    public Game startGame(
            int dimension,
            List<Player> players,
            List<WinningStrategy> winningStrategies
    ){
            // 1. no of Players
            // 2. Every player should have a diff symbol
            // 3. There should be only 1 bot in the game
            return Game
                    .getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .setWinningStrategies(winningStrategies)
                    .build();
    }

    public void displayBoard(Game game){
        game.displayBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public GameState checkState(Game game){
        return game.getGameState();
    }

    public Player getWinner(Game game){
        return null;
    }
    public void undo(Game game){
        game.undo();
    }
}
