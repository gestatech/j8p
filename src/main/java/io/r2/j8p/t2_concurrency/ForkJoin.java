package io.r2.j8p.t2_concurrency;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * Use the parallel Fork/Join Framework
 *
 * @author robymus <r@r2.io>
 */
public class ForkJoin {

    private ForkJoinPool pool;

    private int[] data;

    public int sum;

    public ForkJoin() {
        pool = new ForkJoinPool(4); // optional parallelism level
        // pool = ForkJoinPool.commonPool();
        // or just use common pool (invoke/execute methods on a ForkJoinTask will use this automatically)
        // pseudo random data
        data = new int[100000];
        data[0] = 1;
        for (int i = 1; i < data.length; i++) data[i] = (data[i-1]*37+31381)%1_000_000;
        sum = Arrays.stream(data).sum();
    }

    /**
     * Sum using divide and conquer fork/join
     */
    public int divconqSum() {
        // invoke is fork + join
        return pool.invoke(new DivConqSumTask(data, 0, data.length));
    }

    /**
     * Use RecursiveTask when returning a value -> simple ForkJoinTask subclass
     * RecursiveAction when no return value
     */
    public class DivConqSumTask extends RecursiveTask<Integer>
    {
        final int[] data;
        final int lo;
        final int hi;

        public DivConqSumTask(int[] data, int lo, int hi) {
            this.data = data;
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected Integer compute() {
            if (hi - lo > 1000) {
                int mid = (lo+hi)/2;
                DivConqSumTask left = new DivConqSumTask(data, lo, mid);
                DivConqSumTask right = new DivConqSumTask(data, mid, hi);
                left.fork(); right.fork();
                return left.join() +  right.join();
            }
            else {
                return Arrays.stream(data, lo, hi).sum();
            }
        }
    }

}
