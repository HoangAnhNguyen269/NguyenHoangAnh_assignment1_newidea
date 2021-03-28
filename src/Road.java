import java.util.ArrayList;

public class Road {
    // sameY
    private String id; //id for the road
    private int speedLimit;
    private int length;
    private int[] startLocation = new int[2]; //(x,y)
    private int[] endLocation = new int[2];
    private ArrayList<Car> carsOnRoad = new ArrayList<>();
    private ArrayList<TrafficLight> lightsOnRoad = new ArrayList<>();
    private ArrayList<Road> connectedRoadsEnd = new ArrayList<>(); //the arraylist for other Roads connected at the end of this road
    private ArrayList<Road> connectedRoadsStart = new ArrayList<>();//the arraylist for other Roads connected at the start of this road
    //road vector is how the road look like. Assume from a horizontal road connects to 3 roads. A car on the road go from left to right on the road.
    // If we turn right, the roadVector ="right", turn left ->roadVector ="left" and straight forward roadVector ="straight"
    private static String roadVector;
    //private static CityMap Map;
    private ArrayList<Car> positiveCar;
    private ArrayList<Car> negativeCar;
    public boolean sameY; // whether startLocation and endLocation has the same y axis

    public Road(String id, int speedLimit, int length, int[] startLocation, int[] endLocation) {
        this.id = "road_" + id;
        this.speedLimit = speedLimit;
        this.length = length;
        this.startLocation = startLocation;
        this.endLocation = endLocation;

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

    public void setLength(int length) {
        this.length = length;
    }

    public String printStartLocation() {
        return startLocation[0] + "," + startLocation[1];
    }

    public void setStartLocation(int[] startLocation) {
        this.startLocation = startLocation;
        this.endLocation = new int[]{this.length + this.startLocation[0], 0}; //only works for horizontal roads;
    }

    public String printEndLocation() {
        return endLocation[0] + "," + endLocation[1];
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

    public ArrayList<TrafficLight> getLightsOnRoad() {
        return lightsOnRoad;
    }

    public void setLightsOnRoad(ArrayList<TrafficLight> lightsOnRoad) {
        this.lightsOnRoad = lightsOnRoad;
    }

    //GET and SET method for connected road arraylist; later I gonna use Map
    public ArrayList<Road> getConnectedRoadsEnd() {
        return connectedRoadsEnd;
    }

    public void setConnectedRoadsEnd(ArrayList<Road> connectedRoadsEnd) {
        this.connectedRoadsEnd = connectedRoadsEnd;
    }

    public ArrayList<Road> getConnectedRoadsStart() {
        return connectedRoadsStart;
    }

    public void setConnectedRoadsStart(ArrayList<Road> connectedRoadsStart) {
        this.connectedRoadsStart = connectedRoadsStart;
    }

}

