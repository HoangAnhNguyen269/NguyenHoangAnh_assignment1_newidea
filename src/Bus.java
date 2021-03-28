public class Bus extends Car {

    public Bus(String id, Road currentRoad) {
        super(id, currentRoad);
        this.id = ("bus_" + id);
        length = super.getLength() * 3;//len(bus) = 3 * len(car)
    }
}