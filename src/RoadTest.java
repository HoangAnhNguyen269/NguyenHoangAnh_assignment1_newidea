/***********************
 * RoadTest.java
 * Nguyen Hoang Anh
 *
 * This checks whether Road class work or not
 **********************/
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoadTest {
    Road road = new Road("0", 6, new int[]{0, 0}, new int[]{0,3});
    Road connectedRoad = new Road("1",6,new int[]{0, 3}, new int[]{0,6});
    Car car = new Car("0", road);
    TrafficLight light = new TrafficLight("0", road, true);
    @Test
    void getId() {
        assertEquals("road_0", road.getId());
    }

    @Test
    void getSpeedLimit() {
        assertEquals(6, road.getSpeedLimit());
    }

    @Test
    void getLength() {
        assertEquals(3, road.getLength());
    }

    @Test
    void getStartLocation() {
        int[] expected = {0, 0};
        assertArrayEquals(expected, road.getStartLocation());
    }

    @Test
    void getEndLocation() {
        int[] expected = {0, 3};
        assertArrayEquals(expected, road.getEndLocation());
    }

    @Test
    void getCars() {
        ArrayList<Car> expected = new ArrayList<>();
        expected.add(car);
        assertEquals(expected, road.getCarsOnRoad());
    }

    @Test
    void getLights() {
        TrafficLight expected = light;
        assertEquals(expected, road.getEndLight());
    }

    @Test
    void getConnectedRoadsEnd() {
        ArrayList<Road> expected = new ArrayList<>();
        assertEquals(expected, road.getConnectedRoadsEnd());
    }
    @Test
    void getConnectedRoadsStart() {
        ArrayList<Road> expected = new ArrayList<>();
        assertEquals(expected, road.getConnectedRoadsStart());
    }
}