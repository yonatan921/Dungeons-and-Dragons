public abstract class Tile implements Comparable<Tile>{
    //fields
    private char tile;
    private Position position;

    //constructors
    protected Tile(char tile){
        this.tile = tile;
    }
    protected void initialize(Position position){
        this.position = position;
    }

    //methods
    public char getTile() {
        return tile;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public abstract void accept(Unit unit);
    @Override
    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }
    @Override
    public String toString() {
        return String.valueOf(tile);
    }
    public void setTile(char tile) {
        this.tile = tile;
    }
}
