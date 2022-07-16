import java.util.List;

public abstract class Player extends Unit {
    //fields

    private Integer Level = 1;

    private Integer Experience = 0;
    //constructor
    public Player(String name, Integer health_pool, Integer attack_points, Integer defence_points) {
        super('@', name, health_pool, attack_points, defence_points); //player will always be represented with '@'
    }

    public void init(MessageCallback messageCallback) {
        super.messageCallback = messageCallback;
    }

    public abstract void specialAbility(List<Enemy> enemyList);

    public void visit(Player player) {
        //do nothing
    }

    public void visit(Enemy enemy) {
        //start fight
        this.battle(enemy);
    }

    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void onDeath() {
        //game over
        setTile('X');
        this.send(new Message("GAME OVER - PLAYER DIED"));
    }


    public void levelUp() {
        while (getExperience() >= 50 * getLevel()){
            setExperience(getExperience() - (50 * getLevel()));
            setLevel(getLevel() + 1);
            setHealthPool(getHealthPool() + (10 * getLevel()));
            setHealthAmount(getHealthPool());
            setAttack(getAttack() + (4 * getLevel()));
            setDefense(getDefense() + getLevel());
            this.acceptLvlUp(this);
        }
    }
    protected void acceptLvlUp(Player player) {
        player.acceptLvlUp(this);
    }
    public abstract void specialAbility(Enemy enemy);
    public abstract void gameTick();
    public Integer getLevel() {
        return Level;
    }
    public void setLevel(Integer level) {
        Level = level;
    }
    public  void setExperience(Unit unit){
        unit.acceptEXP(this);
    }
    public Integer getExperience() {
        return Experience;
    }
    public void setExperience(Integer experience) {
        Experience = experience;
    }
    public  void acceptEXP(Player player){

    }

}

