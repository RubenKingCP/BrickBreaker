package BrickBreaker.lib;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Squares extends JPanel{
    private int topLX;
    private int topLY;
    private int width;
    private int height;
    private int botRX;
    private int botRY;

    public Squares(Point topLeft, int width, int height) {
        this.topLX = topLeft.x;
        this.topLY = topLeft.y;
        this.width = width;
        this.height = height;
        this.botRX = topLX + width;
        this.botRY = topLY + height;
    }

   public void paint(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(this.topLX, this.topLY, this.width, this.height);
        g.setColor(Color.BLACK);
        g.drawRect(this.topLX, this.topLY, this.width, this.height);
   }

    //    public boolean checkCollision(Squares square){
    //         //Check to see if one square is to the left of the other or above
    //         if(this.topLX >= square.botRX || this.topLY >= square.topLY){
    //             System.out.println("Works 1");
    //             return false;
    //         }
                
    //         //Check if the other square is bellow or to the left
    //         if(this.botRX <= square.topLX || this.botRY <= square.topLY){
    //             System.out.println("Works 2");
    //             return false;
    //         }

    //         System.out.println("They overlap");
    //         return true;
    //    }

   public void setPosition(Point newPosition) {
        this.topLX = newPosition.x;
        this.topLY = newPosition.y;
        this.botRX = topLX + width;
        this.botRY = topLY + height;
    }

    public int getWidth(){
        return this.width;
    }

    public int getTopLX() {
        return this.topLX;
    }

    public int getTopLY(){
        return this.topLY;
    }

    public int getBotRX(){
        return this.botRX;
    }

    public int getBotRY(){
        return this.botRY;
    }
}
