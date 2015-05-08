import java.awt.*;

/**
 * Created by Dennis on 5/6/2015.
 */
public class Wall {

    //It just walls

    int x;
    int y;
    int width;
    int height;
    private Rectangle rectangle;

    public Wall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rectangle = new Rectangle(x, y, width, height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
