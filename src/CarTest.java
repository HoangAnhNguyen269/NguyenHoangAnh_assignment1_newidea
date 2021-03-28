import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CarTest {
    Road road = new Road("0", 1, new int[]{0, 0}, new int[]{0,3});
    Car car = new Car("0", road);

    @Test
    void testMove() {
        car.move();
        assertEquals(2, car.getRoadPosition());
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
    void getPosition() {
        assertEquals(1, car.getRoadPosition());
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
