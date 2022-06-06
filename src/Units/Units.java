package Units;

public abstract class Units {
    //fields
    protected String name;
    protected Integer health_pool;
    protected Integer health_amount;
    protected Integer attack_points;
    protected Integer defence_points;

    public Units(String name, Integer health_pool, Integer attack_points, Integer defence_points){
        this.name = name;
        this.health_pool = health_pool;
        this.health_amount = health_pool;
        this.attack_points = attack_points;
        this.defence_points = defence_points;
    }
}
