import java.util.Random;

public class Monster extends Enemy {
    //fields
    private final Integer vision_range;

    public Monster(Character tile ,String name, Integer health_pool, Integer attack_points, Integer defence_points,  Integer experience_value ,
                   Integer vision_range) {
        super(name, health_pool, attack_points, defence_points, experience_value, tile);
        this.vision_range = vision_range;
    }

    @Override
    public Position move(Player player) {
        Position newPosition = new Position(this.getPosition().getX(), this.getPosition().getY());
        if (getPosition().distance(player.getPosition()) < getVision_range()){ // chase the player
            int x = newPosition.getX() - player.getPosition().getX();
            int y = newPosition.getY() - player.getPosition().getY();
            if (Math.abs(x) > Math.abs(y)){
                if (x > 0)
                    newPosition.moveLeft();
                else
                    newPosition.moveRight();
            }
            else {
                if (y > 0)
                    newPosition.moveUp();
                else
                    newPosition.moveDown();
            }
        }
        else {  // make a random move
            Random random = new Random();
            int n = random.nextInt(4) ;
            if (n == 0)
                newPosition.moveUp();
            if (n == 1)
                newPosition.moveRight();
            if (n == 2)
                newPosition.moveDown();
            else
                newPosition.moveLeft();
        }
        return newPosition;
    }

    @Override
    public void gameTick(Player player) {

    }

    public Integer getVision_range() {
        return vision_range;
    }

    public String describe(){
        return String.format("%s        Health: %d/%d       Attack: %d      Defense: %d     Experience Value: %d        Vision Range: %d ",
                getName(), getHealthAmount(), getHealthPool(), getAttack(), getDefense(), getExperience_value(), getVision_range());
    }

}
