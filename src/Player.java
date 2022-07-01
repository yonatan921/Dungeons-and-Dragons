import java.util.List;

public abstract class Player extends Unit implements EnemyDeathCallback {
    //fields
    protected Integer Experience = 0;
    protected Integer Level = 1;

    //constructor
    public Player(String name, Integer health_pool, Integer attack_points, Integer defence_points){
        super('@',name, health_pool, attack_points, defence_points); //player will always be represented with '@'
    }

    public abstract void specialAbility(List<Enemy> enemyList);

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
        this.battle(e, attackPoints, defensePoints);
    }
    public void call(Enemy e){
//        e.onDeath();
        Experience += e.experience_value;
        if (Experience >= (Level * 50)){
            this.levelUp();
        }
        this.position = e.position;
    }

    private void levelUp(){
        Experience = Experience - (50 * Level);
        Level += 1;
        healthPool = healthPool + (10 * Level);
        healthAmount = healthPool;
        attack = attack +(4 * Level);
        defense = defense + Level;
        this.acceptLvlup(this);

    }

    protected void acceptLvlup(Player player){
        player.acceptLvlup(this);
    }

    public abstract void specialAbility(Enemy enemy);

    protected void battle(Enemy e, int playerAttck, int enemyDefend ){

        if(playerAttck - enemyDefend > 0) {
            if(e.healthAmount - (playerAttck - enemyDefend) < 0) {
                this.call(e);
            } else {
                e.healthAmount = e.healthAmount - (playerAttck - enemyDefend);
            }
        }
        System.out.println(this.getName() + " rolled "+ playerAttck + " attack points. " + e.getName() + " and rolled " + enemyDefend + " defense points. and dealt " + (playerAttck - enemyDefend) + " damage" );
        System.out.println("BATTLE WITH ENEMY");
    }
}
