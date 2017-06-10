package ch.hslu.vsk.buffer.forum;

/**
 * Produzent, der eine maximale Anzahl Werte produziert und 
 * diese in die SimpleQueue speichert.
 */
public final class Producer implements Runnable {

    private final SimpleQueue queue;
    private final int maxRange;
    private volatile Thread runThread;

    /**
     * Erzeugt einen Produzent, der eine bestimmte Anzahl Integer-Werte produziert.
     * @param queue Queue zum Speichern der Integer-Werte.
     * @param max Anzahl Integer-Werte.
     */
    public Producer(final SimpleQueue queue, final int max) {
        this.queue = queue;
        this.maxRange = max;
    }

    @Override
    public void run() {
        runThread = Thread.currentThread();
        for (int i = 0; i < maxRange; i++) {
            try {
                queue.put(i);
                System.out.println(runThread.getName() + " Put: " + i);
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }
        System.exit(0);
    }
}
