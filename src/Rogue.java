public class Rogue extends Player {
    //fields
    protected Integer cost;
    protected Integer current_energy = 100;

    public Rogue(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer cost) {
        super(name, health_pool, attack_points, defence_points);
        this.cost = cost;
    }

    //methods
    public void specialAbility(Player player){

    }

//    @Override
//    public void accept(Unit unit) {
//        unit.visit(this);
//    }
//
//    @Override
//    public void processStep() {
//
//    }

//    @Override
//    public void onDeath() {
//        //game over
//    }

//    @Override
//    public void visit(Player p) {
//        //impossible scenario
//    }
//
//    @Override
//    public void visit(Enemy e) {
//        //start fight
//    }
protected void acceptLvlup(Player player){
    current_energy = 100;
    attack += 3 * Level;
    }
}
