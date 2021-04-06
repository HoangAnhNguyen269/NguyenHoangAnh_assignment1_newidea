/***********************
 * Map.java
 * Nguyen Hoang Anh
 *
 * This describes the Map class
 **********************/

import java.util.ArrayList;
import java.util.Arrays;

public class Map {
    private String mapId;
    private ArrayList<Road> roadsOnMap = new ArrayList<Road>();
    private ArrayList<Car> carsOnMap = new ArrayList<Car>();

    public Map(String id) {
        this.mapId = "map_" + id;
    }

    public void setMapId(String id) {
        this.mapId = this.mapId = "map_" + id;
    }

    public String getMapId() {
        return this.mapId;
    }

    public void setRoadsOnMap(ArrayList<Road> roads) {
        this.roadsOnMap = roads;
        this.connectRoadsOnMap();
        this.setCarsOnMap();

    }

    public ArrayList<Road> getRoadsOnMap() {
        return this.roadsOnMap;
    }

    public void setARoadOnMap(Road road) {
        this.roadsOnMap.add(road);
        //this.connectRoadsOnMap(); //this line gets error, have to connect by method everytime invoke the map
        this.setCarsOnMap();
    }

    public void removeARoadOnMap(Road road) {
        this.roadsOnMap.remove(road);
        this.connectRoadsOnMap();
        this.setCarsOnMap();
    }

    public void connectRoadsOnMap() { //connect all roads on a map
        for (Road road : this.roadsOnMap) {
            road.getConnectedRoadsEnd().clear();
            road.getConnectedRoadsStart().clear();
        }
        for (int i = 0; i < this.roadsOnMap.size() - 1; i++) {
            for (int count = i + 1; count < this.roadsOnMap.size(); count++) {
                if (Arrays.equals(this.roadsOnMap.get(i).getEndLocation(), this.roadsOnMap.get(count).getStartLocation()) || Arrays.equals(this.roadsOnMap.get(i).getEndLocation(), this.roadsOnMap.get(count).getEndLocation())) {
                    if (!this.roadsOnMap.get(i).getConnectedRoadsEnd().contains(this.roadsOnMap.get(count))) {
                        this.roadsOnMap.get(i).setAConnectedRoadEnd(this.roadsOnMap.get(count));

                    }
                } else if (Arrays.equals(this.roadsOnMap.get(i).getStartLocation(), this.roadsOnMap.get(count).getStartLocation()) || Arrays.equals(this.roadsOnMap.get(i).getStartLocation(), this.roadsOnMap.get(count).getEndLocation())) {
                    if (!this.roadsOnMap.get(i).getConnectedRoadsStart().contains(this.roadsOnMap.get(count))) {
                        this.roadsOnMap.get(i).setAConnectedRoadStart(this.roadsOnMap.get(count));
                    }
                } else {
                    continue;
                }
            }
        }
    }

    public void setCarsOnMap() { //set all cars exist on the map
        carsOnMap = new ArrayList<Car>();
        for (Road road : this.getRoadsOnMap()) {
            for (Car car : road.getCarsOnRoad()) {
                this.carsOnMap.add(car);
            }
        }
    }

    public ArrayList<Car> getCarsOnMap() {
        return this.carsOnMap;
    }

    public void moveCarsOnMap() {
        for (Car car : this.carsOnMap) {
            car.move();
        }
        for (Road road : this.getRoadsOnMap()) {
            road.carsOnRoadMoveCheck();
        }
    }
}
