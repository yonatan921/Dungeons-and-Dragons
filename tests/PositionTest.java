import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position position;
    @BeforeEach
    void setUp() {
        position = new Position(1,1);
    }

    @Test
    void compareTo1() {
        Position position1 = new Position(0, 0);
        assertEquals(1, position.compareTo(position1));
    }

    @Test
    void compareTo2() {
        Position position1 = new Position(1, 1);
        assertEquals(0, position.compareTo(position1));
    }

    @Test
    void compareTo3() {
        Position position1 = new Position(1, 2);
        assertEquals(-1, position.compareTo(position1));
    }

    @Test
    void distance1() {
        Position position1 = new Position(1,1);
        assertEquals(0, position.distance(position1));
    }

    @Test
    void distance2() {
        Position position1 = new Position(2,2);
        assertEquals(Math.sqrt(2), position.distance(position1));
    }

    @Test
    void distance3() {
        Position position1 = new Position(2,1);
        assertEquals(1, position.distance(position1));
    }

    @Test
    void testEquals1() {
        Position position1 = new Position(1,1);
        assertTrue(position.equals(position1));
    }

    @Test
    void testEquals2() {
        Position position1 = new Position(0,1);
        assertFalse(position.equals(position1));
    }


}