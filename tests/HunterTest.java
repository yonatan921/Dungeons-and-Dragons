import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HunterTest {
    private Hunter hunter;
    private Enemy enemy1;
    private Enemy enemy2;
    private List<Enemy> enemies;

    @BeforeEach
    void setUp() {
        hunter =  new Hunter("Ygritte", 220, 30, 2, 6);
        hunter.init(System.out::println);
        hunter.initialize(new Position(0,0));
        enemy1 = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        enemy2 = new Monster('s', "Lannister Solider", 80, 8, 3,25, 3);
        enemy1.init(()->{}, System.out::println);
        enemy2.init(()->{}, System.out::println);
        enemies = Arrays.asList(enemy1, enemy2);
    }

    @Test
    void castAbility1() { //only enemy 1 should get hit
        enemy1.initialize(new Position(1,0));
        enemy2.initialize(new Position(2,0));
        hunter.castAbility(enemies);
        assertTrue(enemy1.getHealthAmount() < enemy1.getHealthPool() && enemy2.getHealthAmount() == enemy2.getHealthPool());
    }

    @Test
    void castAbility2() { //no one should get hit
        enemy1.initialize(new Position(7,0));
        enemy2.initialize(new Position(8,0));
        hunter.castAbility(enemies);
        assertTrue(enemy1.getHealthAmount() == enemy1.getHealthPool() && enemy2.getHealthAmount() == enemy2.getHealthPool());
    }

    @Test
    void castAbility3() { //no one should get hit
        hunter.setArrows(0);
        enemy1.initialize(new Position(1,0));
        enemy2.initialize(new Position(10,0));
        hunter.castAbility(enemies);
        assertTrue(enemy1.getHealthAmount() == enemy1.getHealthPool() && enemy2.getHealthAmount() == enemy2.getHealthPool());
    }
}