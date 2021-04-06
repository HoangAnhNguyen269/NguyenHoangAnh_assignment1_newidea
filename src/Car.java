/***********************
 * Car.java
 * Nguyen Hoang Anh
 *
 * This describes the Car class
 **********************/

import java.lang.*;
import java.util.*;

public class Car {
    private static final int STOPPED = 0; //when the car speed is 0 m/s
    private static final int NEXT_ROAD_INDEX = 0; //In the first protocol we only use 2 road connect to other
    String id; // unique identifier for a car
    static float length; // number of segments occupied of a car
    private static float breadth;
    private int speed; //segments moved per turn, the speed can be negative
    //When the speed is negative, for example: -1, the car moves from (0,0) to (-1,0)
    private int assignedSpeed; // using to store the input speed from user
    //On this prototype,the map acts as an axis graph with x and y axis to identify the position of objects
    private float[] position = new float[2]; // position of car is the position of the head of car, form like (x,y)
    private float carRoadPosition;//The position of the car if we consider only the road containing the car only
    private Road currentRoad; // current Road object


    public Car(String id, Road currentRoad) {
        this.setId("car_" + id);
        this.currentRoad = currentRoad;
        this.currentRoad.addACar(this);//add this car to the road its on.
        this.length = 1f; // cars made 1m long for prototype.
        this.breadth = length * 0.5f;
        this.setSpeed(1);//default speed is 1
        this.assignedSpeed = 1;
        if (this.currentRoad.sameY == true) //when all cars on the road have the same y value
        {
            this.position[1] = this.currentRoad.getStartLocation()[1];
            this.position[0] = this.length; //default the car is put on the start of the road
            this.carRoadPosition = this.position[0];
        } else {
            this.position[0] = this.currentRoad.getStartLocation()[1];
            this.position[1] = this.length;
            this.carRoadPosition = this.position[1];
        }
    }

    public void move() {
        this.setSpeed(this.assignedSpeed);
        boolean stopSign = false; //the boolean value tell the car when the car stop
        if (this.speed > 0) { //when the speed >0, the car move from the direction that is from start to the end
            if (this.currentRoad.getEndLight() != null && (this.carRoadPosition + this.speed) >= this.currentRoad.getEndLight().getPosition() && this.currentRoad.getEndLight().getState().equals("red")) {
                stopSign = true; //red light
            } else {
                stopSign = false; //green light or there is no traffic light.
            }
            if (stopSign == true) {
                this.setCarRoadPosition(this.currentRoad.getLength());
                this.speed = 0;
            } //stop red light
            else {
                this.carRoadPosition = this.carRoadPosition + this.speed; //Now we only consider the position on the same road only
                if (this.currentRoad.getLength() <= this.getRoadPosition() && !this.currentRoad.getConnectedRoadsEnd().isEmpty()) {
                    float newPosition = this.carRoadPosition - this.currentRoad.getLength(); // position in the new road equals to the distance that the car has crossed
                    Road newRoad = this.currentRoad.getConnectedRoadsEnd().get(NEXT_ROAD_INDEX);
                    if (Arrays.equals(newRoad.getStartLocation(), this.currentRoad.getEndLocation())) {
                        this.speed = Math.abs(this.speed); //if the car move from the start of the new road
                    } else {
                        this.speed = -1 * Math.abs(this.speed);
                    }
                    this.setCurrentRoad(newRoad);
                    if (newPosition > this.getCurrentRoad().getSpeedLimit()) {// the car cant excess the speed limit of the new road
                        newPosition = this.getCurrentRoad().getSpeedLimit();
                    }
                    if (this.speed > 0) {
                        this.setCarRoadPosition(newPosition);
                    } else {
                        this.setCarRoadPosition(this.currentRoad.getLength() - newPosition);
                    }

                } else if (this.currentRoad.getLength() <= this.getRoadPosition() && this.currentRoad.getConnectedRoadsEnd().isEmpty()) {
                    this.setSpeed(STOPPED);
                    this.assignedSpeed = 0;
                    this.setCarRoadPosition(this.currentRoad.getLength());// the car reach the end of the road
                } else {
                    RoadPositionToPosition();
                }

            }
        } else if (this.speed < 0) {//when the speed >0, the car move from the direction that is from end to the start
            if (this.currentRoad.getStartLight() != null && (this.carRoadPosition + this.speed) >= this.currentRoad.getStartLight().getPosition() && this.currentRoad.getStartLight().getState().equals("red")) {
                stopSign = true; //red light
            } else {
                stopSign = false; //green light or there is no traffic light.
            }
            if (stopSign == true) {
                this.setCarRoadPosition(0);
                this.speed = 0;
            } //stop red light
            else {
                this.carRoadPosition = this.carRoadPosition + this.speed;
                if (this.getRoadPosition() <= 0 && !this.currentRoad.getConnectedRoadsStart().isEmpty()) {
                    float newPosition = this.carRoadPosition;
                    Road newRoad = this.currentRoad.getConnectedRoadsStart().get(NEXT_ROAD_INDEX); //We only connect to 1 road in the first prototype
                    if (Arrays.equals(newRoad.getStartLocation(), this.currentRoad.getStartLocation())) {
                        this.speed = Math.abs(this.speed);
                    } else {
                        this.speed = -1 * Math.abs(this.speed);
                    }
                    this.setCurrentRoad(newRoad);
                    if (-newPosition > this.getCurrentRoad().getSpeedLimit()) { // the car cant excess the speed limit of the new road
                        newPosition = -this.getCurrentRoad().getSpeedLimit();
                    }
                    if (this.speed > 0) {
                        this.setCarRoadPosition(-newPosition); //when the speed >0, that means the position are not negative
                    } else {
                        this.setCarRoadPosition(this.currentRoad.getLength() + newPosition);
                    }
                } else if (this.getRoadPosition() <= 0 && this.currentRoad.getConnectedRoadsStart().isEmpty()) {
                    this.setSpeed(STOPPED);
                    this.assignedSpeed = 0;
                    this.setCarRoadPosition(0);
                } else {
                    RoadPositionToPosition();
                }


            }
        } else {}


    }


