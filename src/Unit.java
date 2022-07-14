import java.util.Random;

public abstract class Unit extends Tile implements MessageCallback, DeathCallback {

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


    protected int attack() {
        return new Random().nextInt(attack + 1); //random number [0,attack]
    }
    public int defend(){
        return new Random().nextInt(defense +1); //random number [0,defense]
    }

    // Should be automatically called once the unit finishes its turn
    public void processStep() {

    }

    // What happens when the unit dies
    public abstract void onDeath();

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
        tile.accept(this);
    }

    public  void visit(Player p){
        this.battle(p);
    }
    public  void visit(Enemy e){
        this.battle(e);
    }
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
      int attack = this.attack;
      int defend = u.defend();
      this.battle(u, attack, defend);
    }

    protected void battle(Unit u, int attack, int defend){
        if(attack - defend > 0) {
            if(u.healthAmount - (attack - defend) < 0) {
                u.onDeath();
                this.levelUp();
            } else {
                u.healthAmount = u.healthAmount - (attack - defend);
            }
        }
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
    public void send(Message m){

    }

    public abstract void levelUp();

}