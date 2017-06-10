package ch.hslu.vsk.workitem;

import java.util.LinkedList;
import java.util.List;

/**
 * Eine Work Queue, der Aufgaben übergeben werden und deren Bearbeitung
 * abstrahiert wird. Die Schnittstelle processItem muss für eigene
 * Zwecke überschrieben werden. Die Klasse ist thread-sicher.
 */
public abstract class AbstractWorkQueue {

    private final List queue;
    private boolean stopped = false;

    /**
     * Erzeugt die Work Queue.
     */
    public AbstractWorkQueue() {
        queue = new LinkedList();
    }

    /**
     * Aufgabe der Work Queue übergeben.
     * @param workItem Aufgabe.
     */
    public final void enqueue(final Object workItem) {
        synchronized (queue) {
            queue.add(workItem);
        }
    }

    /**
     * Einen Worker nebenläufig starten.
     */
    public void startWorker() {
        new Thread(new Worker()).start();
    }

    /**
     * Worker stoppen.
     */
    public final void stopWorker() {
        synchronized (queue) {
            stopped = true;
        }
        Thread.currentThread().interrupt();
    }

    /**
     * Abstrahierte Bearbeitung eines Work Items.
     * @param workItem Work Item.
     * @throws InterruptedException, wenn das Warten an der Queue unterbrochen wird.
     */
    protected abstract void processItem(final Object workItem)
            throws InterruptedException;

    private class Worker implements Runnable {

        @Override
        public void run() {
            while (true) { // Hauptschleife
                Object workItem;
                synchronized (queue) {
                    try {
                        while (queue.isEmpty() && !stopped) {
                            queue.wait();
                        }
                    } catch (InterruptedException e) {
                        return;
                    }
                    if (stopped) {
                        return;
                    }
                    workItem = queue.remove(0);
                    try {
                        processItem(workItem);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }
    }
}
