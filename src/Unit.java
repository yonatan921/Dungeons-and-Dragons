import java.util.Random;

public abstract class Unit extends Tile implements MessageCallback {

    String name;
    int healthPool;
    int healthAmount;
    int attack;
    int defense;

    protected Unit(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile);
        this.name = name;
        this.healthPool = healthCapacity;
        this.healthAmount = healthCapacity;
        this.attack = attack;
        this.defense = defense;
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
    public void processStep() {

    }

    public void removeFromBoard() {

    }


    // What happens when the unit dies
    public abstract void onDeath();

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
        tile.accept(this);
    }

    public abstract void visit(Player p);
    public abstract void visit(Enemy e);
    public void visit(Wall w){
        //do nothing/game tick
    }
    public void visit(Empty e){
        //move to e
        Position tmpPos = this.position;
        this.position = e.position;
        e.position = tmpPos;
    }


    // Combat against another unit.
    protected void battle(Unit u){
        battle(u);
    }

//    protected void battle(Enemy e){
//        int attackPoints = attack();
//        int defensePoints = e.defend();
//        if(attackPoints - defensePoints > 0) {
//            if(e.healthAmount - (attackPoints - defensePoints) < 0) {
//                this.accept(this);
//                e.onDeath();
//            } else {
//                e.healthAmount = e.healthAmount - (attackPoints - defensePoints);
//            }
//        }
//        System.out.println(this.getName() + " rolled "+ attackPoints + " attack points. " + e.getName() + " and rolled " + defensePoints + " defense points. and dealt " + (attackPoints - defensePoints) + " damage" );
//        System.out.println("BATTLE WITH ENEMY");
//    }


//    protected void battle(Player p){
//        int attack = attack();
//        int defense = p.defend();
//        if(attack - defense > 0) {
//            if(p.defense - (attack - defense) < 0) {
//                p.onDeath();
//            } else {
//               p.defense = p.defense - (attack - defense);
//            }
//        }
//        System.out.println(this.getName() + " attacked " + p.getName() + " and dealt it " + (attack - defense) + " points" );
//        System.out.println("BATTLE WITH PLAYER");
//    }

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
    public void send(Message m){

    }
}