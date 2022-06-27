public class Monster extends Enemy {
    //fields
    protected Integer vision_range;

    public Monster(Character tile ,String name, Integer health_pool, Integer attack_points, Integer defence_points,  Integer experience_value ,
                   Integer vision_range) {
        super(name, health_pool, attack_points, defence_points, experience_value, tile);
        this.vision_range = vision_range;
    }

}
