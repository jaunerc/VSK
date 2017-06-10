package ch.hslu.vsk.workitem.display;

/**
 * Demonstration der Display Queue.
 */
public final class DemoQueue {

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String args[]) throws InterruptedException {
        final DisplayQueue displayQueue = new DisplayQueue();
        displayQueue.startWorker();
        for (int i = 1; i <= 5; i++) {
            displayQueue.enqueue("Schritt " + i);
        }
        Thread.sleep(5000);
        displayQueue.stopWorker();
    }
}
