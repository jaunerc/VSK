package ch.hslu.vsk.sema;

/**
 * Demo DemoAddition und Ausgabe mit Semaphore.
 */
public final class DemoAddition {

    private static final Semaphore sema = new Semaphore();
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
    public static void main(final String args[]) {
        // Thread addiert...
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10000; i++) {
                    sum += i;
                }
                sema.release();
            }
        });
        t1.start();
        // Thread wartet auf Summe...
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("wait for result...");
                try {
                    sema.acquire();
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.println("sum = " + sum);
            }
        });
        t2.start();
    }
}
