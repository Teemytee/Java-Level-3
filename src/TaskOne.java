class MyRunnable implements Runnable {
    private final char symbol;
    private final SharedObject shared;

    MyRunnable(char symbol, SharedObject shared) {
        this.symbol = symbol;
        this.shared = shared;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (shared) {
                while (shared.current != symbol) {
                    try {
                        shared.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(symbol);
                switch (symbol) {
                    case 'A': shared.current = 'B'; break;
                    case 'B': shared.current = 'C'; break;
                    case 'C': shared.current = 'A'; break;
                }
                shared.notifyAll();
            }
        }
    }
}