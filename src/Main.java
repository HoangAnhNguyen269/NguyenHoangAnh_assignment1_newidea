/***********************
 * Main.java
 * Nguyen Hoang Anh
 *
 * This is the first prototype for Assignment1
 **********************/
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Get info needed to start sim:
        Scanner simController = new Scanner(System.in);



        // set values for user inputs for prototype.
        int roadSpawns = 2;
        int carSpawns = 1;
        int lightSpawns = 1;


        //Create objects:

        System.out.println("Prototype1: A single car move on 2 road which are connected straightly\n---------------------");
        System.out.println("Both roads lay horizontally\n---------------------");
        System.out.println("Object Creation:\n---------------------");
        System.out.println("Roads:");
        System.out.println("Road_0: Start from (0,0)");
        System.out.println("Please input parameters for road_0:...");
        System.out.print("Length:");
        int lengthInput = simController.nextInt();
        System.out.print("speed limit:");
        int speedInput = simController.nextInt();
        Road road_0 = new Road(Integer.toString(0), speedInput, new int[]{0, 0}, new int[]{lengthInput, 0});
        System.out.println("Road_1: Start from (" +Integer.toString(road_0.getEndLocation()[0])+",0)");
        System.out.println("Please input parameters for road_1:...");
        System.out.print("Length:");
        lengthInput = simController.nextInt();
        System.out.print("speed limit:");
        speedInput = simController.nextInt();
        Road road_1 = new Road(Integer.toString(1), speedInput, road_0.getEndLocation(), new int[]{lengthInput+road_0.getEndLocation()[0], 0});

        ArrayList<Road> roads = new ArrayList<>();
        roads.add(road_0);
        roads.add(road_1);
        for (Road road : roads) {
            road.printRoadInfo();
        }

        System.out.println("\nCars;");
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < carSpawns; i++) {
            cars.add(new Car(Integer.toString(i), roads.get(0))); // all created cars will begin on road_0.
            cars.get(i).printCarStatus();
        }

        System.out.println("\nTraffic Lights;");
        ArrayList<TrafficLight> lights = new ArrayList<>();
        for (int i = 0; i < lightSpawns; i++) {
            lights.add(new TrafficLight(Integer.toString(i), roads.get(0), true)); // the traffic light will stand at the end of road_0
            lights.get(i).printLightStatus();
        }
        System.out.println();


        // set locations and connections:
        System.out.println("Settings: \n---------------------");
        road_0.setAConnectedRoadEnd(road_1);// place road_1 to a position at the end of road_0.
        System.out.println();



        //Simulation loop:
        System.out.println("Simulation:");
        Random random = new Random();
        int time = 0;
        System.out.print("Set time scale in milliseconds:");
        int speedOfSim = simController.nextInt();
        int carsFinished = 0;
        while (carsFinished < cars.size()) {
            for (TrafficLight light : lights) {
                light.operate(random.nextInt());
                light.printLightStatus();
            }
            for (Car car : cars) {
                car.move();
                car.printCarStatus();
                if (car.getSpeed()==0 && car.getAssignedSpeed() ==0 ) {
                    carsFinished = carsFinished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("-------------------------------------");
        System.out.println("Now we gonna simulate  a car go from the end of road_1 to the start of road_0");
        cars.get(0).setCurrentRoad(road_1);
        cars.get(0).setCarRoadPosition(road_1.getLength());
        System.out.print("Enter the speed for the car by a positive integer: ");
        int negativeSpeed = simController.nextInt();
        cars.get(0).setSpeed(-negativeSpeed);
        cars.get(0).printCarStatus();
        //Simulation loop:
        System.out.println("Simulation:");
        carsFinished = 0;
        time=0;
        while (carsFinished < cars.size()) {
            for (TrafficLight light : lights) {
                light.operate(random.nextInt());
                light.printLightStatus();
            }
            for (Car car : cars) {
                car.move();
                car.printCarStatus();
                if (cars.get(0).getSpeed() == 0) {
                    carsFinished = carsFinished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }


        System.out.println("-------------------------------");
        System.out.println("Now we gonna stimulate how a car pass over another car on these 2 roads");
        System.out.println("Setting");
        System.out.println("First we change speed limit for 2 road to 2m/s");
        System.out.println("---------------------");
        road_0.setSpeedLimit(2);
        road_1.setSpeedLimit(2);
        for (Road road : roads) {
            road.printRoadInfo();
        }
        System.out.println("---------------------");

        System.out.println("Second we put the first car on road_0, set it position on the start of the road, and set the speed to 2");
        System.out.println();
        cars.get(0).setCurrentRoad(road_0);
        cars.get(0).setSpeed(2);
        cars.get(0).setCarRoadPosition(1);
        cars.get(0).printCarStatus();
        System.out.println("---------------------");

        System.out.println("Then we create new car, set speed as default and position is 2 at road_0:");
        System.out.println();
        cars.add(new Car(Integer.toString(1), roads.get(0)));
        cars.get(1).setCarRoadPosition(2);
        cars.get(1).printCarStatus();
        System.out.println("---------------------");
        carsFinished = 0;
        time=0;
        while (carsFinished < cars.size()) {
            for (TrafficLight light : lights) {
                light.operate(random.nextInt());
                light.printLightStatus();
            }
            for (Car car : cars) {
                car.move();
                car.printCarStatus();
                if (cars.get(0).getSpeed() == 0 && cars.get(1).getSpeed() == 0) {
                    carsFinished = carsFinished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("-------------------------------");
        System.out.println("Now we gonna stimulate how a car pass over another car on these 2 roads if there is another car in opposite direction");
        System.out.println("Setting");
        System.out.println("The first car: ");
        cars.get(0).setCurrentRoad(road_0);
        cars.get(0).setCarRoadPosition(1);
        cars.get(0).setSpeed(2);
        cars.get(0).printCarStatus();
        System.out.println("The second car: ");
        cars.get(1).setCurrentRoad(road_0);
        cars.get(1).setCarRoadPosition(2);
        cars.get(1).setSpeed(1);
        cars.get(1).printCarStatus();
        System.out.println("The third car: ");
        cars.add(new Car(Integer.toString(2), roads.get(0)));
        cars.get(2).setCarRoadPosition(4);
        cars.get(2).setSpeed(-1); //opposite direction
        cars.get(2).printCarStatus();
        System.out.println("On the first second the car_0 have to be at position 2.0, not 3.0");
        System.out.println("---------------------");
        System.out.println("Simulate: ");
        carsFinished = 0;
        time=0;
        while (carsFinished < cars.size()) {
            for (TrafficLight light : lights) {
                light.operate(random.nextInt());
                light.printLightStatus();
            }
            for(Car car: cars){
                car.move();
            }
            for(Road road:roads){
                road.carsOnRoadMoveCheck();
            }
            for(Car car:cars){
                car.printCarStatus();
            }
            if (cars.get(0).getAssignedSpeed() == 0 && cars.get(1).getAssignedSpeed() == 0 && cars.get(2).getAssignedSpeed() == 0) {
                carsFinished = carsFinished + 1;
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(speedOfSim); // set speed of simulation.
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }



    }
}

