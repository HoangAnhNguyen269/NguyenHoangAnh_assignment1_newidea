/***********************
 * MotorbikeTest.java
 * Nguyen Hoang Anh
 *
 * This checks whether Motorbike class work or not
 **********************/

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MotorbikeTest {
    Map map = new Map("0");
    Road road = new Road("0", map, 6, new int[]{0, 0}, new int[]{0, 3});
    Motorbike bike = new Motorbike("0", road);

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