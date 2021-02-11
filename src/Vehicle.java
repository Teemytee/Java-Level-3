public abstract class Vehicle {
    protected float tankMaxSize;
    protected float tankCurrentSize;
    protected float fuelsConsuming;
    protected String name;
    protected FuelStation fuelStation;


    public Vehicle(FuelStation fuelStation, String name) {
        this.name = name;
        this.fuelStation = fuelStation;
    }

    public void drive(){
        new Thread(() -> {
            try{
                System.out.println("Начинает поездку: " + name);
                while (tankCurrentSize > 0){
//                    System.out.println(getTankCurrentSize() + " " + name);
                    Thread.sleep(3000);
                    fuelConsume(fuelsConsuming);
                }

                fuelStation.enter(this);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("Топливо кончилось: " + name);
            }
        }).start();
    }

    public float getTankCurrentSize(){
        return tankCurrentSize;
    }

    public void refuelTank(){
        this.tankCurrentSize = tankMaxSize;
    }

    public void fuelConsume(float fuelsConsuming){
        this.tankCurrentSize = getTankCurrentSize() - fuelsConsuming;
    }

    public String getName(){
        return this.name;
    }
}

