package io.r2.j8p.t5_lambda;

import java.util.Optional;

/**
 * Develop code that uses the Optional APIs
 */
public class OptionalAPI {

    public void optional() {

        // an optional value may hold a value or may not, tries to eliminate null checks
        Optional<String> x;

        // creating empty - not containing value
        x = Optional.empty();

        // creating with value
        x = Optional.of("hello");

        // if null, create empty, otherwise with value
        x = Optional.ofNullable(null);

        // check value
        x.isPresent();

        // get value (or get default, get generated or throw exception)
        x.get();
        x.orElse("default");
        x.orElseGet(() -> "default");
        x.orElseThrow(() -> new IllegalArgumentException());

        // magic lambda operations

        // if present, execute consumer
        x.ifPresent(System.out::println);

        // filter with predicate and return new optional
        Optional<String> filtered = x.filter((s) -> s.length() > 3);

        // flatmap maps to a new optional
        // if not present, the result is empty optional
        Optional<Integer> flatMapped = x.flatMap((s) -> s.length() == 0 ? Optional.empty() : Optional.of(s.length()));

        // map maps to value or null, and if null, it is converted to empty optional
        // if not present, the result is empty optional
        Optional<Integer> mapped = x.map(((s) -> s.length() == 0 ? 0 : s.length()));


    }

}
