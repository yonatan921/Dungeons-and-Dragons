package Units;

public abstract class Rogue extends Player {
    //fields
    protected Integer cost;
    protected Integer current_energy = 100;

    public Rogue(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer cost) {
        super(name, health_pool, attack_points, defence_points);
        this.cost = cost;
    }
}
