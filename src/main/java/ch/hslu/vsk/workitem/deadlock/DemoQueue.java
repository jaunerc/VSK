package ch.hslu.vsk.workitem.deadlock;

/**
 * Demonstration der Queue, die einen Deadlock verursacht.
 */
public final class DemoQueue {

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String args[]) throws InterruptedException {
        final DeadlockQueue deadlockQueue = new DeadlockQueue();
        deadlockQueue.startWorker();
        for (int i = 1; i <= 5; i++) {
            deadlockQueue.enqueue("Schritt " + i);
        }
        Thread.sleep(8000);
        deadlockQueue.stopWorker();
    }
}
