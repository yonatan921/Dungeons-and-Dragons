public abstract class Enemy extends Unit {
    //fields
    protected Integer experience_value;
    protected Character tile;

    //constructors
    public Enemy(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer experience_value, Character tile) {
        super(tile, name, health_pool, attack_points, defence_points);
        this.experience_value = experience_value;
        this.tile = tile;
    }

    @Override
    public void visit(Player player) {
        //start fight
        this.battle(player);
    }

    public void visit(Enemy enemy) {
        //do nothing
    }

    public void accept(Unit unit) {
        unit.visit(this);
    }

    public void onDeath() {
        // +xp
        // -enemy
        // move to its position
        System.out.println("ENEMY DIED");
    }

    protected void battle(Player p) {
        int attack = attack();
        int defense = p.defend();
        if (attack - defense > 0) {
            if (p.defense - (attack - defense) < 0) {
                p.onDeath();
            } else {
                p.defense = p.defense - (attack - defense);
            }
        }
        System.out.println(this.getName() + " attacked " + p.getName() + " and dealt it " + (attack - defense) + " points");
        System.out.println("BATTLE WITH PLAYER");
    }
}
