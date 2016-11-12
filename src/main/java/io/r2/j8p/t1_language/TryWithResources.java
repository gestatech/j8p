package io.r2.j8p.t1_language;

import java.util.Iterator;

/**
 * Develop code that uses try-with-resources statements,
 * including using classes that implement the AutoCloseable interface
 *
 * @author robymus <r@r2.io>
 */
public class TryWithResources {

    private FibonacciProvider lastProvider = null;

    public FibonacciProvider getFibonacciProvider(int max) {
        return lastProvider = new FibonacciProvider(max);
    }

    public FibonacciProvider getLastProvider() {
        return lastProvider;
    }

    /**
     * Mock iterator / autoclosable class
     */
    public class FibonacciProvider implements Iterator<Integer>, AutoCloseable {

        private boolean closed;
        private int a, b;
        private int max;

        public FibonacciProvider(int max) {
            a = 0;
            b = 1;
            this.max = max;
            closed = false;
        }

        @Override
        public void close() throws Exception {
            closed = true;
        }

        @Override
        public boolean hasNext() {
            return !closed && b <= max;
        }

        @Override
        public Integer next() {
            int n = a+b;
            a = b;
            b = n;
            return a;
        }

        public boolean isClosed() {
            return closed;
        }

    }
}
