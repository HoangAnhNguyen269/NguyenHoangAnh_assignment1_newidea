/***********************
 * Car.java
 * Nguyen Hoang Anh
 *
 * This describes the Car class
 **********************/

import java.lang.*;
import java.util.*;
public class Car {
    private static final int STOPPED = 0; //car speed is 0m/s, change to STOPPED_SPEED
    private static final int NEXT_ROAD_INDEX = 0; //In the first protocol we only use 2 road connect to other
    String id; // unique identifier, change to carId
    static float length; // number of segments occupied, 1 for ease of prototype, change to carLength
    private static float breadth; // change to carBreadth
    private int speed; //segments moved per turn, change to carSpeed, the speed can be negative
    //When the speed is negative, for example: -1, the car moves from (0,0) to (-1,0)
    private int assignedSpeed; // using to store the speed when the speed meet the red traffic light
    //On this prototype,the map acts as an axis graph with x and y axis to identify the position of objects
    private float[] position = new float[2]; // position of car is the position of the head of car
    private float carRoadPosition;//The position of the car if we consider only the road containing the car only
    //remember to create method that change carRoadPosition to position
    private Road currentRoad; // current Road object


    public Car(String id, Road currentRoad) {
        //this.id = "car_" + id;
        this.setId("car_" + id);
        this.currentRoad = currentRoad;
        this.currentRoad.getCarsOnRoad().add(this); //add this car to the road its on.
        this.length = 1f; // cars made 1m long for prototype.
        this.breadth = length * 0.5f;
        //this.speed = 1; //default speed is 1
        this.setSpeed(1);//default speed is 1
        if(this.currentRoad.sameY ==true) //when all cars on the road have the same y value
        {
            this.position[1] = this.currentRoad.getStartLocation()[1];
            this.position[0] = this.length; //default the car is put on the start of the road
            this.carRoadPosition = this.position[0];
        } else{
            this.position[0] = this.currentRoad.getStartLocation()[1];
            this.position[1] = this.length;
            this.carRoadPosition = this.position[1];
        }
    }

