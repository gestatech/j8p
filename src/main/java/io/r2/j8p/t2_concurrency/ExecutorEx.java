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

    public void completableFutureOps() throws Exception {
        // basically every method has async/non-async version

        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(()->5);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(()->6);

        // any of 2 completes
        cf1.acceptEither(cf2, (x) -> { /* use x */});

        // got any of the results and applies a method to it
        cf1.applyToEitherAsync(cf2, (x) -> x*2).get();

        // handles result and exception state
        cf1.handle((x, exception) -> x*2).get();

        // runs an action after both (or either of ) cf1/cf2 finishes
        cf1.runAfterBoth(cf2, () -> {});
        cf1.runAfterEitherAsync(cf2, () -> {});

        // accept (consume) result
        cf1.thenAccept(System.out::println);

        // accept 2 results
        cf1.thenAcceptBoth(cf2, (fromCf1, fromCf2) -> {});

        // applies a new function (if no exception)
        cf1.thenApply((x)->x*2).get();

        // combine 2 results
        cf1.thenCombineAsync(cf2, Integer::sum).get();

        // run something at the end
        cf1.thenRunAsync(() -> {});

        // run something when completed (but still can chain more)
        cf1.whenCompleteAsync((result, exception) -> {});

        // getters
        cf1.get(); // wait for result
        cf1.get(10, TimeUnit.SECONDS); // wait with timeout
        cf1.getNow(12 /* default value */);
        cf1.join(); // like get

        // termination methods
        cf1.complete(5);
        cf1.completeExceptionally(new RuntimeException());

        // check status
        cf1.isDone();
        cf1.isCompletedExceptionally();

        // some methods omitted..

    }

}
