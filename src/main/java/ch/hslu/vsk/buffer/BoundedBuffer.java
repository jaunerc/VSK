package ch.hslu.vsk.buffer;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

/**
 * Puffer nach dem First In First Out Prinzip mit einer begrenzten Kapazität. 
 * Der Puffer ist thread sicher.
 */
public final class BoundedBuffer<T> implements Queue<T> {

    private final ConcurrentLinkedQueue<T> queue;
    private final Semaphore putSema;
    private final Semaphore takeSema;

    /**
     * Erzeugt einen Puffer mit bestimmterKapazität.
     *
     * @param n Kapazität des Puffers
     */
    public BoundedBuffer(final int n) {
        queue = new ConcurrentLinkedQueue<T>();
        putSema = new Semaphore(n);
        takeSema = new Semaphore(0);
    }

    /**
     * @see ch.hslu.vsk.buffer.Queue
     */
    @Override
    public void put(final T x) throws InterruptedException {
        putSema.acquire();
        queue.add(x);
        takeSema.release();
    }

    /**
     * @see ch.hslu.vsk.buffer.Queue
     */
    @Override
    public T take() throws InterruptedException {
        takeSema.acquire();
        T temp = queue.remove();
        putSema.release();
        return temp;
    }
}
