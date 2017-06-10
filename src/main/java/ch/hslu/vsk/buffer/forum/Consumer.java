package ch.hslu.vsk.buffer.forum;

/**
 * Konsument, der soviele Werte aus der SimpleQueue liest, wie er ur kann.
 */
public final class Consumer implements Runnable {

    private final SimpleQueue queue;
    private final boolean[] check;
    private volatile Thread runThread;

    /**
     * Erzeugt einen Konsumenten, der soviel Integer-Werte ausliest, wie er nur kann.
     * @param queue Queue zum Lesen der Integer-Werte.
     * @param check Array zum Prüfen auf doppelt ausgelesene Werte.
     */
    public Consumer(final SimpleQueue queue, final boolean[] check) {
        this.queue = queue;
        this.check = check;
    }

    @Override
    public void run() {
        runThread = Thread.currentThread();
        int value;
        while (true) {
            try {
                value = queue.get();
                System.out.println(runThread.getName() + " Got: " + value);
                synchronized (queue) {
                    if (!check[value]) {
                        check[value] = true;
                    } else {
                        System.err.println("double value: " + value);
                    }
                }
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
