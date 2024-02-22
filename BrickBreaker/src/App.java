import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;
import BrickBreaker.lib.Squares;
import BrickBreaker.lib.SquaresArray;
import BrickBreaker.lib.Ball;
import BrickBreaker.lib.Point;


public class App extends JFrame implements ActionListener, MouseMotionListener, MouseListener {
    private SquaresArray squares;
    private Squares shooter;
    private Ball ball;
    private boolean started = false;

    public App() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Rectangle");
        setSize(new Dimension(1000, 1000));
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        squares = new SquaresArray();
        squares.initiateArray();

        shooter = new Squares(new Point(500, 900), 100, 10);
        addMouseMotionListener(this);

        ball = new Ball((500 - 20), (900 - 20), 20);
        addMouseListener(this);

        JPanel gamePanel = new GamePanel();
        add(gamePanel);

        // Start the game loop timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                actionPerformed(null); // Trigger the game logic
            }
        }, 0, 10); // Run every 10 milliseconds
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawGame(g);
        }
    }

    private void drawGame(Graphics g) {
        squares.paint(g);
        shooter.paint(g);
        ball.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (started) {
            ball.move();
            ball.collideShooter(shooter);
            ball.collideBrick(squares);
            //End game check
            if (!ball.Wallcollision(1000, 1000)) {
                // Handle game over condition
                started = false;
            }
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){
        int mouseX = e.getX();
        shooter.setPosition(new Point(mouseX - shooter.getWidth() / 2, 900));
        if(this.started == false) ball.setPosition(new Point(mouseX, 900 - 20));
        repaint(); // Redraw the frame to update the shooter's position
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not used in this example
    }

    @Override
    public void mouseClicked(MouseEvent e){
            this.started = true;
    }

    @Override
    public void mousePressed(MouseEvent e){

    }

    @Override
    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseReleased(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e){

    }

    public static void main(String[] args) throws NullPointerException {
       SwingUtilities.invokeLater(() -> {
        new App();
        });
    }
}

