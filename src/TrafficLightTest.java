/***********************
 * TrafficLightTest.java
 * Nguyen Hoang Anh
 *
 * This checks the Traffic Light class work or not
 **********************/
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrafficLightTest {
    Map map = new Map("0");
    Road road = new Road("0", map,6, new int[]{0, 0}, new int[]{0,3});
    TrafficLight light = new TrafficLight("0", road, true);

    @Test
    void testOperate() {
        light.operate(1);
        assertEquals("green", light.getState());
    }

    @Test
    void getState() {
        assertEquals("red", light.getState());
    } //put random string so sth true sth false

    @Test
    void getRoad() {
        assertEquals(road, light.getRoadAttachedTo());
    }

    @Test
    void getPosition() {
        assertEquals(3, light.getPosition());
    }

    @Test
    void getId() {
        assertEquals("light_0", light.getId());
    }

    @Test
    void testCarAndTrafficLight(){
        Car car = new Car("0", road);
        Road connectedRoad = new Road("1",map,6,new int[]{0, 3}, new int[]{0,6});
        map.connectRoadsOnMap();
        light.setState("green");
        car.setAssignedSpeed(4);
        car.move();
        assertEquals(2, car.getRoadPosition());
        assertEquals(connectedRoad, car.getCurrentRoad());
    }



}

