/***********************
 * BusTest.java
 * Nguyen Hoang Anh
 *
 * This test whether Bus class work or not
 **********************/
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BusTest {
    Road road = new Road("0", 1, new int[]{0, 0}, new int[]{0,3});
    Bus bus = new Bus("0",road);

    @Test
    void getLength() {
        assertEquals(3, bus.getLength());
    }

    @Test
    void getId() {
        assertEquals("bus_0", bus.getId());
    }

    @Test
    void testInheritance() {
        assertEquals(1, bus.getSpeed());
        assertEquals(1, bus.getRoadPosition());
        float expectOutput[] = {0f,1f};
        assertEquals( true, Arrays.equals(expectOutput,bus.getPosition()));

    }
}