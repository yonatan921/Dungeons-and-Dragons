public abstract class Player extends Unit {
    //fields
    protected Integer Experience = 0;
    protected Integer Level = 1;

    //constructor
    public Player(String name, Integer health_pool, Integer attack_points, Integer defence_points){
        super('@',name, health_pool, attack_points, defence_points); //player will always be represented with '@'
    }

    public abstract void specialAbility();

    public void visit(Player player) {
        //do nothing
    }

    public void visit(Enemy enemy){
        //start fight
    }

    public void accept(Unit unit){
        unit.visit(this);
    }

    public void onDeath() {
        //game over
        System.out.println("GAME OVER - PLAYER DIED");
    }
}
