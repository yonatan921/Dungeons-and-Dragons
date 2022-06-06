package Units;

public abstract class Monster extends Enemies{
    //fields
    protected Integer vision_range;

    public Monster(String name, Integer health_pool, Integer attack_points, Integer defence_points,  Integer vision_range,
                   Integer experience_value, Character tile) {
        super(name, health_pool, attack_points, defence_points, experience_value, tile);
        this.vision_range = vision_range;
    }
}
