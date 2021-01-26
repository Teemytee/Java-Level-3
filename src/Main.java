public class Main {
    public static void main(String[] args) throws Exception {
        SharedObject shared = new SharedObject();
        shared.current = 'A';
        Thread t1 = new Thread(new MyRunnable('A', shared));
        Thread t2 = new Thread(new MyRunnable('B', shared));
        Thread t3 = new Thread(new MyRunnable('C', shared));
        t1.start();
        t2.start();
        t3.start();
    }
}


