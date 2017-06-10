package ch.hslu.vsk.executor;

import ch.hslu.vsk.buffer.Queue;

/**
 * Ein einfacher Executor, der Aufgaben an eine bestimmte Anzahl Worker zuteilt. 
 * Die Aufgaben werden in einer Queue gespeichert.
 */
public final class PlainWorkerPool implements Executor {

    private final Queue<Runnable> workQueue;
    private int nWorkers;
    private boolean stopped;

    /**
     * Erzeugt einen einfacher Executor.
     * @param workQueue Queue für die Aufgaben.
     * @param nWorkers Anzahl Worker.
     */
    public PlainWorkerPool(final Queue<Runnable> workQueue, final int nWorkers) {
        this.workQueue = workQueue;
        this.nWorkers = nWorkers;
        this.stopped = false;
        for (int i = 0; i < nWorkers; ++i) {
            activate();
        }
    }

    @Override
    public void execute(Runnable task) {
        try {
            workQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void activate() {
        Runnable runLoop = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Runnable r;
                        r = workQueue.take();
                        r.run();
                    }
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        };
        final Thread thread = new Thread(runLoop);
        thread.setDaemon(true);
        thread.start();
    }
}
