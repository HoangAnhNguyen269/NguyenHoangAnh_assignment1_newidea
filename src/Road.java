/***********************
 * Road.java
 * Nguyen Hoang Anh
 *
 * This describes the Road class
 **********************/

import java.util.*;

public class Road {
    // sameY
    private Map roadMap;
    private String id; //id for the road
    private int speedLimit;
    private int length;
    private int[] startLocation = new int[2]; //(x,y)
    private int[] endLocation = new int[2];
    private ArrayList<Car> carsOnRoad = new ArrayList<>();
    private TrafficLight startLight;
    private TrafficLight endLight;
    private ArrayList<Road> connectedRoadsEnd = new ArrayList<>(); //the arraylist for other Roads connected at the end of this road
    private ArrayList<Road> connectedRoadsStart = new ArrayList<>();//the arraylist for other Roads connected at the start of this road

    public boolean sameY; // whether startLocation and endLocation has the same y axis

    public Road(String id, Map map, int speedLimit, int[] startLocation, int[] endLocation) {
        this.id = "road_" + id;
        this.setMap(map);
        this.speedLimit = speedLimit;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        if (this.startLocation[1] == this.endLocation[1]) {
            this.sameY = true;
            this.length = Math.abs(this.startLocation[0] - this.endLocation[0]);
        } else {
            this.sameY = false;
            this.length = Math.abs(this.startLocation[1] - this.endLocation[1]);
        }

    }

    public void carsOnRoadMoveCheck() { //check some situation that a car cannot pass another car in the same direction when there is a car on the opposite direction on the same position

        for (Car aCar : this.getPositiveCar()) {
            ArrayList<Car> newPositiveCars = aCar.getCurrentRoad().getPositiveCar();
            newPositiveCars.remove(aCar);
            for (Car car : newPositiveCars) { //When 2 car pass to other
                for (Car negCar : aCar.getCurrentRoad().getNegativeCar()) {
                    if (aCar.getRoadPosition() <= car.getRoadPosition() && aCar.getRoadPosition() > (car.getRoadPosition() - car.getLength()) && negCar.getRoadPosition() <= car.getRoadPosition() && negCar.getRoadPosition() > (car.getRoadPosition() - car.getLength())) {
                        aCar.setCarRoadPosition(car.getRoadPosition() - car.getLength());// the faster car cannot overtake because theres another car on the opposite side
                    }
                }
            }
        }
        for (Car aCar : this.getNegativeCar()) {
            ArrayList<Car> newNegativeCars = aCar.getCurrentRoad().getNegativeCar();
            newNegativeCars.remove(aCar);
            for (Car car : newNegativeCars) {
                for (Car posCar : aCar.getCurrentRoad().getPositiveCar()) {
                    if (aCar.getRoadPosition() >= car.getRoadPosition() && aCar.getRoadPosition() < (car.getRoadPosition() - car.getLength()) && posCar.getRoadPosition() >= car.getRoadPosition() && posCar.getRoadPosition() < (car.getRoadPosition() - car.getLength())) {
                        aCar.setCarRoadPosition(car.getRoadPosition() + car.getLength()); // the faster car cannot overtake because theres another car on the opposite side
                    }
                }
            }
        }
    }

    public void setMap(Map map) {
        this.roadMap = map;
        map.setARoadOnMap(this);

    }

    public void addACar(Car car) {
        this.carsOnRoad.add(car);
        this.roadMap.setCarsOnMap();
    }

    public void removeACar(Car car) {
        this.carsOnRoad.remove(car);
        this.roadMap.setCarsOnMap();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getLength() {
        return length;
    }

    public String printStartLocation() {
        return "(" + Integer.toString(startLocation[0]) + "," + Integer.toString(startLocation[1]) + ")";
    }

    public void setStartLocation(int[] startLocation) {
        this.startLocation = startLocation;
        this.endLocation = new int[]{this.length + this.startLocation[0], 0}; //only works for horizontal roads;
    }

    public String printEndLocation() {
        return "(" + Integer.toString(endLocation[0]) + "," + Integer.toString(endLocation[1]) + ")";
    }

    public void printRoadInfo() {
        System.out.printf("%s limit of:%dm/s is %dm long at location:%s to %s%n", this.getId(), this.getSpeedLimit(), this.getLength(), this.printStartLocation(), this.printEndLocation());
    }

    public void setEndLocation(int[] endLocation) {
        this.endLocation = endLocation;
    }

    public int[] getStartLocation() {
        return startLocation;
    }

    public int[] getEndLocation() {
        return endLocation;
    }

    public ArrayList<Car> getCarsOnRoad() {
        return carsOnRoad;
    }

    public void setCarsOnRoad(ArrayList<Car> carsOnRoad) {
        this.carsOnRoad = carsOnRoad;
    }

    public void setStartLight(TrafficLight light) {
        this.startLight = light;
    }

    public void setEndLight(TrafficLight light) {
        this.endLight = light;
    }

    public TrafficLight getStartLight() {
        return this.startLight;
    }

    public TrafficLight getEndLight() {
        return this.endLight;
    }

    //GET and SET method for connected road arraylist; later I gonna use Map
    public ArrayList<Road> getConnectedRoadsEnd() {
        return connectedRoadsEnd;
    }

    public void setConnectedRoadsEnd(ArrayList<Road> connectedRoadsEnd) {
        this.connectedRoadsEnd = connectedRoadsEnd;
    }

    public void setAConnectedRoadEnd(Road connectedRoadEnd) {
        this.connectedRoadsEnd.add(connectedRoadEnd);
        if (Arrays.equals(this.getEndLocation(), connectedRoadEnd.getStartLocation())) {
            connectedRoadEnd.getConnectedRoadsStart().add(this);

        } else {
            connectedRoadEnd.getConnectedRoadsEnd().add(this);
        }
    }

    public ArrayList<Road> getConnectedRoadsStart() {
        return connectedRoadsStart;
    }


    public void setConnectedRoadsStart(ArrayList<Road> connectedRoadsStart) {
        this.connectedRoadsStart = connectedRoadsStart;
    }

    public void setAConnectedRoadStart(Road connectedRoadsStart) {
        this.connectedRoadsStart.add(connectedRoadsStart);
        if (Arrays.equals(this.getStartLocation(), connectedRoadsStart.getStartLocation())) {
            connectedRoadsStart.getConnectedRoadsStart().add(this);
        } else {
            connectedRoadsStart.getConnectedRoadsEnd().add(this);
        }
    }

    public ArrayList<Car> getPositiveCar() { //cars that move from start to the end of the road
        ArrayList<Car> positiveCar = new ArrayList<>();
        for (Car car : this.carsOnRoad) {
            if (car.getSpeed() > 0) {
                positiveCar.add(car);
            }
        }
        return positiveCar;
    }

    public ArrayList<Car> getNegativeCar() {//cars that move from end to the start of the road
        ArrayList<Car> negativeCar = new ArrayList<>();
        for (Car car : this.carsOnRoad) {
            if (car.getSpeed() < 0) {
                negativeCar.add(car);
            }
        }
        return negativeCar;
    }

}