    public void move() {
        this.setSpeed(this.assignedSpeed);
        boolean stopSign = false; //the boolean value tell the car when the car stop
        if(this.speed >0){
            if (this.currentRoad.getEndLight() != null && (this.carRoadPosition+this.speed) >= this.currentRoad.getEndLight().getPosition() && this.currentRoad.getEndLight().getState().equals("red")) {
                stopSign = true; //red light
            }else{
                stopSign =false; //green light or there is no traffic light.
            }
            if(stopSign == true){this.setCarRoadPosition(this.currentRoad.getLength());this.speed = 0;} //stop red light
            else{
                this.carRoadPosition = this.carRoadPosition + this.speed; //Now we only consider the position on the same road only
                if (this.currentRoad.getLength() <= this.getRoadPosition() && !this.currentRoad.getConnectedRoadsEnd().isEmpty()) {
                    float newPosition = this.carRoadPosition - this.currentRoad.getLength(); // position in the new road equals to the distance that the car has crossed
                    Road newRoad = this.currentRoad.getConnectedRoadsEnd().get(NEXT_ROAD_INDEX);
                    if(Arrays.equals(newRoad.getStartLocation(),this.currentRoad.getEndLocation())){
                        this.speed = Math.abs(this.speed); //What if the speed >speed limit -> new method
                    }
                    else{
                        this.speed = -1*Math.abs(this.speed);
                    }
                    this.setCurrentRoad(newRoad);
                    if(newPosition > this.getCurrentRoad().getSpeedLimit()){
                        newPosition = this.getCurrentRoad().getSpeedLimit();
                    }
                    if(this.speed>0){
                        this.setCarRoadPosition(newPosition);
                    }else{
                        this.setCarRoadPosition(this.currentRoad.getLength()-newPosition);
                    }

                } else if (this.currentRoad.getLength() <= this.getRoadPosition() && this.currentRoad.getConnectedRoadsEnd().isEmpty()) {
                    this.setSpeed(STOPPED);
                    this.setCarRoadPosition(this.currentRoad.getLength());
                } else { RoadPositionToPosition(); }

                /*
                ArrayList<Car> newPositiveCars = this.currentRoad.getPositiveCar();
                newPositiveCars.remove(this);
                for (Car car:newPositiveCars){ //When 2 car pass to other
                    for (Car negCar: this.currentRoad.getNegativeCar()){
                        if(this.getRoadPosition() <= car.getRoadPosition() && this.getRoadPosition() > (car.getRoadPosition()-car.getLength()) && negCar.getRoadPosition() <= car.getRoadPosition() && negCar.getRoadPosition() > (car.getRoadPosition()-car.getLength()))
                        {

                            this.setCarRoadPosition(car.carRoadPosition-car.getLength());// the faster car cannot overtake because theres another car on the opposite side
                        }
                    }
                } */
            }
        }

        else if(this.speed <0){
            if (this.currentRoad.getStartLight() != null && (this.carRoadPosition+this.speed) >= this.currentRoad.getStartLight().getPosition() && this.currentRoad.getStartLight().getState().equals("red")) {
                stopSign = true; //red light
            }else{
                stopSign =false; //green light or there is no traffic light.
            }
            if(stopSign == true){this.setCarRoadPosition(0);this.speed = 0;} //stop red light
            else{
                this.carRoadPosition = this.carRoadPosition + this.speed;
                if (this.getRoadPosition() <=0 && !this.currentRoad.getConnectedRoadsStart().isEmpty()){
                    float newPosition = this.carRoadPosition;
                    Road newRoad = this.currentRoad.getConnectedRoadsStart().get(NEXT_ROAD_INDEX); //We only connect to 1 road in the first prototype
                    if(Arrays.equals(newRoad.getStartLocation(),this.currentRoad.getStartLocation())){
                        this.speed = Math.abs(this.speed);
                    }
                    else{
                        this.speed = -1*Math.abs(this.speed);
                    }
                    this.setCurrentRoad(newRoad);
                    if(newPosition > this.getCurrentRoad().getSpeedLimit()){
                        newPosition = this.getCurrentRoad().getSpeedLimit();
                    }
                    if(this.speed>0){
                        this.setCarRoadPosition(-newPosition);
                    }else{
                        this.setCarRoadPosition(this.currentRoad.getLength()+newPosition);
                    }
                }else if (this.getRoadPosition() <=0 && this.currentRoad.getConnectedRoadsStart().isEmpty()) {
                    this.setSpeed(STOPPED);
                    this.setCarRoadPosition(0);
                }
                else{RoadPositionToPosition();}

                /*
                ArrayList<Car> newNegativeCars = this.currentRoad.getNegativeCar();
                newNegativeCars.remove(this);
                for (Car car:newNegativeCars){
                    for (Car posCar: this.currentRoad.getPositiveCar()){
                        if(this.getRoadPosition() >= car.getRoadPosition() && this.getRoadPosition() <(car.getRoadPosition()-car.getLength())&&posCar.getRoadPosition() >= car.getRoadPosition() && posCar.getRoadPosition() < (car.getRoadPosition()-car.getLength()))
                        {
                            this.setCarRoadPosition(car.getRoadPosition()+car.getLength()); // the faster car cannot overtake because theres another car on the opposite side
                        }
                    }
                } */
            }
        }
        else{}



    }


    public void RoadPositionToPosition(){
        if(this.currentRoad.sameY ==true) //when all cars on the road have the same y value
        {
            this.position[1] = this.currentRoad.getStartLocation()[1];
            this.position[0] = this.currentRoad.getStartLocation()[0]+this.carRoadPosition;
        } else{
            this.position[0] = this.currentRoad.getStartLocation()[1];
            this.position[1] = this.currentRoad.getStartLocation()[0]+this.carRoadPosition;
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
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if(Math.abs(this.speed) > this.currentRoad.getSpeedLimit()) {
            this.speed =  this.speed/Math.abs(this.speed) * this.currentRoad.getSpeedLimit(); //set speed limit to that of currentRoad
        }
        this.assignedSpeed = this.speed;
    }
    public void setSpeed() {
        if(Math.abs(this.speed) > this.currentRoad.getSpeedLimit()) {
            this.speed =  this.speed/Math.abs(this.speed) * this.currentRoad.getSpeedLimit(); //set speed limit to that of currentRoad
        }
        this.assignedSpeed = this.speed;
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
        this.currentRoad.getCarsOnRoad().remove(this);
        this.currentRoad = currentRoad;
        this.currentRoad.getCarsOnRoad().add(this);
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
