package ch.hslu.vsk.workitem.display;

import ch.hslu.vsk.workitem.AbstractWorkQueue;

/**
 * Anzeige von Work Items.
 */
public final class DisplayQueue extends AbstractWorkQueue {

    /**
     * Anzeige eines Work Items für eine Sekunde.
     * @param workItem Work Item.
     * @throws InterruptedException wird hier nicht passieren.
     */
    @Override
    protected void processItem(final Object workItem)
            throws InterruptedException {
        System.out.println(workItem);
        Thread.sleep(1000);
    }
}
