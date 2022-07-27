import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Mage extends Player{
    //fields
    private Integer mana_pool;
    private Integer current_mana;
    private final Integer mana_cost;
    private Integer spell_power;
    private final Integer hits_count;
    private final Integer ability_range;

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
    public void castAbility(Enemy enemy){
        this.battle(enemy, getSpell_power(), enemy.defend());
    }

    @Override
    public void gameTick() {
        setCurrent_mana(Math.min(getMana_pool(), getCurrent_mana() + getLevel()));
    }

    public void castAbility(List<Enemy> enemyList){
        if (getMana_cost() <= getCurrent_mana()){
            setCurrent_mana(getMana_pool() - getMana_cost());
            int hits = 0;
            List<Enemy> inRange = enemyList.stream().filter((c) ->this.getPosition().distance(c.getPosition()) <
                    this.getAbility_range()).collect(Collectors.toList());
            while ((hits < getHits_count()) && !(inRange.isEmpty())){
                Random random = new Random();
                Enemy randomEnemy = inRange.get(random.nextInt(inRange.size()));
                this.castAbility(randomEnemy);
                hits++;
                if (randomEnemy.getHealthAmount() == 0) // todo const death value
                    inRange.remove(randomEnemy);
            }
        }
        else
            this.send(new Message(this.getName() + " has not enough mana"));
    }

    protected void acceptLvlUp(Player player){
       setMana_pool(getMana_pool() + (25 * getLevel()));
       setCurrent_mana(Math.min(getCurrent_mana() + 4/getMana_pool(), getMana_pool()));
       setSpell_power(getSpell_power() + (10 * getLevel()));
        send(new Message(String.format("%s reached level %d: +%d Health, +%d Attack, +%d Defense, +%d Maximum mana, +%d Spell power",
                getName(), getLevel(), 10 * getLevel(), 4 *getLevel(), getLevel(), 25 * getLevel(), 10 * getLevel())));
    }
    public String describe(){
        return String.format("%s        Health: %d/%d       Attack: %d      Defense: %d     Level: %d\n     Experience: %d/%d       Mana: %d/%d     Spell Power: %d",
                getName(), getHealthAmount(), getHealthPool(), getAttack(), getDefense(), getLevel(), getExperience(), getLevel() * 50,
                getCurrent_mana(), getMana_pool(), getSpell_power());
    }

    public Integer getMana_pool() {
        return mana_pool;
    }

    public Integer getMana_cost() {
        return mana_cost;
    }

    public Integer getCurrent_mana() {
        return current_mana;
    }

    public Integer getSpell_power() {
        return spell_power;
    }

    public Integer getHits_count() {
        return hits_count;
    }

    public Integer getAbility_range() {
        return ability_range;
    }

    public void setMana_pool(Integer mana_pool) {
        this.mana_pool = mana_pool;
    }

    public void setCurrent_mana(Integer current_mana) {
        this.current_mana = current_mana;
    }

    public void setSpell_power(Integer spell_power) {
        this.spell_power = spell_power;
    }
}
