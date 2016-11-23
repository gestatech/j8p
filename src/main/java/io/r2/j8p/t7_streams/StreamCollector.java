package io.r2.j8p.t7_streams;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Save results (eg. from stream processing) to a collection by using the collect method and Collector class, including the averagingDouble, groupingBy, joining, partitioningBy methods
 */
public class StreamCollector {

    // sample data type
    public class Data {
        public String getType() { return "type"; }
        public String getName() { return "name"; }
        public double getPrice() { return 0.5; }
        public int getHeight() { return 84; }
    }

    Stream<Data> s = Stream.empty();


    //
    // a collector is passed to the collect mutating reduction operator of the stream
    // it will somehow produce a summarized/final result from the stream
    //
    public void collectToCollection() {

        // generic syntax when using a collector from Collectors

        // collect to a list
        List<Data> list = s.collect(Collectors.toList());

        // collect to a different collection
        HashSet<Data> set = s.collect(Collectors.toCollection(HashSet::new));

        // converting to map or concurrent map can become tricky (.toMap, toConcurrentMap)
        // normally convert to key and value separately
        Map<String, Double> namePrice = s.collect(
                Collectors.toMap(Data::getName, Data::getPrice)
        );

        // optional parameter is a merge function, which will merge same values with same key
        Map<String, Double> typeSumPrice = s.collect(
                Collectors.toMap(Data::getType, Data::getPrice, Double::sum)
        );

        // one more optional parameter is the Map supplier creating a new map (eg HashMap::new)
    }

    //
    // some collectors will do some calculation to get a final value
    //
    public void calculatingCollection() {

        // count number of elements
        long cnt = s.collect(Collectors.counting());

        // calculate average - with a mapper function to get the value to calculate average of
        double avgPrice = s.collect(
                Collectors.averagingDouble(Data::getPrice)
        );
        double avgHeight = s.collect(
                Collectors.averagingInt(Data::getHeight)
        );
        // also available for Long

        // find maximum/minimum element
        Optional<Data> highest = s.collect(
                Collectors.maxBy(
                        Comparator.comparing(Data::getHeight)
                )
        );
        Optional<Data> cheapest = s.collect(
                Collectors.minBy(
                        Comparator.comparing(Data::getPrice)
                )
        );

        // sum
        double totalPrice = s.collect(
                Collectors.summingDouble(Data::getPrice)
        );

        // all of the above as a summary
        IntSummaryStatistics heightStat = s.collect(Collectors.summarizingInt(Data::getHeight));

        // and there is a special one for joining strings

        String allNames = s.map(Data::getName).collect(
                Collectors.joining()
        );

        // special versions provide a delimiter, and optional prefix/suffix as well
        String allNamesCommaSeparated = s.map(Data::getName).collect(
                Collectors.joining(", ")
        );
    }

    //
    // Composing collectors
    //
    public void composing() {

        // with collectiongAndThen a final transformation can be applied to collection result before returning
        Optional<String> cheapestName = s.collect(
                Collectors.collectingAndThen(
                    Collectors.minBy(
                            Comparator.comparing(Data::getPrice)
                    ),
                    (x) -> x.map(Data::getName)
                )
        );

        // with mapping, a pre-collection mapping can be applied
        Set<String> types = s.collect(
                Collectors.mapping(Data::getType, Collectors.toSet())
        );
    }

    //
    // Grouping creates a map based on a classifier retrieved by a lambda function
    //
    public void grouping() {

        // in simplest form, groupingBy will create a list for each classifier
        Map<String, List<Data>> byType = s.collect(
                Collectors.groupingBy(Data::getType)
        );

        // extended version can use a downstream collector for each of the groups
        Map<String, Double> avgPriceByType = s.collect(
                Collectors.groupingBy(Data::getType, Collectors.averagingDouble(Data::getPrice))
        );

        // this could also result in two level grouping
        // example omitted

        // a special version can use a map supplier
        Map<String, List<Data>> byTypeTreeMap= s.collect(
                Collectors.groupingBy(Data::getType, TreeMap::new, Collectors.toList())
        );


        // there is a groupingByConcurrent version of all of the above as well, which will create a ConcurrentMap

        // partitioningBy uses a special Boolean classifier, provided by a predicate
        // this has two versoin, with or without a downstream collector
        Map<Boolean, List<Data>> isCheaperThan2Bucks = s.collect(
                Collectors.partitioningBy((x) -> x.getPrice() < 2)
        );
    }

    //
    // Note: Collectors are working only on generic streams, not on primitives
    //
}
