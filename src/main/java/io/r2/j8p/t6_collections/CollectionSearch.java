package io.r2.j8p.t6_collections;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Search for data by using methods, such as findFirst(), findAny(), anyMatch(), allMatch(), and noneMatch()
 */
public class CollectionSearch {

    List<Integer> data;

    CollectionSearch() {
        data = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
    }


    void findValue() {
        // findFirst and findAny operates on streams, which may be generated from a collection
        // findFirst requires strict ordering, findAny might return any match
        // this makes findAny faster in parallel environment

        Optional<Integer> first = data.stream().filter((x) -> x > 10).findFirst();

        Optional<Integer> any = data.parallelStream().filter((x) -> x > 10).findAny();

        // the result is Optional, as the collection (or filtered version) might be empty
    }

    void checkMatch() {
        // a stream can be matched to a filter with the following methods
        // anyMatch: true, if at least 1 is matched (and lazily terminates there)
        // allMatch: true, if all items metch (lazily terminates at first mismatch)
        // noneMatch: true, of no items match (lazily terminates at first match)

        data.stream().anyMatch((x) -> x > 10);
        data.stream().allMatch((x) -> x <= 20);
        data.stream().noneMatch((x) -> x < 0);
    }

}
