# Java Based Traffic Simulator
## Program Working Document

### Specification
The client needs a way to be able to simulate traffic in city areas; needing to simulate some different vehicle types 
behavior with traffic lights, roads and intersections. The program will be console-based for this iteration, and will 
be used in the console. Taking inputs from the user and displaying the status of objects within the simulation to the 
user. This version of the program will be able to simulate a single car moving along a single lane road, interact with 
a traffic light, move to a second road connected to the first, representing a very basic intersection, and move to the 
end of that road.Moreover, this prototype also simulate how the car move from the end of the road to the start, simulate
how 2 cars move on the road and interact with the traffic light. The last simulation is the simulation for the situation 
a car can not pass another car because there is a car that is moving in the opposite direction of these two cars in the same 
position.
### Decomposition
The problem can be broken into operate objects that interact with each other to achieve the desired behaviour. 
These objects include;
#### Car
The car class will be an object that describes an average size road vehicle. Holding the following attributes; 
- *id* – a unique identifier that will differentiate each car.
- *Length* – the physical space the car occupies longways.
- *Breadth* – the physical space the car occupies widthways, half the cars length.
- *assignedSpeed* – The speed is assigned from user
- *Speed* – The real speed of the car when it is moving on the road.
- *Position* – where the car is located on a map, using Cartesian Coordinate System.
- *carRoadPosition* – where the car is located on the road.
- *currentRoad* – the road the car is currently traveling on.

The car will be able to move, using the move() method, within the simulation along a road depending on its speed, the speed will be defined by the speed limit of the road the car is traveling on. When the car is in the same position as a traffic light it will check its state before moving, if the light is red it will stop, if the light is green it will move to the next road. When the cars position is equal to the end of a road and there is no connected road it will stop ending the simulation.
A Car cannot pass another car when there is a car that is moving in the opposite direction of these two cars in the same position. 
In this situation, the Car which want to pass have to stay in a bit after the position of the car which will be passed. 
This situation will be checked by carsOnRoadMoveCheck() method of Road class.

The method on the Car class should be public to allow main class can invoke them.
##### Bus
The bus class will be a subclass of car, describing a large road vehicle. It will inherit its attributes and behaviour 
from Car except its length will be defined as being three times that of the car’s length. 

##### Motorbike
The motorbike class will be a subclass of car, describing a small road vehicle. It will inherit its attributes from and 
behaviour Car except its length will be defined as being half that of the car’s length.

#### Road
The road class will be an object that describes a single lane road. Holding the following attributes;
- *id* - a unique identifier that will differentiate each road
- *Speed limit* – the maximum speed that cars on that road may travel at.
-	*Length* – the number of segments the road is comprised of and the physical space it occupies.
-	*Start location* – the (x,y) coordinate that represents where the road begins.
-	*End location* – the (x,y) coordinate that represents where the road ends.
-	*Connected roads* – all of the roads that this road is physically connected to.
-	*Lights on the road* – all the traffic lights that are on the ends this road.
-	*Cars on the road* – all of the cars that are currently traveling on this road.
- *roadMap* - indicate the map object that the road states in.
- *id* - a unique identifier that will differentiate each road.
- *speedLimit* - the maximum speed that cars on that road may travel at.
- *length* - the number of segments the road is comprised of and the physical space it occupies.
- *startLocation* - the (x,y) coordinate that represents where the road begins.
- *endLocation* - the (x,y) coordinate that represents where the road ends.
- *carsOnRoad* - the ArrayList of Car object that present the group of car belonged to this road.
- *startLight* - the TrafficLight object that present the traffic light stands at the start of the road.
- *endLight* - the TrafficLight object that present the traffic light stands at the end of the road.
- *connectedRoadsStart* - the ArrayList of Road object that present the group of roads that connects to this road at the 
  start of this road.
- *connectedRoadsEnd* - the ArrayList of Road object that present the group of roads that connects to this road at the
  end of this road.
- *sameY* - the boolean value that will return tru if the startPosition and the endPosition has the same y-coordinator
  and return false if the startPosition and the endPosition has the same x-coordinator.There are only 2 situations because 
  on this simulator the road can only lay horizontally or vertically.

Road class has method carsOnRoadMoveCheck() to make sure there is no situation that a Car can pass another car when in 
the same position, there is a car that move in the opposite direction. The Road class has some method to interact with 
cars and map like adding a car, removing a car, set this road on a map,get information of the roads, get traffic lights 
on the road, etc.

The method on the Road class should be public to allow main class can invoke them.

The for the first version of the program the speed will be constant and set to 1. Meaning the car will only be able to 
  move a single position each turn making it easier deal with traffic lights and the ends of roads. 
The length of the road will be variable depending of user input. There are only 2 roads connected in this prototype.
Both of them lay horizontally. The first road starts from (0,0) and the second road starts from the end of the first 
road  Roads will interreact with other roads by being connected to them, creating a very basic intersection.

#### Traffic Light
The traffic light class will represent a simple red or green traffic light. Holding the following attributes;
- *id* - a unique identifier that will differentiate each traffic light.
- *State* - the colour the ligth is displaying.
- *Position* - where the traffic light is located on the road.
- *Road attached to* - the road that the light is attached to.

The traffic light will operate, using the opperate() method. Randomly changing from green to red. 
This opperation will be based on pseudo-random numbers generated by the program. 
The traffic light can only stop the car on the road it stands at.
The light will be placed on a road only at its final position, the end of the road, and will interact with cars that 
are also at that position. If the light is red the cars will stop and not move to the next road. If the light is green 
the cars will continue past and move to the next road.

The method on the Road class should be public to allow main class can invoke them.
#### Map
The Map class will represent a map that includes all roads for simulattion;
- *mapId* - a unique identifier that will differentiate each Map.
- *roadsOnMap * - the ArraysList of Roads that contains all road objects for a map.
- *carsOnMap* - the ArraysList of Roads that contains all car objects for a map.

On this prototype, a map will be initialized to contain 2 connected roads. The map help prototype link the road by 
the method connectRoadsOnMap(). The moveCarsOnMap() method of the map will invoke the move() method of all car object 
on the map. The Map class have some methods to interact with cars and roads like set and get roads and cars, remove
a road out of the map, etc. 

The method on the Road class should be public to allow main class can invoke them.
### Main
This class will have the main() method that will contain the simulation loop.
All the objects needed for the simulation will be created here; creating roads, a car and a traffic light. 
Locations for roads and their connection will also be set here. For this version of the program the user 
will only have control over the lengh of the road, and the speed the simulation runs at. The simulation will run, the 
car first will move from the start of the first road to the end od the second road. Then we place the car at the end of 
second road and move the car to the start of the  first road. The third simulation will create another car from Car class
that has the slower speed than the first car and simulate how the first car pass the second car. The last simulation will
simulate the situation where the first car cannot pass the second car because there is a car moving on the opposite 
direction at the same position.