    public void RoadPositionToPosition() {
        //This method convert the roadPosition to Position which is in (x,y) form
        if (this.currentRoad.sameY == true) //when all cars on the road have the same y value
        {
            this.position[1] = this.currentRoad.getStartLocation()[1];
            this.position[0] = this.currentRoad.getStartLocation()[0] + this.carRoadPosition;
        } else {
            this.position[0] = this.currentRoad.getStartLocation()[1];
            this.position[1] = this.currentRoad.getStartLocation()[0] + this.carRoadPosition;
        }
    }

    public void printCarStatus() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(), this.getCurrentRoad().
                getId(), this.getRoadPosition());
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        Car.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        Car.breadth = breadth;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if (Math.abs(this.speed) > this.currentRoad.getSpeedLimit()) {
            this.speed = this.speed / Math.abs(this.speed) * this.currentRoad.getSpeedLimit(); //set speed limit to that of currentRoad
        }
        //this.assignedSpeed = this.speed;
    }

    public void setSpeed() {
        if (Math.abs(this.speed) > this.currentRoad.getSpeedLimit()) {
            this.speed = this.speed / Math.abs(this.speed) * this.currentRoad.getSpeedLimit(); //set speed limit to that of currentRoad
        }
        //this.assignedSpeed = this.speed;
    }

    public void setAssignedSpeed(int assignedSpeed) { //every assignment for the speed have to use this method
        this.assignedSpeed = assignedSpeed;
    }

    public float[] getPosition() {
        return this.position;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    public void setCarRoadPosition(float position) {
        this.carRoadPosition = position;
        RoadPositionToPosition();
    }

    public float getRoadPosition() {
        return carRoadPosition;
    }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road currentRoad) {
        this.currentRoad.removeACar(this);
        this.currentRoad = currentRoad;
        this.currentRoad.addACar(this);
        this.setSpeed();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAssignedSpeed() {
        return this.assignedSpeed;
    }
}
