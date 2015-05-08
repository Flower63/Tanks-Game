import javax.swing.*;
import java.awt.*;

/**
 * Created by Dennis on 5/5/2015.
 */
public class Opponent extends Moveble {

    private int delay = 30;
    private boolean direct = true;
    private Moveble player = BattleField.battleField.getPlayer();

    //Constructor
    public Opponent(int x, int y, Direction direction) {

        super(x, y, direction, 2, new Rectangle(x, y, 40, 40));

        setImageUp(new ImageIcon(getClass().getResource("resource/opponent_up.png")).getImage());
        setImageDown(new ImageIcon(getClass().getResource("resource/opponent_down.png")).getImage());
        setImageLeft(new ImageIcon(getClass().getResource("resource/opponent_left.png")).getImage());
        setImageRight(new ImageIcon(getClass().getResource("resource/opponent_right.png")).getImage());

        setImage();
    }

    //In this method opponents decides, what to do
    //It uses delay variable to change behaviour every 30 cycles (1,5 sec)
    @Override
    public void move() {
        if (delay == 30){
            chooseAction();
            delay = 0;
        } else {
            delay++;
            doAction();
        }

    }

    //Opponent uses this method to randomly choose between two options
    //(orient by player's x-angle or y-angle)
    private void chooseAction() {
        double num = Math.random() * 2;
        if(num < 1)
            direct = true;
        else
            direct = false;
        doAction();
    }

    //To escape blocking trap
    @Override
    public void ifBlocked() {
        switch (direction){
            case UP:
                for (int i = 0; i < 3; i++) {
                    moveDown();
                }
                break;
            case DOWN:
                for (int i = 0; i < 3; i++) {
                    moveUp();
                }
                break;
            case LEFT:
                for (int i = 0; i < 3; i++) {
                    moveRight();
                }
                break;
            case RIGHT:
                for (int i = 0; i < 3; i++) {
                    moveLeft();
                }
        }
    }

    //Move tank or fires, depending of where player is and what option was chosen in chooseAction() method
    private void doAction() {
        if(direct) {
            if (x < player.x) {
                direction = Direction.RIGHT;
                moveRight();
            }
            else if (x > player.x) {
                direction = Direction.LEFT;
                moveLeft();
            }
            else if (x == player.x) {
                if (y < player.y) {
                    direction = Direction.DOWN;
                    fire();
                }
                else if (y > player.y) {
                    direction = Direction.UP;
                    fire();
                }
            }
        } else {
            if (y < player.y) {
                direction = Direction.DOWN;
                moveDown();
            } else if (y > player.y) {
                direction = Direction.UP;
                moveUp();
            } else if (y == player.y) {
                if (x < player.x) {
                    direction = Direction.RIGHT;
                    fire();
                } else if (x > player.x) {
                    direction = Direction.LEFT;
                    fire();
                }
            }
        }
    }
}
