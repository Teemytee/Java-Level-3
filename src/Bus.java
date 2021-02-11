public class Bus extends Vehicle {
    Bus(FuelStation fuelStation, String name){
        super(fuelStation, name);
        super.tankMaxSize = 40;
        super.tankCurrentSize = 40;
        super.fuelsConsuming = 7.5f;
        super.name = name;
    }
}