import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Dennis on 5/5/2015.
 */
public class Render extends JPanel {

    private JFrame frame;
    private Font messageFont;
    private Moveble player;
    private ArrayList<Wall> walls;
    private ArrayList<Moveble> tanks;
    private CopyOnWriteArrayList<Moveble> missiles;

    //Setter for array of missiles
    public void setMissiles(CopyOnWriteArrayList<Moveble> missiles) {
        this.missiles = missiles;
    }

    //Setter for array of wall
    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    //Setter for array of tanks
    public void setTanks(ArrayList<Moveble> tanks) {
        this.tanks = tanks;
    }

    //Player setter
    public void setPlayer(Moveble player) {
        this.player = player;
    }

    //Keyboard listener
    private class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!BattleField.battleField.running) {
                BattleField.battleField.running = true;
            }
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_UP){
                player.moveUp();
            }
            else if (key == KeyEvent.VK_DOWN){
                player.moveDown();
            }
            else if (key == KeyEvent.VK_LEFT){
                player.moveLeft();
            }
            else if (key == KeyEvent.VK_RIGHT){
                player.moveRight();
            }
            else if (key == KeyEvent.VK_SPACE){
                player.fire();
            }
        }
    }

    //Constructor
    public Render(Moveble player) {
        this.player = player;
        messageFont = new Font("Arial", Font.PLAIN, 30);
        frame = new JFrame("Java Tanks");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(620, 640);
        frame.add(this);
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
        frame.setVisible(true);
    }

    //Method, where we draw everything on screen
    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 620, 640);
        if (!BattleField.battleField.running) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(messageFont);
            graphics.drawString(BattleField.battleField.message, 230, 200);
        }
        if (missiles != null && tanks != null && walls != null) {
            for (Moveble missile : missiles) {
                if (missile.x < 0 || missile.x > 620 || missile.y < 0 || missile.y > 640) {
                    //remove missile, what get out of screen
                    missiles.remove(missile);
                    continue;
                } else {
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect(missile.x, missile.y, 4, 4);
                }
            }
            for (Moveble tank : tanks) {
                graphics.drawImage(tank.image, tank.x, tank.y, null);
            }
            for (Wall wall : walls) {
                graphics.setColor(Color.WHITE);
                graphics.fillRect(wall.x, wall.y, wall.width, wall.height);
            }
        }
    }
}
