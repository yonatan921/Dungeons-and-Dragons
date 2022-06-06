package Units;

public abstract class Trap extends Enemies {
    //fields
    protected Integer visibility_time;
    protected Integer invisibility_time;
    protected Integer tick_count = 0;
    protected Boolean visible = true;

    public Trap(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer experience_value,
                Integer visibility_time, Integer invisibility_time) {
        super(name, health_pool, attack_points, defence_points, experience_value);
        this.visibility_time = visibility_time;
        this.invisibility_time = invisibility_time;
    }
}
