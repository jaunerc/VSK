package ch.hslu.vsk.workitem.deadlock;

import ch.hslu.vsk.workitem.AbstractWorkQueue;

/**
 * Anzeige von Work Items.
 */
public final class DeadlockQueue extends AbstractWorkQueue {

    /**
     * Anzeige eines Work Items alle Sekunden und dann das Work Item in die
     * WorkQueue zurücklegen. Falls die WorkQueue voll ist - warten.
     *
     * @param workItem Work Item.
     * @throws InterruptedException, wenn das Warten unterbrochen wird.
     */
    @Override
    protected void processItem(final Object workItem)
            throws InterruptedException {
        System.out.println(workItem);
        Thread.sleep(1000);
        // Erzeuge einen neuen Thread, der workItem 
        // in die Queue zurücklegt
        final Thread child = new Thread() {
            @Override
            public void run() {
                enqueue(workItem);
            }
        };
        child.start();
        child.join(); // Deadlock!
        System.out.println(workItem + " returned");
    }
}
