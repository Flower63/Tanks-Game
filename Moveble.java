import java.awt.*;

/**
 * Created by Dennis on 5/5/2015.
 */

/**
 * Abstract class, what contains common variables and methods
 * for missiles, opponent tanks and player tank
 */

public abstract class Moveble {

    protected int x;
    protected int y;
    protected int speed;
    Direction direction;
    Rectangle rectangle;

    //Images for draw player's tank and the enemies
    Image image;
    Image imageUp;
    Image imageLeft;
    Image imageRight;
    Image imageDown;

    //Constructor
    public Moveble(int x, int y, Direction direction, int speed, Rectangle rectangle) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = speed;
        this.rectangle = rectangle;
    }

    //Moving methods, up down left right
    //Using by tanks only
    public void moveUp() {
        if(y > 0){
            if(image != imageUp) {
                y = y - speed;
                image = imageUp;
                direction = Direction.UP;
            } else {
                y = y - speed;
            }
        }
    }

    public void moveDown() {
        if(y < 560) {
            if(image != imageDown) {
                y = y + speed;
                image = imageDown;
                direction = Direction.DOWN;
            } else {
                y = y + speed;
            }
        }
    }

    public void moveLeft() {
        if(x > 0){
            if(image != imageLeft) {
                x = x - speed;
                image = imageLeft;
                direction = Direction.LEFT;
            } else {
                x = x - speed;
            }
        }
    }

    public void moveRight() {
        if(x < 560){
            if(image != imageRight) {
                x = x + speed;
                image = imageRight;
                direction = Direction.RIGHT;
            } else {
                x = x + speed;
            }
        }
    }

    //Method for enemy tank, for decide what to do if it stuck
    //declared here, to call it from super class-type collections
    public void ifBlocked() {
    }

    //Tank fire. Call fire(x, y, direction) in battleField class
    //refers to static variable of game
    public void fire() {
        BattleField.battleField.fire(x, y, direction);
    }

    //Destroying shooted tank. Removes it from array of existed tanks
    //and missiles
    public void destroy() {
        BattleField.battleField.destroy(this);
    }

    //Method to move dynamic objects. Used by player and missile. Overridden by Opponent class
    public void move() {
        switch (direction) {
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
    }

    //Returns rectangle for each object to compute collisions and destroying
    public Rectangle getRect() {
        rectangle.setLocation(x, y);
        return rectangle;
    }

    //Setting images for tanks. Calling by constructors of this classes
    public void setImageUp(Image imageUp) {
        this.imageUp = imageUp;
    }

    public void setImageLeft(Image imageLeft) {
        this.imageLeft = imageLeft;
    }

    public void setImageRight(Image imageRight) {
        this.imageRight = imageRight;
    }

    public void setImageDown(Image imageDown) {
        this.imageDown = imageDown;
    }

    //Set visible image, every time tank make a turn
    void setImage() {
        switch (direction) {
            case UP:
                image = imageUp;
                break;
            case DOWN:
                image = imageDown;
                break;
            case LEFT:
                image = imageLeft;
                break;
            case RIGHT:
                image = imageRight;
        }
    }
}
