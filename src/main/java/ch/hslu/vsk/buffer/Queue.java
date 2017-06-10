package ch.hslu.vsk.buffer;

/**
 * A collection designed for holding elements prior to processing.
 * Queues typically, but do not necessarily, order elements in a 
 * FIFO (first-in-first-out) manner.
 * @param <T>
 */
public interface Queue<T> {

    /**
     * Inserts the specified element into this queue if it is possible. 
     * If not, the writer has to wait.
     * @param x element to insert
     * @throws InterruptedException if waiting was interrupted
     */
    public void put(T x) throws InterruptedException;

    /**
     * Retrieves and removes the head of this queue, 
     * or wait if this queue is empty.
     * @return element
     * @throws InterruptedException if waiting was interrupted
     */
    public T take() throws InterruptedException;
}
