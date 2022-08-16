import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    public List<Tile> tiles;
    public int boardWidth;
    public LevelManager levelManager;

    public GameBoard() {

    }

    public void initialize(LevelManager levelManager){
        this.levelManager = levelManager;
        this.tiles = levelManager.loadLevel();
        boardWidth = levelManager.getCurrLevelWidth();
    }

    
    public void sortTiles(){
        tiles = tiles.stream().sorted().collect(Collectors.toList());
    }

    public void remove(Enemy e) {
        Position p = e.getPosition();
        int t = tiles.indexOf(e);
        tiles.remove(e);
        levelManager.getEnemies().remove(e);
        Empty newEmpty = new Empty();
        newEmpty.initialize(p);
        tiles.add(newEmpty);
       
    }


    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        String output = "";
        int counter = 0;
        for(Tile t : tiles) {

            if(counter < boardWidth) {
                output += t.toString();
                counter++;
            } else {
                output += "\n" + t.toString();
                counter = 1;
            }
        }
        return output;
    }

    public void advanceLevel(){
        levelManager.advanceLevel();
        if (!levelManager.isWon()){
            this.tiles = levelManager.loadLevel();
            this.boardWidth = levelManager.getCurrLevelWidth();
        }
    }


}