package ch.hslu.vsk.executor;

/**
 * An object that executes submitted Runnable tasks.
 */
public interface Executor {

    /**
     * Executes the given command at some time in the future. 
     * The command may execute in a new thread, in a pooled thread, 
     * or in the calling thread, at the discretion of the Executor implementation.
     * @param task the runnable task
     */
    void execute(Runnable task);
}
