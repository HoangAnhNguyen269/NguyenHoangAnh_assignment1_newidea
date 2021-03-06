/***********************
 * MapTest.java
 * Nguyen Hoang Anh
 *
 * This checks whether Map class work or not
 **********************/

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {
    Map map = new Map("0");
    Road road = new Road("0", map, 6, new int[]{0, 0}, new int[]{0, 3});
    Road connectedRoad = new Road("1", map, 6, new int[]{0, 3}, new int[]{0, 6});
    Road connectedRoad2 = new Road("2", map, 6, new int[]{0, 0}, new int[]{3, 0});
    Car car = new Car("0", road);

    @Test
    void testMove() {
        map.connectRoadsOnMap();
        car.setAssignedSpeed(1);
        map.moveCarsOnMap();
        map.moveCarsOnMap();
        map.moveCarsOnMap();
        assertEquals(1, car.getRoadPosition());
        assertEquals(connectedRoad, car.getCurrentRoad());
    }

    @Test
    void testId() {
        assertEquals("map_0", map.getMapId());
    }

    @Test
    void testRoads() {
        map.connectRoadsOnMap();
        ArrayList<String> testString = new ArrayList<String>();
        assertEquals(road, map.getRoadsOnMap().get(0));
        assertEquals(connectedRoad, map.getRoadsOnMap().get(1));
    }

    @Test
    void testConnect() {
        map.connectRoadsOnMap();
        assertEquals(connectedRoad, road.getConnectedRoadsEnd().get(0));
        assertEquals(connectedRoad2, road.getConnectedRoadsStart().get(0));
    }

    @Test
    void roadsOnMap() {
        map.connectRoadsOnMap();
        ArrayList<Road> roads = new ArrayList<Road>();
        roads.add(road);
        roads.add(connectedRoad);
        roads.add(connectedRoad2);
        assertEquals(roads, map.getRoadsOnMap());
    }

    @Test
    void carsOnMap() {
        map.connectRoadsOnMap();
        assertEquals(car, map.getCarsOnMap().get(0));
    }
}
