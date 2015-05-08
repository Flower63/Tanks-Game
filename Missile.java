import java.awt.*;

/**
 * Created by Dennis on 5/5/2015.
 */
public class Missile extends Moveble {

    //Constructor
    public Missile(int x, int y, Direction direction) {
        super(x, y, direction, 5, new Rectangle(x, y, 4, 4));
    }

    //Overridden methods from Moveble, because missile move little different than tank :)
    @Override
    public void moveUp() {
        y -= speed;
    }

    @Override
    public void moveDown() {
        y += speed;
    }

    @Override
    public void moveLeft() {
        x -= speed;
    }

    @Override
    public void moveRight() {
        x += speed;
    }
}
