package strategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{
    HashMap<Integer , HashMap<Symbol , Integer>> counts = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        // 0-> represents Diagonal
        // 1-> represents Anti Diagonal
        // 0 -> { 'X' -> 1} , {'O' -> 2}

        int diagonalRow = move.getCell().getRow();
        int diagonalCol = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getPlayerSymbol();

        if(diagonalRow == diagonalCol){
            if(board.getSize()%2 == 0)
                return checkDiagonal(symbol,board);

            if(board.getSize()%2 == 1 && (board.getSize() -1)/2 == diagonalRow) {
                if(checkDiagonal(symbol, board))
                    return true;

                return checkAntiDiagonal(symbol, board);
            }else {
                return checkDiagonal(symbol, board);
            }
        }

        else if(diagonalRow + diagonalCol == board.getSize()-1){
            return checkAntiDiagonal(symbol, board);
        }

        return false;
    }
    private boolean checkAntiDiagonal(Symbol symbol, Board board) {
        if(!counts.containsKey(1)){
            counts.put(1, new HashMap<>());
        }

        HashMap<Symbol, Integer> antiDiagonal = counts.get(1);

        if(!antiDiagonal.containsKey(symbol)){
            antiDiagonal.put(symbol , 0);
        }

        antiDiagonal.put(symbol , antiDiagonal.get(symbol) + 1);

        return antiDiagonal.get(symbol) == board.getSize();
    }

    private boolean checkDiagonal(Symbol symbol, Board board) {
        if(!counts.containsKey(0)){
            counts.put(0, new HashMap<>());
        }

        HashMap < Symbol , Integer > diagonal = counts.get(0);

        if(!diagonal.containsKey(symbol)){
            diagonal.put(symbol , 0);
        }

        diagonal.put(symbol , diagonal.get(symbol) + 1);

        return diagonal.get(symbol) == board.getSize();
    }

    @Override
    public void handleUndo(Move move, Board board) {
        int diagonalRow = move.getCell().getRow();
        int diagonalCol = move.getCell().getCol();

        if(diagonalRow == diagonalCol){
            handleDiagonalUndo(move);

            if(board.getSize()%2 == 1 && (board.getSize() -1)/2 == diagonalRow) {
                handleAntiDiagonalUndo(move);
            }
        }

        else if(diagonalRow + diagonalCol == board.getSize()-1){
            handleAntiDiagonalUndo(move);
        }
    }

    private void handleAntiDiagonalUndo(Move move) {
        HashMap<Symbol , Integer> antiDiagonal = counts.get(1);
        antiDiagonal.put(move.getPlayer().getPlayerSymbol(), antiDiagonal.get(move.getPlayer().getPlayerSymbol()) - 1);
    }

    private void handleDiagonalUndo(Move move) {
        HashMap<Symbol , Integer> diagonal = counts.get(0);
        diagonal.put(move.getPlayer().getPlayerSymbol(), diagonal.get(move.getPlayer().getPlayerSymbol()) - 1);
    }

}
