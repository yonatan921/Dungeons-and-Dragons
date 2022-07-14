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

    @Override
    public void gameTick() {
        if (remaining_cooldown != 0)
            remaining_cooldown -= 1;
    }

    public void specialAbility(List<Enemy> enemyList){
        if (remaining_cooldown == 0){
            List<Enemy> inRange = new ArrayList<>();
            for (Enemy enemy : enemyList){
                if (this.position.distance(enemy.position) < CONST_range)
                    inRange.add(enemy);
            }
            Random random = new Random();
            if (!inRange.isEmpty()){
                Enemy randomEnemy = inRange.get(random.nextInt(inRange.size()));
                this.specialAbility(randomEnemy);
            }
            remaining_cooldown = ability_cooldown;
            healthAmount = Math.min(healthPool , healthAmount + 10 * defense);
        }
        else
            this.messageCallback.send(new Message(this.name + " has cooldown"));
    }

    protected void acceptLvlup(Player player){
        remaining_cooldown = 0;
        healthPool += 5 * Level;
        attack += 2 * Level;
        defense += Level;
    }
}
