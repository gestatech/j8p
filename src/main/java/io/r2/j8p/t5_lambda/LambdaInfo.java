package io.r2.j8p.t5_lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Describe a lambda expression; refactor the code that uses an anonymous inner class to use a lambda expression; describe type inference and target typing
 */
public class LambdaInfo {

    public void lambdaInfo() {

        // inline lambda: implicit return
        Supplier<String> s1 = () -> "hello";

        // block lambda: has to return (if needed)
        UnaryOperator<String> ss = (s) -> {
            if (s == null) return "null";
            else return "not null";
        };

        // type inference: lambda argument types are optional and are inferred from the location is used
        BiFunction<String, Object, Double> bf = (/*inferred as string */ s, /* inferred as Object */o) -> {
            return 5.2; /* return type is inferred as double */
        };

        // target typing: lambda can only be used where target type is known, eg. value to variable, parameter, etc.
        // invalid if standalone: () -> "hello";
    }
}
