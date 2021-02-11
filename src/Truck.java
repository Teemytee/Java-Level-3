public class Truck extends Vehicle {
    Truck(FuelStation fuelStation, String name){
        super(fuelStation, name);
        super.tankMaxSize = 60;
        super.tankCurrentSize = 60;
        super.fuelsConsuming = 15f;
        super.name = name;
    }
}
