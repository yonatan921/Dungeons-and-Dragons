package Units;
public abstract class Player extends Units {
    //fields
    protected Integer Experience = 0;
    protected Integer Level = 1;

    //constructor
    public Player(String name, Integer health_pool, Integer attack_points, Integer defence_points){
        super(name, health_pool, attack_points, defence_points, '@'); //player will always be represented with '@'
    }

}
