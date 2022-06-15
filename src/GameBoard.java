import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameBoard {
    private List<Tile> tiles;

    public GameBoard(Tile[][] board){
        tiles = new ArrayList<>();
        for(Tile[] line : board){
            tiles.addAll(Arrays.asList(line));
        }
    }

    public Tile get(int x, int y) throws Exception {
        Position p = new Position(x, y);
        for(Tile t : tiles){
            if (t.getPosition().equals(p)){
                return t;
            }
        }
        throw new Exception("Tile not found");// Throw an exception if no such tile.

    }

    public void remove(Enemy e) {
        tiles.remove(e);
        Position p = e.getPosition();
        //tiles.add(new Empty(p)); //____________Create new empty place in the same position__________//
    }

    @Override
    public String toString() {
        tiles = tiles.stream().sorted().collect(Collectors.toList());
        // TODO: Implement me
        return null;
    }
}