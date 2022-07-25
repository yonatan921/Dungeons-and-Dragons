public class Trap extends Enemy {
    //fields
    private final Integer visibility_time;
    private final Integer invisibility_time;
    private Integer tick_count = 0;
    private Boolean visible = true;
    private final Character ConstTile;
    private final int vision_range = 2;

    //constructors
    public Trap(Character tile,String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer experience_value,
                Integer visibility_time, Integer invisibility_time) {
        super(name, health_pool, attack_points, defence_points, experience_value, tile);
        this.visibility_time = visibility_time;
        this.invisibility_time = invisibility_time;
        this.ConstTile = tile;
    }
    //methods
    @Override
    public Position move(Player player) {
        return this.getPosition(); //Trap can't move!
    }

    @Override
    public void gameTick(Player player) {
        setVisibility(getTick_count() < getVisibility_time());
        if (getTick_count() == (getVisibility_time() + getInvisibility_time()))
            setTick_count(0);
        else
            setTick_count(getInvisibility_time() + 1); // add 1 to tick count
        if (this.getPosition().distance(player.getPosition()) < getVision_range()) // player is close
            this.battle(player); // attack player
    }

    public void setTick_count(Integer tick_count) {
        this.tick_count = tick_count;
    }

    public void setVisibility(Boolean visible) {
        this.visible = visible;
        if (getVisible())
            setTile(getConstTile()); // make tile visible
        else
            setTile('.'); // make tile invisible
    }
    public String describe(){
        return String.format("%s        Health: %d/%d       Attack: %d      Defense: %d     Experience Value: %d",
                getName(), getHealthAmount(), getHealthPool(), getAttack(), getDefense(), getExperience_value());
    }

    public int getVision_range() {
        return vision_range;
    }
    public Integer getVisibility_time() {
        return visibility_time;
    }

    public Integer getInvisibility_time() {
        return invisibility_time;
    }

    public Integer getTick_count() {
        return tick_count;
    }

    public Boolean getVisible() {
        return visible;
    }

    public Character getConstTile() {
        return ConstTile;
    }
}
