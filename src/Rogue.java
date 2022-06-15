public class Rogue extends Player {
    //fields
    protected Integer cost;
    protected Integer current_energy = 100;

    public Rogue(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer cost) {
        super(name, health_pool, attack_points, defence_points);
        this.cost = cost;
    }

    //methods
    public void specialAbility(){}

    @Override
    public void accept(Unit unit) {

    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Enemy e) {

    }
}
