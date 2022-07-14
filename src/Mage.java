import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mage extends Player{
    //fields
    public Integer mana_pool;
    protected Integer current_mana;
    protected Integer mana_cost;
    protected Integer spell_power;
    protected Integer hits_count;
    protected Integer ability_range;

    //constructors
    public Mage(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer mana_pool,
                Integer mana_cost , Integer spell_power, Integer hits_count, Integer ability_range) {
        super(name, health_pool, attack_points, defence_points);
        this.mana_pool = mana_pool;
        this.current_mana = mana_pool;
        this.mana_cost = mana_cost;
        this.spell_power = spell_power;
        this.hits_count = hits_count;
        this.ability_range = ability_range;
    }

    //methods
    public void specialAbility(Enemy enemy){
        this.battle(enemy, spell_power, enemy.defend());
    }

    @Override
    public void gameTick() {
        current_mana = Math.min(mana_pool, current_mana + Level);
    }

    public void specialAbility(List<Enemy> enemyList){
        if (mana_cost <= current_mana){
            current_mana -= mana_cost;
            int hits = 0;
            List<Enemy> inRange = new ArrayList<>();
            for (Enemy enemy : enemyList){
                if (this.position.distance(enemy.position) < ability_range)
                    inRange.add(enemy);
            }
            while ((hits < hits_count) && !(inRange.isEmpty())){
                Random random = new Random();
                Enemy randomEnemy = inRange.get(random.nextInt(inRange.size()));
                this.specialAbility(randomEnemy);
                hits++;
            }
        }
        //Todo not enough resources
    }

    protected void acceptLvlup(Player player){
       mana_pool += 25 * Level;
       current_mana = Math.min(current_mana + 4/mana_pool, mana_pool);
       spell_power += 10 * Level;
    }

}
