package io.r2.j8p.t7_streams;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Perform calculations on Java Streams by using count, max, min, average, and sum methods
 */
public class StreamCalc {

    // calculating and summarizing on generic streams
    public void genericStream() {

        Stream<Integer> s = Stream.generate(() -> 1).limit(50);

        // count just returns the number of elements in the stream (as long)
        long cnt = s.count();

        // min/max finds min/max using a comparator
        Optional<Integer> min = s.min(Comparator.naturalOrder());
        Optional<Integer> max = s.min(Comparator.naturalOrder());

    }

    // primitive streams has additional options for summmarizing
    // Note: these could be implemented for generic streams too using collect/reduce
    // @see StreamCollectReduce
    public void primitiveStream() {
        IntStream s = IntStream.range(0, 1000);

        // min/max/sum
        OptionalInt min = s.min();
        OptionalInt max = s.max();

        // sum is not optional (empty has a sum of 0)
        int sum = s.sum();

        // as is count, except count is long
        long cnt = s.count();

        // average is calculated as a double
        OptionalDouble avg = s.average();

        // if all of the above is important, summaryStatistics can be queried
        IntSummaryStatistics stat = s.summaryStatistics();
        stat.getAverage(); stat.getCount(); stat.getMax(); stat.getMin(); stat.getAverage();

        // these could be combined
        stat.combine(IntStream.range(1000,2000).summaryStatistics());

    }
}
