package io.r2.j8p.t7_streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Develop  code that uses the merge(), flatMap(), and map() methods on Java Streams
 */
public class StreamMap {

    // 1:1 mapping of stream elements is performed by map
    public void usingMap() {
        Stream<Integer> s = Stream.generate(() -> 1).limit(50);

        // map can map to the same or different stream type
        s.map((x) -> 2*x);
        Stream<String> ss = s.map(String::valueOf);

        // also possible to map to primitive streams
        IntStream is = s.mapToInt(Integer::intValue);

        // primitive streams can be converted to boxed
        is.boxed();

        // casted to other type
        is.asLongStream();
        is.asDoubleStream();

        // mapped to other types
        is.mapToDouble((x) -> 0.5*x);
        is.mapToObj(String::valueOf);
    }


    // for 1:n mapping, flatmap has to be used
    // the mapping function has to return a new stream, and each returned stream will be "flattened", eg.
    // concatenated into the output stream
    public void usingFlatMap() {

        Stream<Integer> s = Stream.generate(() -> 1).limit(50);

        s.flatMap((x) -> Stream.generate(()->x).limit(x));

        // flatmap can change into primitive stream
        IntStream is = s.flatMapToInt((x) -> IntStream.range(1, x));

        // but primitive can't flatmap back to object
        is.flatMap((x) -> IntStream.of(1, x));


    }
}
