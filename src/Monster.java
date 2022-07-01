import java.util.Random;

public class Monster extends Enemy {
    //fields
    protected Integer vision_range;

    public Monster(Character tile ,String name, Integer health_pool, Integer attack_points, Integer defence_points,  Integer experience_value ,
                   Integer vision_range) {
        super(name, health_pool, attack_points, defence_points, experience_value, tile);
        this.vision_range = vision_range;
    }

    @Override
    public Position move(Player player) {
        Position newPosition = new Position(this.position.x, this.position.y);
        if (this.position.distance(player.position) < vision_range){
            int x = newPosition.x - player.position.x;
            int y = newPosition.y - player.position.y;
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
        else {
            Random random = new Random();
            int n = random.nextInt(4) ;
            if (n == 0)
                newPosition.moveUp();
            if (n==1)
                newPosition.moveRight();
            if (n==2)
                newPosition.moveDown();
            else
                newPosition.moveLeft();
        }
        return newPosition;
    }
}
