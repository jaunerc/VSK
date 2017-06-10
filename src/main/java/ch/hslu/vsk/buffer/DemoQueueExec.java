/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.vsk.buffer;

import ch.hslu.vsk.executor.PlainWorkerPool;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Demonstration eines BoundedBuffers mit n Producer und m Consumer und einem Executor.
 */
public final class DemoQueueExec {

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
        // Executor
        PlainWorkerPool pwp = new PlainWorkerPool(new BoundedBuffer<Runnable>(10), 10);
        pwp.execute(c1);
        pwp.execute(c2);
        pwp.execute(c3);
        pwp.execute(p1);
        pwp.execute(p2);
        // Warte kurz...
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("Checksum");
        System.out.println("Prod = " + (p1.getSum() + p2.getSum()));
        System.out.println("Cons = " + (c1.getSum() + c2.getSum() + c3.getSum() + c4.getSum()));
    }
}
