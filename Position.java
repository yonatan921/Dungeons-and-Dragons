import java.util.Objects;

public class Position implements Comparable<Position> {
    //fields
    private int x;
    private int y;

    //constructors
    public Position(int x,int y){
        this.x = x;
        this.y = y;
    }

    //methods
    @Override
    public int compareTo(Position o) { // lex comparator
        if (y < o.y)
            return -1;
        if (y > o.y)
            return 1;
        return Integer.compare(x, o.x);
    }

    public double distance(Position position){ //Euclidean Distance
        int x_def = this.x - position.x;
        int y_def = this.y - position.y;
        return  Math.sqrt(Math.pow(x_def, 2) + Math.pow(y_def, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    public void moveUp(){
        setY(getY() -1);
    }
    public void moveDown(){
        setY(getY()+1);
    }
    public void moveLeft(){
        setX(getX() - 1);
    }
    public void moveRight(){
        setX(getX() + 1);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
