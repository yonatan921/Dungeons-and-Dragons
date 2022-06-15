import java.util.Random;

public abstract class Unit extends Tile {

    String name;
    int healthPool;
    int healthAmount;
    int attack;
    int defense;

    protected Unit(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile);

    }

    protected void initialize(Position position, MessageCallback messageCallback){

    }

    protected int attack() {
        return new Random().nextInt(attack + 1); //random number [0,attack]
    }
    public int defend(){
        return new Random().nextInt(defense +1); //random number [0,defense]
    }

    // Should be automatically called once the unit finishes its turn
    public abstract void processStep();

    // What happens when the unit dies
    public abstract void onDeath();

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){

    }

    public void visit(Empty e){

    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    // Combat against another unit.
    protected void battle(Unit u){

    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return healthAmount;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttack(), getDefense());
    }
}