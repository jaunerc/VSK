package ch.hslu.vsk.buffer;

/**
 * Konsument, der soviele Werte aus einer Queue liest, wie er nur kann.
 */
public final class Consumer implements Runnable {

    private final Queue<Integer> queue;
    private long sum;

    /**
     * Erzeugt einen Konsumenten, der soviel Integer-Werte ausliest, wie er nur kann.
     * @param queue Queue zum Lesen der Integer-Werte.
     */
    public Consumer(final Queue<Integer> queue) {
        this.queue = queue;
        this.sum = 0;
    }

    @Override
    public void run() {
        boolean finished = false;
        while (!finished) {
            try {
                sum += queue.take();
            } catch (InterruptedException ex) {
                finished = true;
            }
        }
        System.out.println(Thread.currentThread().getName() + " sum: \t" + sum);
    }

    /**
     * Liefert die Summe aller ausgelesener Werte.
     * @return Summe.
     */
    public long getSum() {
        return sum;
    }
}
