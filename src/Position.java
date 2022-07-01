import java.util.Objects;

public class Position implements Comparable<Position> {
    //fields
    public int x;
    public int y;

    public Position(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Position o) {
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

    public void moveUp(){
        this.y = y -1 ;
    }
    public void moveDown(){
        this.y =y + 1;
    }
    public void moveLeft(){
        this.x = x -1 ;
    }
    public void moveRight(){
        this.x = x + 1 ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
