public class Car extends Vehicle {
    Car(FuelStation fuelStation, String name){
        super(fuelStation, name);
        super.tankMaxSize = 20;
        super.tankCurrentSize = 20;
        super.fuelsConsuming = 2.5f;
        super.name = name;
    }
}