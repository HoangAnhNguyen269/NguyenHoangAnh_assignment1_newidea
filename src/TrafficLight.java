/***********************
 * TrafficLight.java
 * Nguyen Hoang Anh
 *
 * This describes the Traffic Light class
 **********************/

import java.util.Random;

public class TrafficLight {
    private static final double CHANGE_GREEN = 0.5; // 50/50 chance of changing state.
    private static final String GREEN = "green";
    private static final String RED = "red";
    private String id;
    private String state;
    private int position;
    private Road roadAttachedTo;

    public TrafficLight(String id, Road road, boolean stayAtTheEnd) {
        this.id = "light_" + id;
        state = RED;
        this.roadAttachedTo = road;
        if (stayAtTheEnd == true) {
            position = this.roadAttachedTo.getLength(); // always places the traffic light at the end of the roadAttachedTo.
            this.roadAttachedTo.setEndLight(this);
        } else {
            position = 0;
            this.roadAttachedTo.setStartLight(this);
        }
    }

    public void operate(int seed) {
        Random random = new Random(seed);
        double probability = random.nextDouble();
        if (probability > CHANGE_GREEN) {
            this.setState(GREEN);
        } else {
            this.setState(RED);
        }
    }

    public void printLightStatus() {
        System.out.printf("%s is:%s on %s at position:%s%n", this.getId(), this.getState(), this.getRoadAttachedTo().getId(), this.getPosition());
    }

    public String getState() {

        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Road getRoadAttachedTo() {
        return roadAttachedTo;
    }

    public void setRoadAttachedTo(Road roadAttachedTo) {
        this.roadAttachedTo = roadAttachedTo;
    }

    public int getPosition() {
        return position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
