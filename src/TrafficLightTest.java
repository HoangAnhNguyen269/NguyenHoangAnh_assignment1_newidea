import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrafficLightTest {
    Road road = new Road("0", 6, new int[]{0, 0}, new int[]{0,3});
    TrafficLight light = new TrafficLight("0", road);

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
        Road connectedRoad = new Road("1",6,new int[]{0, 3}, new int[]{0,6});
        road.setAConnectedRoadEnd(connectedRoad);
        light.setState("green");
        car.setSpeed(4);
        car.move();
        assertEquals(2, car.getRoadPosition());
        assertEquals(connectedRoad, car.getCurrentRoad());
    }
    @Test
    void testCarAndTrafficLight2(){
        Car car = new Car("0", road);
        Road connectedRoad = new Road("1",3,new int[]{0, 3}, new int[]{0,6});
        road.setAConnectedRoadEnd(connectedRoad);
        light.setState("green");
        car.setSpeed(6);
        car.move();
        assertEquals(3, car.getRoadPosition());
        assertEquals(connectedRoad, car.getCurrentRoad());
    }


}

