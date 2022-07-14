public abstract class Enemy extends Unit {
    //fields
    protected Integer experience_value;
    protected Character tile;
    private DeathCallback dcb;
    private MessageCallback mcb;

    //constructors
    public Enemy(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer experience_value, Character tile) {
        super(tile, name, health_pool, attack_points, defence_points);
        this.experience_value = experience_value;
        this.tile = tile;
    }
    public void init(DeathCallback edc, MessageCallback mcb){
        this.dcb = edc;
        this.mcb = mcb;

    }

    public void visit(Enemy enemy) {
        //do nothing
    }

    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void onDeath() {
        dcb.onDeath();
        mcb.send(new Message(this.name + " died"));
    }

    public void specialAbility(Player player){
        player.specialAbility(this);
    }

    public abstract Position move(Player player);

    public void levelUp(){
        //DO nothing
    }
}
