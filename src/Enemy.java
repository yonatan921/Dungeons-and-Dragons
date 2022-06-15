public abstract class Enemy extends Unit {
    //fields
    protected Integer experience_value;
    protected Character tile;

    //constructors
    public Enemy(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer experience_value, Character tile) {
        super(tile,name, health_pool, attack_points, defence_points );
        this.experience_value = experience_value;
        this.tile = tile;
    }
}
