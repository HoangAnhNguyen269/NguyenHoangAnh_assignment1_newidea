/***********************
 * CarTest.java
 * Nguyen Hoang Anh
 *
 * This checks whether Car class work or not
 **********************/
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CarTest {
    Road road = new Road("0", 6, new int[]{0, 0}, new int[]{0,3});
    Road connectedRoad = new Road("1",6,new int[]{0, 3}, new int[]{0,6});
    Road connectedRoad2 = new Road("2",6,new int[]{0, 0}, new int[]{3,0});
    Car car = new Car("0", road);

    @Test
    void testMove() {
        car.move();
        assertEquals(2, car.getRoadPosition());
    }
    @Test
    void testMoveToNewRoad() { //Test if Car go out of the road and come to new
        road.setAConnectedRoadEnd(connectedRoad);
        car.setSpeed(4);
        car.move();
        assertEquals(2, car.getRoadPosition());
        assertEquals(connectedRoad, car.getCurrentRoad());
    }
    @Test
    void testMoveToNewRoad2(){
        Road connectedRoad3 = new Road("3",6,new int[]{-3,3}, new int[]{0,3});
        road.getConnectedRoadsEnd().remove(connectedRoad); //On this prototype we only connect 1 road to end or start
        road.setAConnectedRoadEnd(connectedRoad3);
        car.setSpeed(4);
        car.move();
        assertEquals(1,car.getRoadPosition());
        assertEquals(connectedRoad3, car.getCurrentRoad());
    }
    @Test
    void testNegativeSpeed(){
        road.setAConnectedRoadEnd(connectedRoad);
        car.setCurrentRoad(connectedRoad);
        car.setCarRoadPosition(car.getCurrentRoad().getLength()-1);
        car.setSpeed(-4);
        car.move();
        assertEquals(1,car.getRoadPosition());
        assertEquals(road, car.getCurrentRoad());
    }
    @Test
    void testNegativeSpeed2(){
        road.setAConnectedRoadStart(connectedRoad2);
        car.setCarRoadPosition(car.getCurrentRoad().getLength()-1);
        car.setSpeed(-4);
        car.move();
        assertEquals(2,car.getRoadPosition());
        assertEquals(connectedRoad2, car.getCurrentRoad());
        assertEquals(4, car.getSpeed());
    }
    @Test
    void getLength() {
        assertEquals(1, car.getLength());

    }

    @Test
    void getBreadth() {
        assertEquals(0.5, car.getBreadth());

    }

    @Test
    void getSpeed() {
        assertEquals(1, car.getSpeed());
    }

    @Test
    void getRoadPosition() {
        assertEquals(1, car.getRoadPosition());

    }


    @Test
    void getPosition(){
        float expectOutput[] = {0f,1f};
        assertEquals( true, Arrays.equals(expectOutput,car.getPosition()));
    }
    @Test
    void getRoad() {
        assertEquals(road, car.getCurrentRoad());
    }

    @Test
    void getId() {
        assertEquals("car_0", car.getId());
    }
}
