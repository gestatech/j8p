package io.r2.j8p.t2_concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.locks.*;

/**
 * Use Lock, ReadWriteLock, and ReentrantLock classes in the java.util.concurrent.locks and
 * java.util.concurrent.atomic packages to support lock-free thread-safe programming on single variables
 *
 * @author robymus <r@r2.io>
 */
public class LockAtomic {

    public void lockExample() throws InterruptedException {
        Lock lock = new ReentrantLock();

        // this is a straightforward replacement to synchronized blocks (similar to a semaphore)
        lock.lock();
        // ...
        lock.unlock();

        if (!lock.tryLock(5, TimeUnit.SECONDS)) {
            System.out.println("Can't acquire lock in 5 seconds");
        }

        ReadWriteLock rwlock = new ReentrantReadWriteLock();
        // read write lock is just a pair of locks, with r/w semantics
        rwlock.readLock().lock();

        rwlock.readLock().unlock();

        rwlock.writeLock().lock();
        rwlock.writeLock().unlock();

        // object notify/wait is packaged as conditions of a lock

        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition(); // can have any number of conditions

        c1.await();
        c2.signalAll(); // from other thread
    }

    public void atomicLongExample() {
        AtomicLong a = new AtomicLong(0);
        long val = a.get();

        val = a.addAndGet(5);
        val = a.getAndIncrement(); // get can be before and after any operation

        boolean ok = a.compareAndSet(6, 12);

        // the really interesting one with an operator accumulating a new value
        val = a.accumulateAndGet(3, (x,y) -> x*y);

        // or just updating
        val = a.updateAndGet((x) -> x*x);

        AtomicLongArray aa = new AtomicLongArray(2);
        // array actually works the same, with first argument being an index

        val = aa.addAndGet(0, 5);
    }

    public void atomicDoubleAccumulator() {
        // can be LongAccumulator as well
        // *Adder is a special accumulator with ::add

        // accumulator function / initial value
        DoubleAccumulator acc = new DoubleAccumulator((x,y) -> 0.9*x+0.1*y, 0);
        acc.accumulate(72.3);
        double d = acc.doubleValue();
    }

}
