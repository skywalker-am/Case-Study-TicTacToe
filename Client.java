import controllers.GameController;
import models.*;
import strategies.ColumnWinningStrategy;
import strategies.DiagonalWinningStrategy;
import strategies.RowWinningStrategy;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("Game is Starting!");

        try{
            int dimension = 3;
            List<Player> players = new ArrayList<>();
            players.add(new Player(1 , "Omi", PlayerType.HUMAN , new Symbol('X' , "Green")));
            players.add(new Player(2 , "Abhishek", PlayerType.HUMAN , new Symbol('0' , "Blue")));

            List<WinningStrategy> winningStrategies = new ArrayList<>();
            winningStrategies.add(new RowWinningStrategy());
            winningStrategies.add(new ColumnWinningStrategy());
            winningStrategies.add(new DiagonalWinningStrategy());

            Game game = gameController.startGame(dimension , players , winningStrategies);
            gameController.displayBoard(game);

            while(gameController.checkState(game).equals(GameState.IN_PROGRESS)) {
                gameController.makeMove(game);
                gameController.displayBoard(game);

                System.out.println("Anyone wants to Undo ???? (Y/N)");
                String x = sc.next();
                if(x.equals("Y")){
                    gameController.undo(game);
                    game.setGameState(GameState.IN_PROGRESS);
                    System.out.println("After UNDO : ");
                    gameController.displayBoard(game);
                }
            }

            if(gameController.checkState(game).equals(GameState.SUCCESS)){
                System.out.println("****** GAME OVER *******");
                System.out.println("Winner is " + game.getWinner().getName());
            } else {
                System.out.println(" ***** GAME OVER ******");
                System.out.println("Game ends in a DRAW");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Something went wrong!, Please start the game again.");
        }
    }

}
