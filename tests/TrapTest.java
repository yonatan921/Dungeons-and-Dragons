import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {
    private Trap trap;

    @BeforeEach
    void setUp() {
        trap = new Trap('B', "Bonus Trap", 1, 10, 1, 250,  1, 5);
        trap.init(()->{}, System.out::println);
        trap.initialize(new Position(0,0));
    }

    @Test
    void gameTick1() { // hit player
        Player player = new Warrior("Jon Snow", 300, 30, 0, 3);
        player.init(System.out::println);
        player.initialize(new Position(1,1));
        trap.gameTick(player);
        assertTrue(player.getHealthAmount() < player.getHealthPool());

    }

    @Test
    void gameTick2() { // player not in range
        Player player = new Warrior("Jon Snow", 300, 30, 0, 3);
        player.init(System.out::println);
        player.initialize(new Position(2,2));
        trap.gameTick(player);
        assertEquals(player.getHealthAmount(), player.getHealthPool());

    }

    @Test
    void gameTick3() { // trap invisible
        Player player = new Warrior("Jon Snow", 300, 30, 0, 3);
        player.init(System.out::println);
        player.initialize(new Position(2,2));
        boolean visible = trap.getVisible();
        for (int i = 0; i < trap.getVisibility_time() + 1; i++)
            trap.gameTick(player);
        assertNotEquals(visible, trap.getVisible());
    }

    @Test
    void toString1() { // trap invisible
        trap.setVisibility(true);
       assertEquals(trap.toString(), String.valueOf('B'));
    }

    @Test
    void toString2() { // trap visible
        trap.setVisibility(false);
        assertEquals(trap.toString(), String.valueOf('.'));
    }
}