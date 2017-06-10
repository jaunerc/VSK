package ch.hslu.vsk.buffer;

import java.util.Random;

/**
 * Produzent, der eine maximale Anzahl Werte produziert und 
 * diese in eine Queue speichert.
 */
public final class Producer implements Runnable {

    private final Queue<Integer> queue;
    private final int max;
    private long sum;
    private static final Random rand = new Random();

    /**
     * Erzeugt einen Produzent, der eine bestimmte Anzahl Integer-Werte produziert.
     * @param queue Queue zum Speichern der Integer-Werte.
     * @param max Anzahl Integer-Werte.
     */
    public Producer(final Queue<Integer> queue, final int max) {
        this.queue = queue;
        this.max = max;
        this.sum = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < max; i++) {
            try {
                int value = rand.nextInt(max);
                sum += value;
                queue.put(value);
            } catch (InterruptedException ex) {
                return;
            }
        }
        System.out.println(Thread.currentThread().getName() + " sum: \t" + sum);
    }

    /**
     * Liefert die Summe aller gespeicherter Werte.
     * @return Summe.
     */
    public long getSum() {
        return sum;
    }
}
