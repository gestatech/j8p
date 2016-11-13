package io.r2.j8p.t2_concurrency;

import java.util.concurrent.*;

/**
 * Use Executor, ExecutorService, Executors, Callable, and Future to execute tasks using thread pools
 *
 * @author robymus <r@r2.io>
 */
public class ExecutorEx {

    private ExecutorService ex;
    private ScheduledExecutorService s_ex;

    public ExecutorEx() {
        ex = Executors.newCachedThreadPool();
        // or other types of executor service
        // ex = Executors.newFixedThreadPool(4);
        // ex = Executors.newSingleThreadExecutor();

        s_ex = Executors.newScheduledThreadPool(2);
    }

    public int parallelAdd_class(int a, int b) throws ExecutionException, InterruptedException {
        Future<Integer> sum = ex.submit(new Adder(a, b));
        return sum.get();
    }

    public class Adder implements Callable<Integer> {

        private int x, y;

        public Adder(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public Integer call() throws Exception {
            return x+y;
        }
    }

    public int parallelAdd_lambda(int a, int b, int c, int d) throws ExecutionException, InterruptedException {
        Future<Integer> ab = ex.submit(()->a+b);
        Future<Integer> cd = ex.submit(()->c+d);
        Future<Integer> sum = ex.submit(()->ab.get()+cd.get());
        return sum.get();
    }

    public int completableFutureAdd(int a, int b, int c, int d) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> ab = CompletableFuture.supplyAsync(() -> a+b);
        CompletableFuture<Integer> cd = CompletableFuture.supplyAsync(() -> c+d);
        return ab.thenCombineAsync(cd, Integer::sum).get();
    }

}
