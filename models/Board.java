package models;

import java.util.List;
import java.util.ArrayList;

public class Board {
    private int size;
    private List<List<Cell>> grid;

    Board(int dimension){
        this.size = dimension;
        this.grid = new ArrayList<>();

        for(int i = 0; i < dimension ; i++){
            grid.add(new ArrayList<>());
            for(int j = 0; j < dimension ; j ++){
                grid.get(i).add(new Cell( i , j));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }
    public void display(){
        for(List<Cell> x : grid){
            for(Cell y : x){
                y.printCell();
            }
            System.out.println();
        }
    }


}
