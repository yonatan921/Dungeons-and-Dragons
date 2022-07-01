import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Warrior extends Player {
    //fields
    protected Integer ability_cooldown;
    protected Integer remaining_cooldown =0;

    public int CONST_range = 3;

    public Warrior(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer ability_cooldown){
        super(name, health_pool, attack_points, defence_points);
        this.ability_cooldown = ability_cooldown;
    }

    //methods
    public void specialAbility(Enemy enemy){
        this.battle(enemy,  healthPool / 10, 0);
    }

    public void specialAbility(List<Enemy> enemyList){
        if (remaining_cooldown > 0){
            List<Enemy> inRange = new ArrayList<>();
            for (Enemy enemy : enemyList){
                if (this.position.distance(enemy.position) < CONST_range)
                    inRange.add(enemy);
            }
            Random random = new Random();
            Enemy randomEnemy = inRange.get(random.nextInt(inRange.size()));
            this.specialAbility(randomEnemy);

        }
    }

    protected void acceptLvlup(Player player){
        remaining_cooldown = 0;
        healthPool += 5 * Level;
        attack += 2 * Level;
        defense += Level;
    }
}
