import javax.swing.*;
import java.awt.*;

/**
 * Created by Dennis on 5/5/2015.
 */
public class Player extends Moveble {

    //Constructor, which loads images and set it to variables if superclass
    public Player(int x, int y) {

        super(x, y, Direction.UP, 2, new Rectangle(x, y, 40, 40));

        setImageUp(new ImageIcon(getClass().getResource("resource/player_up.png")).getImage());
        setImageDown(new ImageIcon(getClass().getResource("resource/player_down.png")).getImage());
        setImageLeft(new ImageIcon(getClass().getResource("resource/player_left.png")).getImage());
        setImageRight(new ImageIcon(getClass().getResource("resource/player_right.png")).getImage());

        setImage();
    }

    @Override
    public void destroy() {
        super.destroy();
        BattleField.battleField.message = " YOU LOST";
        BattleField.battleField.running = false;
    }
}
