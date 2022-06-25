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
        this.battle(enemy);
    }

    public void accept(Unit unit){
        unit.visit(this);
    }

    public void onDeath() {
        //game over
        this.tile = 'X';
        System.out.println("GAME OVER - PLAYER DIED");
    }

    protected void battle(Enemy e){
        int attackPoints = attack();
        int defensePoints = e.defend();
        if(attackPoints - defensePoints > 0) {
            if(e.healthAmount - (attackPoints - defensePoints) < 0) {
                Experience += e.experience_value;


                e.onDeath();
            } else {
                e.healthAmount = e.healthAmount - (attackPoints - defensePoints);
            }
        }
        System.out.println(this.getName() + " rolled "+ attackPoints + " attack points. " + e.getName() + " and rolled " + defensePoints + " defense points. and dealt " + (attackPoints - defensePoints) + " damage" );
        System.out.println("BATTLE WITH ENEMY");
    }
}
