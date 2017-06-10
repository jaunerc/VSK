package ch.hslu.vsk.buffer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Demonstration eines BoundedBuffers mit n Producer und m Consumer.
 */
public final class DemoQueue {

    /**
     * Main-Demo.
     * @param args not used.
     */
    public static void main(final String args[]) throws InterruptedException {
        final Random random = new Random();
        Queue<Integer> q = new BoundedBuffer<Integer>(200);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        Consumer c3 = new Consumer(q);
        Consumer c4 = new Consumer(q);
        Producer p1 = new Producer(q, random.nextInt(1000000));
        Producer p2 = new Producer(q, random.nextInt(1000000));
        ThreadGroup tgCons = new ThreadGroup("Consumers");
        ThreadGroup tgProd = new ThreadGroup("Producers");
        new Thread(tgCons,c1, "Cons A").start();
        new Thread(tgCons,c2, "Cons B").start();
        new Thread(tgCons,c3, "Cons C").start();
        new Thread(tgCons,c4, "Cons D").start();
        new Thread(tgProd,p1, "Prod 1").start();
        new Thread(tgProd,p2, "Prod 2").start();

        while (tgProd.activeCount() > 0) {
            Thread.yield();
        }
        // Warte kurz , dann wird der Verbraucher gestoppt
        TimeUnit.MILLISECONDS.sleep(200);
        tgCons.interrupt();
        System.out.println("Checksum");
        System.out.println("All producers = " + (p1.getSum() + p2.getSum()));
        System.out.println("All consumers = " + (c1.getSum() + c2.getSum() + c3.getSum() + c4.getSum()));
    }
}
