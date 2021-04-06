/***********************
 * Motorbike.java
 * Nguyen Hoang Anh
 *
 * This describes the Motorbike class which inherited from Car class
 **********************/
public class Motorbike extends Car {
    public Motorbike(String id, Road currentRoad) {
        super(id, currentRoad);
        this.id = ("bike_" + id);
        length = super.getLength() * 0.5f;
    }
}


