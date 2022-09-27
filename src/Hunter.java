import java.util.Comparator;
import java.util.List;

public class Hunter extends Player{
    //fields
    private final int range;
    private int arrows;
    private int tick;
    //constructors
    public Hunter(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer range) {
        super(name, health_pool, attack_points, defence_points);
        this.range = range;
        this.arrows = 10 * this.getLevel();
        this.tick = 0;
    }

    //methods
    @Override
    public void castAbility(List<Enemy> enemyList) {
        if (getArrows() > 0){
            setArrows(getArrows() -1);
            Comparator<Enemy> enemyComparator = (enemy1, enemy2) ->
                    (int) Math.signum(enemy1.getPosition().
                            distance(this.getPosition()) - enemy2.getPosition().distance(this.getPosition()));
            Enemy closetEnemy = enemyList.stream().min(enemyComparator).get();
            if (this.getPosition().distance(closetEnemy.getPosition()) < getRange()) castAbility(closetEnemy);
        }
        else
            this.messageCallback.send(new Message(this.getName() + " has no arrows"));
    }

    @Override
    public void castAbility(Enemy enemy) {
        this.battle(enemy, this.getAttack(), enemy.defend());
    }

    @Override
    public void gameTick() {
        if (getTick() == 10){
            setArrows(getArrows() + getLevel());
            setTick(0);
        }
        else
            setTick(getTick() + 1);
    }

    @Override
    public String describe(){
        return String.format("%s    Health: %d/%d   Attack: %d  Defense: %d     Level: %d\n     Experience: %d/%d       Arrows: %d ",
                getName(), getHealthAmount(), getHealthPool(), getAttack(), getDefense(), getLevel(), getExperience(), getLevel() * 50, getArrows());
    }

    protected void acceptLvlUp(Player player){
        setArrows(10 * getLevel());
        setAttack(getAttack() + (2 * getLevel()));
        setDefense(getDefense() + getLevel());
        send(new Message(String.format("%s reached level %d:  +%d Attack, +%d Defense",
                getName(), getLevel(), 6 *getLevel(), 3 * getLevel())));
    }

    public int getArrows() {
        return arrows;
    }

    public int getRange() {
        return range;
    }

    public void setArrows(int arrows) {
        this.arrows = arrows;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }
}
