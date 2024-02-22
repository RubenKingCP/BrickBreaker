package BrickBreaker.lib;

import java.awt.Graphics;

public class SquaresArray {
    private final int numOfBlocks = 10;
    private final int numOfRows = 5;
    private Squares[][] squares;
    private boolean[][] active;

    public SquaresArray() {
        squares = new Squares[numOfRows][numOfBlocks];
        active = new boolean[numOfRows][numOfBlocks];
    }

    public void initiateArray() {
        int startX = 40;
        int startY = 40;
        int blockSizeX = 80;
        int blockSizeY = 40;
        int paddingX = 5;
        int paddingY = 10;
        for(int j = 0; j < numOfRows; j++){
            int posY = startY + (j * (blockSizeY + paddingY));
            for (int i = 0; i < numOfBlocks; i++) {
                int posX = startX + (i * (blockSizeX + paddingX));
                squares[j][i] = new Squares(new Point(posX, posY), blockSizeX, blockSizeY);
                active[j][i] = true;
            }
        }
    }

    public void paint(Graphics g) {
        for (int j = 0; j < numOfRows; j++) {
            for (int i = 0; i < numOfBlocks; i++){
                if(active[j][i] == true) 
                    squares[j][i].paint(g);
            }
        }
    }

    public Squares[][] getSquares() {
        return this.squares;
    }

    public int getNumOfRows(){
        return this.numOfRows;
    }

    public int getNumOfBlocks(){
        return this.numOfBlocks;
    }

    public boolean getActive(int numOfRow, int numOfBlock){
        return this.active[numOfRow][numOfBlock];
    }

    public void toggleActive(int numOfRow, int numOfBlock){
        this.active[numOfRow][numOfBlock] = false;
    }

    public Squares getSquare(int numOfRow, int numOfBlock){
        return this.squares[numOfRow][numOfBlock];
    }
}

