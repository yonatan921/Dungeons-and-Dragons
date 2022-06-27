import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelManager {
    int currLevel;
    int currLevelWidth;
    List<Tile> tiles;
    TileFactory tf;
    private static final String path = "C:\\Users\\yonat\\IdeaProjects\\Dungeons and Dragons"; //TODO: CHANGE THIS PATH TO URS BERFORE RUNNING
    Player selected;
    List<Enemy> enemies;

    public LevelManager(Player selected) {
        currLevel = 1;
        this.tf = new TileFactory();
        this.selected = selected; //TODO: CHANGE
        enemies = new ArrayList<>();
        loadLevel(currLevel);
    }

    private void loadLevel(int currLevel) {
        List<String> readLines = readAllLines(path + "\\level" + currLevel + ".txt");
        this.currLevelWidth = readLines.get(0).length();
        this.tiles = convertReadLinesToTiles(readLines);
    }

    public void advanceLevel() {
        if(currLevel < 4) {
            loadLevel(currLevel + 1);
        }
    }

    public List<String> readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
        return lines;
    }

    public List<Tile> convertReadLinesToTiles(List<String> readLines) {
        List<Tile> tiles = new ArrayList<>();
        int x = 0; // x position
        int y = 0; // y position
        for(String row : readLines) {
            for (int i = 0; i < row.length(); i++) {
                char c = row.charAt(i);
                Position pos = new Position(x,y);
                Tile tile;
                switch (c) {
                    case '.': tile = tf.produceEmpty(pos); break;
                    case '@': tile = selected; selected.initialize(new Position(x,y)); break; //TODO: CHANGE IDX
                    case '#': tile = tf.produceWall(pos); break;
                    default: tile = tf.produceEnemy(c,pos); enemies.add((Enemy)tile);
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
        this.tiles = tiles;
        return tiles;
    }

}
