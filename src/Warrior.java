import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    //fields
    private final Integer ability_cooldown;
    private Integer remaining_cooldown =0;
    private final int CONST_range = 3;

    //constructors
    public Warrior(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer ability_cooldown){
        super(name, health_pool, attack_points, defence_points);
        this.ability_cooldown = ability_cooldown;
    }

    //methods
    public void castAbility(Enemy enemy){
        this.battle(enemy,  getHealthPool() / 10, 0);
    }

    @Override
    public void gameTick() {
        if (getRemaining_cooldown() != 0)
            setRemaining_cooldown(getRemaining_cooldown() - 1);
    }

    public void castAbility(List<Enemy> enemyList){
        if (getRemaining_cooldown() == 0){
            List<Enemy> enemiesInRange = enemyList.stream().filter((e) -> this.getPosition().distance(e.getPosition()) <
                    this.getCONST_range()).toList();
            if (!enemiesInRange.isEmpty()){
                Random random = new Random();
                Enemy randomEnemy = enemiesInRange.get(random.nextInt(enemiesInRange.size()));
                this.castAbility(randomEnemy);
            }
            setRemaining_cooldown(getAbility_cooldown());
            setHealthAmount(Math.min(getHealthPool() , getHealthAmount() + 10 * getDefense()));
        }
        else
            this.send(new Message(this.getName() + " has cooldown"));
    }

    protected void acceptLvlUp(Player player){
        setRemaining_cooldown(0);
        setHealthPool(getHealthPool() + (5 * getLevel()) );
        setAttack(getAttack() + (2 * getLevel()));
        setDefense(getDefense() + getLevel());
        send(new Message(String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense",
                getName(), getLevel(), 15 * getLevel(), 6 *getLevel(), 3 * getLevel())));
    }

    public Integer getRemaining_cooldown() {
        return remaining_cooldown;
    }

    public int getCONST_range() {
        return CONST_range;
    }

    public Integer getAbility_cooldown() {
        return ability_cooldown;
    }

    public void setRemaining_cooldown(Integer remaining_cooldown) {
        this.remaining_cooldown = remaining_cooldown;
    }

    public String describe(){
        return String.format("%s    Health: %d/%d   Attack: %d  Defense: %d     Level: %d\n     Experience: %d/%d       Cooldown: %d/%d ",
                getName(), getHealthAmount(), getHealthPool(), getAttack(), getDefense(), getLevel(), getExperience(), getLevel() * 50, getRemaining_cooldown(), getAbility_cooldown());
    }
}
