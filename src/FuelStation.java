import java.util.concurrent.Semaphore;

public class FuelStation {
    private final Semaphore semaphore = new Semaphore(3);

    public void enter(Vehicle vehicle){
        new Thread(() -> {
            try {
                System.out.println("На заправку попала машина: " + vehicle.getName());
                semaphore.acquire();
                System.out.println("Количество свободных колонок: " + semaphore.availablePermits());
                System.out.println("Заправляется машина: " + vehicle.getName());
                vehicle.refuelTank();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                vehicle.drive();
            }
        }).start();

    }

}
