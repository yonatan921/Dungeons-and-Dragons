import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LevelManager {
    private int currLevel;

    private int currLevelWidth;

    private TileFactory tf;

    private final String path;
    private Player selected;
    private List<Enemy> enemies;

    private boolean won = false;

    public LevelManager(TileFactory tileFactory, String path) {
        currLevel = 1;
        this.tf = tileFactory;
        enemies = new ArrayList<>();
        selected = tileFactory.getSelected();
        this.path = path;

    }

    private List<Tile> loadLevel(int currLevel) {
        List<String> readLines = readAllLines(path + "\\level" + currLevel + ".txt");
        this.currLevelWidth = readLines.get(0).length();
        return convertReadLinesToTiles(readLines);
    }

    public void advanceLevel() {
        if(currLevel < 4) {
            currLevel++;
        }
        else
            won = true;
    }

    private List<String> readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    Arrays.toString(e.getStackTrace()));
        }
        return lines;
    }

    private List<Tile> convertReadLinesToTiles(List<String> readLines) {
        List<Tile> tiles = new ArrayList<>();
        int x = 0; // x position
        int y = 0; // y position
        for(String row : readLines) {
            for (int col = 0; col < row.length(); col++) {
                char c = row.charAt(col);
                Position pos = new Position(x,y);
                Tile tile;
                switch (c) {
                    case '.' -> tile = tf.produceEmpty(pos);
                    case '#' -> tile = tf.produceWall(pos);
                    case '@' -> {
                        tf.producePlayer(pos);
                        tile = selected;
                        selected.initialize(pos);
                    }
                    default -> {
                        Enemy enemy = tf.produceEnemy(c, pos);
                        tile = enemy;
                        enemies.add(enemy);
                    }
                }
                tiles.add(tile);
                if(x == currLevelWidth - 1) {
                    x = 0;
                } else {
                    x++;
                }
            }
            y++;
        }
        return tiles;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
    public int getCurrLevel() {
        return currLevel;
    }

    public int getCurrLevelWidth() {
        return currLevelWidth;
    }


    public TileFactory getTf() {
        return tf;
    }

    public Player getSelected() {
        return selected;
    }

    public boolean isWon() {
        return won;
    }

    public List<Tile> loadLevel(){
        return loadLevel(currLevel);
    }
}
