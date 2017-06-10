package ch.hslu.vsk.lostsignals;

/**
 * Demo Lost Signals. Eine Addition gesteuert mit wait - notify.
 */
public final class DemoAddition {

    private static final Object lock = new Object();
    private static long sum = 0;

    /**
     * Privater Konstruktor.
     */
    private DemoAddition() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(String args[]) {
        // Thread addiert...
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10000; i++) {
                    sum += i;
                }
                System.out.println("calc finished, notifing...");
                synchronized (lock) {
                    lock.notify();
                }
            }
        });
        t1.start();
        // Thread wartet auf Summe...
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("wait for result...");
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                System.out.println("sum = " + sum);
            }
        });
        t2.start();
    }
}
