import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;
    private int boardWidth;
    private LevelManager levelManager;

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
        StringBuilder output = new StringBuilder();
        int counter = 0;
        for(Tile t : tiles) {

            if(counter < boardWidth) {
                output.append(t.toString());
                counter++;
            } else {
                output.append("\n").append(t.toString());
                counter = 1;
            }
        }
        return output.toString();
    }

    public void advanceLevel(){
        levelManager.advanceLevel();
        if (!levelManager.isWon()){
            this.tiles = levelManager.loadLevel();
            this.boardWidth = levelManager.getCurrLevelWidth();
        }
    }


    public List<Tile> getTiles() {
        return tiles;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }
}