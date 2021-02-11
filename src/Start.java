public class Start {
    public void doStart(FuelStation fuelStation){
        Cars(fuelStation);
        Trucks(fuelStation);
        Buses(fuelStation);

    }

    private void Cars(FuelStation fuelStation){
        Car carOne = new Car(fuelStation, "автомобиль Мазда");
        Car carTwo = new Car(fuelStation, "автомобиль БМВ");
        Car carThree = new Car(fuelStation, "автомобиль Ниссан");
        Car[] cars = {carOne, carTwo, carThree};
        for (Car car: cars) {
            car.drive();
        }
    }

    private void Trucks(FuelStation fuelStation){
        Truck truckOne = new Truck(fuelStation, "грузовик КАМАЗ");
        Truck truckTwo = new Truck(fuelStation, "грузовик ГАЗ");
        Truck truckThree = new Truck(fuelStation, "грузовик Мерседес");
        Truck truckFour = new Truck(fuelStation, "грузовик Вольво");
        Truck[] trucks = {truckOne, truckTwo, truckThree, truckFour};
        for (Truck truck: trucks) {
            truck.drive();
        }
    }

    private void Buses(FuelStation fuelStation){
        Bus busOne = new Bus(fuelStation, "автобус Мерседес");
        Bus busTwo = new Bus(fuelStation, "автобус ГАЗ");
        Bus busThree = new Bus(fuelStation, "автобус Вольво");
        Bus[] buses = {busOne, busTwo, busThree};
        for (Bus bus: buses) {
            bus.drive();
        }
    }
}
