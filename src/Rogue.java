import java.util.List;

public class Rogue extends Player {
    //fields
    protected Integer cost;
    protected Integer current_energy = 100;

    private int CONST_range = 2;

    public Rogue(String name, Integer health_pool, Integer attack_points, Integer defence_points, Integer cost) {
        super(name, health_pool, attack_points, defence_points);
        this.cost = cost;
    }

    //methods
    public void specialAbility(Enemy enemy){
        int attack = this.attack;
        int defend = enemy.defend();
        this.battle(enemy, attack, defend);

    }
    public void specialAbility(List<Enemy> enemyList){
        if (current_energy > cost){
            current_energy -= cost;
            for (Enemy enemy : enemyList){
                if (this.position.distance(enemy.position) < CONST_range)
                    enemy.specialAbility(this);
            }
        }
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
