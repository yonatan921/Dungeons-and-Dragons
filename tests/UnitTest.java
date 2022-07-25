import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    private Player player;
    private Enemy enemy;
    private Empty empty;
    private Wall wall;

    @BeforeEach
    void setUp() {
        player = new Warrior("Jon Snow", 300, 30, 4, 3);
        player.init(System.out::println);
        enemy = new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3);
        enemy.init(() -> {
        }, System.out::println);
        player.initialize(new Position(0, 0));
        enemy.initialize(new Position(0, 0));
        empty.initialize(new Position(0, 1));
        wall.initialize(new Position(0, 1));

    }

    @Test
    void PvE1() {
        Enemy enemy1 = new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3);
        enemy1.init(() -> {}, System.out::println);
        player.battle(enemy1, 10, 5);
        assertEquals(enemy1.getHealthPool() - 5, enemy1.getHealthAmount());
    }

    @Test
    void PvE2() {
        Enemy enemy1 = new Monster('s', "Lannister Solider", 80, 8, 3, 25, 3);
        enemy1.init(() -> {}, System.out::println);
        player.battle(enemy1, 5, 8);
        assertEquals(enemy1.getHealthPool(), enemy1.getHealthAmount());
    }

    @Test
    void PvE3() {
        Enemy enemy1 = new Monster('s', "Lannister Solider", 1, 8, 3, 25, 3);
        enemy1.init(() -> {}, System.out::println);
        player.battle(enemy1, 5, 0);
        assertEquals(0, enemy1.getHealthAmount());
    }

    @Test
    void PvEM() {
        player.interact(empty);
        assertEquals(new Position(0, 1), player.getPosition());
        assertEquals(new Position(0, 0), empty.getPosition());
    }

    @Test
    void PvW() {
        player.interact(wall);
        assertEquals(new Position(0, 0), player.getPosition());
        assertEquals(new Position(0, 1), wall.getPosition());
    }

    @Test
    void EvE() {
        Enemy enemy1 = new Monster('s', "Lannister Solider", 1, 8, 3, 25, 3);
        enemy1.initialize(new Position(1, 0));
        enemy.interact(enemy1);
        assertEquals(new Position(0, 0), enemy.getPosition());
        assertEquals(new Position(1, 0), enemy1.getPosition());
    }

    @Test
    void EvEM() {
        enemy.interact(empty);
        assertEquals(new Position(0, 1), enemy.getPosition());
        assertEquals(new Position(0, 0), empty.getPosition());
    }

    @Test
    void EvW() {
        enemy.interact(wall);
        assertEquals(new Position(0, 0), enemy.getPosition());
        assertEquals(new Position(0, 1), wall.getPosition());
    }

    @Test
    void EvP1(){
        Player player1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        player1.init( System.out::println);
        enemy.battle(player1, 10, 5);
        assertEquals(player1.getHealthPool() - 5, player1.getHealthAmount());
    }

    @Test
    void EvP2(){
        Player player1 = new Warrior("Jon Snow", 300, 30, 4, 3);
        player1.init( System.out::println);
        enemy.battle(player1, 5, 8);
        assertEquals(player1.getHealthPool(), player1.getHealthAmount());
    }

    @Test
    void EvP3(){
        Player player1 = new Warrior("Jon Snow", 1, 30, 4, 3);
        player1.init( System.out::println);
        enemy.battle(player1, 5, 0);
        assertEquals(0, player1.getHealthAmount());
    }

}