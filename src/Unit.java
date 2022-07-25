import java.util.Random;

public abstract class Unit extends Tile implements MessageCallback, DeathCallback {
    //fields
    private final String name;
    private int healthPool;
    private int healthAmount;
    private int attack;
    private int defense;
    protected MessageCallback messageCallback;

    //constructors
    protected Unit(char tile, String name, int healthCapacity, int attack, int defense) {
        super(tile);
        this.name = name;
        this.healthPool = healthCapacity;
        this.healthAmount = healthCapacity;
        this.attack = attack;
        this.defense = defense;
    }

    //fields
    protected int attack() {
        return new Random().nextInt(attack + 1); //random number [0,attack]
    }
    public int defend(){
        return new Random().nextInt(defense +1); //random number [0,defense]
    }

    // What happens when the unit dies
    public abstract void onDeath();

    // This unit attempts to interact with another tile.
    public void interact(Tile tile){
        tile.accept(this);
    }

    public  void visit(Player p){
        this.battle(p);
    } // enemy engage player
    public  void visit(Enemy e){
        this.battle(e);
    } // player engage enemy
    public void visit(Wall w){

    } //do nothing
    public void visit(Empty e){
        //swap positions with empty
        Position tmpPos = this.getPosition();
        this.setPosition(e.getPosition());
        e.setPosition(tmpPos);
    }

    // Combat against another unit.
    protected void battle(Unit u){
      int attack = this.attack();
      int defend = u.defend();
      this.battle(u, attack, defend);
    }

    protected void battle(Unit u, int attack, int defend){
        send(new Message(String.format("%s engaged in combat with %s.",this.getName(), u.getName())));
        send(new Message(this.describe()));
        send(new Message(u.describe()));
        send(new Message(String.format("%s rolled %d attack points.",this.getName(), attack)));
        send(new Message(String.format("%s rolled %d defense points.",u.getName(), defend)));
        if(attack - defend > 0) {
            send(new Message(String.format("%s dealt %d damage to %s.",this.getName(),attack - defend, u.getName())));
            if(u.getHealthAmount() - (attack - defend) < 0) { // check if unit died
                u.onDeath(); //remove unit
                this.setExperience(u); // add exp
                this.levelUp(); // level up
            } else {
                u.setHealthAmount(u.getHealthAmount() - (attack - defend));
            }
        }
        else
            send(new Message(String.format("%s dealt 0 damage to %s.",this.getName(), u.getName())));
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public abstract String describe();

    public abstract void levelUp();

    public int getHealthAmount() {
        return healthAmount;
    }

    public int getHealthPool() {
        return healthPool;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setHealthAmount(int healthAmount) {
        this.healthAmount = healthAmount;
    }

    public void setHealthPool(int healthPool) {
        this.healthPool = healthPool;
    }

    public abstract void setExperience(Unit unit);

    public abstract void acceptEXP(Player player);

    @Override
    public void send(Message m) {
        messageCallback.send(m);
    }
}