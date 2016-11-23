package io.r2.j8p.t6_collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Develop code that iterates a collection, filters a collection, and sorts a collection by using lambda expressions
 * Develop code that uses Java SE 8 collection improvements, including the Collection.removeIf(),
 */
public class CollectionBasics {

    List<Integer> data;

    CollectionBasics() {
        data = IntStream.rangeClosed(1, 20).boxed().collect(Collectors.toList());
    }

    public void iterate() {
        data.forEach((d) -> {
            // iterate with forEach - this works for every Collection
            System.out.println(d);
        });
    }

    public void filter() {
        // van only be filtered using a stream actually
        data.stream().filter((d) -> d%3 == 0).forEach(System.out::println);
    }

    public void sort() {
        // sorting is quite simple, just List.sort, but constructing the Comparator is getting easier

        // Comparator can be constructed from lambda
        data.sort((a,b) -> a < b ? -1 : a > b ? 1 : 0);

        // use a method ref
        data.sort(Integer::compareUnsigned);

        // or create a new one from Comparator default methods, which is the real fun
        // Note: sort is in List interface, not Collection
        data.sort(Comparator.reverseOrder());

        Comparator<Integer> c = Comparator.comparingInt((Integer x) -> x*x).thenComparing((x) -> data.get(x)).reversed();
        data.sort(c);

        // nullsFirst and nullsLast is special, not chainable, must be top level
        data.sort(Comparator.nullsFirst(c));
    }

    public void collectionExtensions() {
        // removeIf is new in collection, can removve elements matching a filter
        data.removeIf((x) -> x > 3 && x < 10);

        // list extension: replaceAll - applies an UnaryOperator
        data.replaceAll((x) -> x-10);
        data.replaceAll(Math::abs);

    }

    public void mapExtensions() {
        // maps have some interesting default methods
        Map<String, Integer> map = new HashMap<>();

        // compute: applies mapping to a key if key is present, or to null, of key is not present, and stores returned value
        map.compute("key", (key, val) -> {
                    if (val == null) return -1;
                    else return val*val;
                }
        );

        // computeIfAbsent only operates when key is not present or value is null, and if returned value is not null, stores new value
        map.computeIfAbsent("key", (key) -> key.length());

        // compute if present works when key is present and maps a new value
        map.computeIfPresent("key", (key, value) -> value + key.length());

        // merge - if the key is not present (or null), puts new value, otherwise applies mapping function to old and new value
        map.merge("counter", 1, (oldVal, newVal) -> oldVal+newVal);

        // put a value of not present yet
        map.putIfAbsent("key", 2);

        // removes key only if value matchaes
        map.remove("key", 3);

        // replaces a value only if it's already mapped (and optionally has specific value)
        map.replace("key1", 3);
        map.replace("key1", 2 /* expected value */, 3);

        // getOrDefault returns a default value if key is not present
        map.getOrDefault("key", -1);



    }

}
