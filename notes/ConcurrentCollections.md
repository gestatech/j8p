# Concurrent collections
The java.util.concurrent package defines several concurrent collection classes. These classes are engineered for high performance concurrent operation, but these are not the same as the synchronized versions.

The synchronized versions guarantee that all reads will return the same, while the concurrent versions only guarantee reads (and especially iterators)will return a consistent state, but not necessarily the latest one, as due to congestion, it might become available to readers only after a slight delay.

- *ArrayBlockingQueue* - Fixed size blocking queue
- *ConcurrentHashMap* - HashMap
- *ConcurrentLinkedDeque*, *ConcurrentLinkedQueue* - Queues
- *ConcurrentSkipListMap*, *ConcurrentSkipListSet* - Navigable (sorted, traversable) map and set
- *CopyOnWriteArrayList*, *CopyOnWriteArraySet* - Array/set duplicated on write, usable for high read volume and low write volume
- *DelayQueue* - a queue of Delayed elements
- *LinkedBlockingDeque*, *LinkedBlockingQueue*, *LinkedTransferQueue*, *PriorityBlockingQueue*, *SynchronousQueue* - Various queues for synchronizing threads