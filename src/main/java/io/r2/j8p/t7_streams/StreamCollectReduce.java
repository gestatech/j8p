package io.r2.j8p.t7_streams;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Develop code that uses parallel streams, including decomposition operation and reduction operation in streams
 */
public class StreamCollectReduce {

    Stream<Integer> s = Stream.empty();

    //
    // collect is mutable reduction on the stream
    // it creates a mutable container (for each partition), accumulates data into it, and combines partitions
    // these 3 functions can be packed into a Collector class (the Collectors class creates these instances),
    // but may also be individually specified
    //
    void collect() {
        ArrayList<Integer> asList = s.collect(
                ArrayList::new,     // the supplier creates new mutable containers
                ArrayList::add,     // a biconsumer (parameters: mutable container, element) stores the new element
                ArrayList::addAll   // and a combiner combines two mutable containers (adds second to the first)
        );

        // Note: this collect operation can be used on primitive streams, while Collectors can't b used
    }

    //
    // Reduce provides a reduction without a mutable container on the stream
    //
    void reduce() {

        // if the accumulation function is associative, a BinaryOperator is sufficient to define the operation
        // (this will be used when combining results from partitions)

        // first version uses only the accumulator function and returns an optional, in case the stream was empty
        Optional<Integer> sum1 = s.reduce(Integer::sum);

        // second version uses an identity value, so the result is not optional
        Integer prod1 = s.reduce(1, (x, y) -> x*y);

        // the associative version of reduce is available for primitive streams as well (but only the associative version)
        Long factorial100 = LongStream.rangeClosed(1, 100).reduce(1, (x, y) -> x*y);

        // if the accumulation function is not associative, the setup is a little different
        // the result (U) might not be the same as the input (T)
        // the accumulator function is UxT->U (update U by T)
        // the combiner function is UxU->U
        Integer squareSum = s.reduce(
                0,                  // start with 0
                (a, x) -> a+x*x,    // add number squared
                Integer::sum        // combine with addition
        );
    }
}
