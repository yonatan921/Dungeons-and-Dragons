import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Rogue extends Player {
    //fields
    private final Integer cost;
    private Integer current_energy = 100;

    private final int CONST_range = 2;

    public Rogue(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer cost) {
        super(name, health_pool, attack_points, defence_points);
        this.cost = cost;
    }

    //methods
    public void castAbility(Enemy enemy){
        int attack = this.getAttack();
        int defend = enemy.defend();
        this.battle(enemy, attack, defend);

    }

    @Override
    public void gameTick() {
        setCurrent_energy(Math.min(getCurrent_energy() +10, 100));
    }

    public void castAbility(List<Enemy> enemyList){
        List<Enemy> enemiesClone = new ArrayList<>(enemyList);

        if (getCurrent_energy() > getCost()){
            setCurrent_energy(getCurrent_energy() - getCost());
            for (Enemy enemy : enemiesClone){
                if (this.getPosition().distance(enemy.getPosition()) < getRange())
                    enemy.specialAbility(this);
            }
        }
        else
            this.send(new Message(this.getName() + " has not enough energy"));
    }

    protected void acceptLvlUp(Player player){
        setCurrent_energy(100);
        setAttack(getAttack() + (3 * getLevel()));
        send(new Message(String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense",
                getName(), getLevel(), 10 * getLevel(), 7 *getLevel(), getLevel())));
    }
    public String describe(){
        return String.format("%s        Health: %d/%d       Attack: %d      Defense: %d     Level: %d\n     Experience: %d/%d       Energy: %d/%d",
                getName(), getHealthAmount(), getHealthPool(), getAttack(), getDefense(), getLevel(), getExperience(),
                getLevel() * 50, getCurrent_energy(), 100);
    }

    public Integer getCurrent_energy() {
        return current_energy;
    }

    public void setCurrent_energy(Integer current_energy) {
        this.current_energy = current_energy;
    }

    public Integer getCost() {
        return cost;
    }

    public int getRange() {
        return CONST_range;
    }
}
