package Units;

public abstract class Warrior extends Player {
    //fields
    protected Integer ability_cooldown;
    protected Integer remaining_cooldown =0;

    public Warrior(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer ability_cooldown){
        super(name, health_pool, attack_points, defence_points);
        this.ability_cooldown = ability_cooldown;
    }
}
