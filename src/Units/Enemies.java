package Units;

public abstract class Enemies extends Units {
    //fields
    protected Integer experience_value;
    protected Character tile;

    public Enemies(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer experience_value, Character tile) {
        super(name, health_pool, attack_points, defence_points);
        this.experience_value = experience_value;
        this.tile = tile;
    }
}
