package ch.hslu.vsk.buffer.forum;

/**
 * Demonstration der SimpleQueue mit einem Producer und n Consumer.
 */
public final class DemoSimpleQueue {

    /**
     * Privater Konstruktor.
     */
    private DemoSimpleQueue() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String args[]) {
        final int n = 1000;
        final int nCons = Integer.parseInt(args[0]);
        final boolean[] check = new boolean[n];
        final SimpleQueue queue = new SimpleQueue();
        new Thread(new Producer(queue, n), "Prod  ").start();
        for (int i = 0; i < nCons; i++) {
            new Thread(new Consumer(queue, check), "Cons "+(char)(i+65)).start();
        }
    }
}
