package io.r2.j8p.t2_concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;

/**
 * Synchronizers in java.util.concurrent
 *
 * @author robymus <r@r2.io>
 */
public class Synchronizers {


    /**
     * Semaphore example
     * @return order of operations, should be 1i, 1o, 2i, 2o
     * @throws InterruptedException
     */
    public List<String> syncSemaphore() throws InterruptedException {
        Semaphore s = new Semaphore(1,true);
        ArrayList<String> order = new ArrayList<>();
        Worker w1 = new Worker( () -> {
            s.acquire();
            order.add("1i");
            Thread.sleep(200);
            s.release();
            order.add("1o");
        });
        Worker w2 = new Worker( () -> {
            Thread.sleep(100);
            s.acquire();
            order.add("2i");
            Thread.sleep(200);
            s.release();
            order.add("2o");
        });
        w1.start();
        w2.start();
        w1.join();
        w2.join();
        return order;
    }

    /**
     * CountdownLatch example
     * @return should be true, as both workers report finished state
     * @throws InterruptedException
     */
    public boolean syncCountdownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList<String> order = new ArrayList<>();
        Worker w1 = new Worker( () -> {
            order.add("w1");
            countDownLatch.countDown();
        });
        Worker w2 = new Worker( () -> {
            order.add("w2");
            countDownLatch.countDown();
        });
        w1.start();
        w2.start();
        return countDownLatch.await(200, TimeUnit.MILLISECONDS);
    }

    /**
     * CyclicBarrier example
     * @return order of operations, should be 1-1, 2-1, 1-2, 2-2
     * @throws InterruptedException
     */
    public List<String> syncCyclicBarrier() throws InterruptedException, BrokenBarrierException {
        ArrayList<String> order = new ArrayList<>();
        String[] state1 = new String[1];
        String[] state2 = new String[1];
        CyclicBarrier barrier = new CyclicBarrier(3, ()->{
            order.add(state1[0]);
            order.add(state2[0]);
        }); // 3 threads: 2 workers + this
        Worker w1 = new Worker( () -> {
            state1[0] = "1-1";
            barrier.await();
            state1[0] = "1-2";
            barrier.await();
        });
        Worker w2 = new Worker( () -> {
            state2[0] = "2-1";
            barrier.await();
            state2[0] = "2-2";
            barrier.await();
        });
        w1.start();
        w2.start();
        // wait 2 cycle in main thread
        barrier.await();
        barrier.await();
        // barrier action was run before releasing thread from await
        return order;
    }

    /**
     * Exchanger example
     * @return returns variable state after exchange, should be W2, W1
     * @throws InterruptedException
     */
    public String[] syncExchanger() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        String[] val = new String[2];
        Worker w1 = new Worker( () -> {
            String s = "W1";
            s = exchanger.exchange(s);
            val[0] = s;
        });
        Worker w2 = new Worker( () -> {
            String s = "W2";
            s = exchanger.exchange(s);
            val[1] = s;
        });
        w1.start();
        w2.start();
        w1.join();
        w2.join();
        return val;
    }

    /**
     * CyclicBarrier example
     * @return order of operations, should be 1-1, 2-1, 3-1, 2-2, 3-2, 3-3, FIN
     * @throws InterruptedException
     */
    public List<String> syncPhaser() throws InterruptedException, BrokenBarrierException {
        ArrayList<String> order = new ArrayList<>();
        String[] state1 = new String[1];
        String[] state2 = new String[1];
        String[] state3 = new String[1];
        Phaser phaser = new Phaser(4); // start with 4 threads
        Worker w1 = new Worker( () -> {
            // w1 only has a single phase
            state1[0] = "1-1";
            phaser.arriveAndDeregister();
        });
        Worker w2 = new Worker( () -> {
            // w2 has 2 phases
            state2[0] = "2-1";
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance(); // extra phase: copying
            state2[0] = "2-2";
            phaser.arriveAndDeregister();
        });
        Worker w3 = new Worker( () -> {
            // w3 has 3 phases
            state3[0] = "3-1";
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance(); // extra phase: copying
            state3[0] = "3-2";
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance(); // extra phase: copying
            state3[0] = "3-3";
            phaser.arriveAndDeregister();
        });
        w1.start();
        w2.start();
        w3.start();
        // wait 3 cycles in main thread
        phaser.arriveAndAwaitAdvance();
        order.add(state1[0]); order.add(state2[0]); order.add(state3[0]);
        phaser.arriveAndAwaitAdvance(); // continue running
        phaser.arriveAndAwaitAdvance(); // stop for copying
        order.add(state2[0]); order.add(state3[0]);
        phaser.arriveAndAwaitAdvance(); // continue running
        phaser.arriveAndAwaitAdvance();
        order.add(state3[0]);
        phaser.arriveAndDeregister();
        if (phaser.isTerminated()) order.add("FIN");
        return order;
    }


    @FunctionalInterface
    public interface Action {
        void run() throws InterruptedException, BrokenBarrierException;
    }
    
    public class Worker extends Thread {

        private Action action;

        public Worker(Action action) {
            this.action = action;
        }

        @Override
        public void run() {
            try {
                action.run();
            }
            catch (InterruptedException|BrokenBarrierException e) {}
        }
    }
}
