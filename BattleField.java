import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Dennis on 5/5/2015.
 */
public class BattleField {

    boolean running = false;
    String message = "HIT ANY KEY";
    private Moveble player;
    private Render render;
    private ArrayList<Wall> walls;
    private ArrayList<Moveble> tanks;
    private CopyOnWriteArrayList<Moveble> missiles;

    //Main game object
    public static BattleField battleField;

    //BattleField constructor
    public BattleField() {
        player = new Player(280, 560);
        render = new Render(player);
    }

    //Inits objects before game starts,
    //also uses for reset the game in case of win/lose
    public void init() {
        render.setPlayer(player);
        missiles = new CopyOnWriteArrayList<>();
        tanks = new ArrayList<>();
        tanks.add(new Opponent(0, 280, Direction.RIGHT));
        tanks.add(new Opponent(280, 0, Direction.DOWN));
        tanks.add(new Opponent(560, 280, Direction.LEFT));
        tanks.add(player);
        walls = new ArrayList<>();
        walls.add(new Wall(130, 130, 10, 320));
        walls.add(new Wall(270, 280, 100, 10));
        walls.add(new Wall(500, 130, 10, 320));
        render.setTanks(tanks);
        render.setMissiles(missiles);
        render.setWalls(walls);
        render.repaint();
    }

    //Method, where game runs
    public void run() {
        while (running) {
            if (tanks.size() == 1) {
                if (tanks.contains(player)) {
                    message = "  YOU WIN!";
                } else {
                    player.destroy();
                }
                running = false;
            }
            try {
                render.repaint();
                for (Moveble missile : missiles) {
                    if (!intersectionTest(missile)) {
                        missile.move();
                    }
                }
                for (Moveble tank : tanks) {
                    if (!intersectionTest(tank)) {
                        tank.move();
                    } else {
                        tank.ifBlocked();
                    }
                }
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        render.repaint();
        player = new Player(280, 560);
    }

    //Reference of player object, obtained by enemy to hunt the player
    public Moveble getPlayer() {
        return player;
    }

    //Removes destroyed moveble object
    public void destroy(Moveble tank) {
        tanks.remove(tank);
    }

    //Launch a missile
    //calls by fire() method of tank
    public void fire(int x, int y, Direction direction) {
        switch (direction) {
            case UP:
                x += 20;
                y -= 4;
                break;
            case DOWN:
                y += 40;
                x += 20;
                break;
            case LEFT:
                y += 20;
                x -= 4;
                break;
            case RIGHT:
                y += 20;
                x += 40;
        }
        missiles.add(new Missile(x, y, direction));
    }

    //Check for objects intersection
    private boolean intersectionTest(Moveble own) {
        for (Moveble moveble : tanks) {
            if (moveble != own) {
                if (moveble.getRect().intersects(own.getRect())) {
                    if (own instanceof Missile) {
                        moveble.destroy();
                        missiles.remove(own);
                    }
                    return true;
                }
            }
        }
        for (Wall wall : walls) {
            if (wall.getRectangle().intersects(own.getRect())) {
                if (own instanceof Missile)
                    missiles.remove(own);
                return true;
            }
        }
        return false;
    }

    //Method main()
    public static void main(String[] args) {
        battleField = new BattleField();
        while (true) {
            battleField.init();
            while (!battleField.running) {
                try {
                    Thread.sleep(50);
                    battleField.render.repaint();
                } catch (InterruptedException ignored) {
                    //NOP
                }
            }
            battleField.run();
        }
    }
}
