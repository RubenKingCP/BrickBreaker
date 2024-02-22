package BrickBreaker.lib;

import java.awt.Color;
import java.awt.Graphics;


public class Ball {
    private int radius = 10;
    private double x;
    private double y;
    private double dx = (int) (Math.random() * 11) - 5;
    private int dy = -4;
    

    public Ball(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void paint(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval((int)x - radius, (int)y - radius, radius * 2, radius * 2);
        g.setColor(Color.black);
        g.drawOval((int)x - radius, (int)y - radius, radius * 2, radius * 2);
    }

    public void setPosition(Point position) {
        this.x = position.x;
        this.y = position.y;
    }

    public void move(){
        this.x+= this.dx;
        this.y+= this.dy;
    }

    public boolean Wallcollision(int screenWidth, int screenHeight){
        double centerX = this.x + this.radius;
        double centerY = this.y + this.radius;
        //Check collision with right and left wall
        if (centerX - radius < 0 || centerX + radius > screenWidth) {
            //Reverse ball direction when hit
            this.dx *= -1;
        }

        // Check collision with top wall
        if (centerY - radius < 0) {
            this.dy *= -1;
        }

        // Check collision with bottom wall
        if (centerY + radius > screenHeight) {
            // Collision with bottom wall
            // Handle collision here
            this.dy *= -1;
            this.dx = (int) (Math.random() * 11) - 5;
            return false;
        }
        return true;
    }

    public boolean collideShooter(Squares shooter){
        double centerX = this.x + this.radius;
        double centerY = this.y + this.radius;
        
        // Check if the center of the ball is within the boundaries of the shooter
        if(centerX + radius >= shooter.getTopLX() && centerX - radius <= shooter.getBotRX() && 
            centerY + radius / 2 >= shooter.getTopLY() && centerY - radius / 2 <= shooter.getBotRY()){
            // Collision detected, perform appropriate action
            this.dy *= -1; // Reverse the vertical direction of the ball, for example
            this.dx = (int) (Math.random() * 11) - 5;
            return true;
        }
        return false;
    }

    public void collideBrick(SquaresArray squares){
        for(int j = 0; j < squares.getNumOfRows(); j++){
            for(int i = 0; i < squares.getNumOfBlocks(); i++){
                if(squares.getActive(j, i) && collideShooter(squares.getSquare(j, i))){
                    squares.toggleActive(j, i);
                    return;
                }
            }

        }
    }
}
    
