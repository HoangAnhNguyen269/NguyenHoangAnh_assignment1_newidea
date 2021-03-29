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
        System.out.println("hi");
        float[] hi = bus.getPosition();
        String hello = Arrays.toString(hi);
        System.out.println(hello);
    }
}
