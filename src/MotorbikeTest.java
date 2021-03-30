import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MotorbikeTest {
    Road road = new Road("0", 6, new int[]{0, 0}, new int[]{0,3});
    Motorbike bike = new Motorbike("0",road);

    @Test
    void getLength() {
        assertEquals(0.5, bike.getLength());
    }

    @Test
    void getId() {
        assertEquals("bike_0", bike.getId());
    }

    @Test
    void testInheritance() {
        assertEquals(1, bike.getSpeed());
        assertEquals(1, bike.getRoadPosition());
    }
}