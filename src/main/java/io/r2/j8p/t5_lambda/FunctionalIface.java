package io.r2.j8p.t5_lambda;

import java.util.function.*;

/**
 * Define and write functional interfaces and describe the interfaces of the java.util.function package
 * Develop code that uses the built-in interfaces included in the java.util.function package, such as Function, Consumer, Supplier, UnaryOperator, Predicate, including the primitive and binary variations of the interfaces
 */
public class FunctionalIface {

    // informative annotation
    @FunctionalInterface
    public interface FunctInt {
        // must have 1 abstract method
        int calculate(int z);
    }

    public void lambda() {
        // that's id, lambda as functional interface
        FunctInt f = (z) -> 2*z;
    }

    /**
     * java.util.function has predefined functional interfaces for common uses
     */
    public void builtInFunctions() {

        // object version

        // consumer: (method: accept)
        Consumer<String> c = (String x) -> {};
        BiConsumer<String, Integer> bc = (String x, Integer y) -> {};

        // can be chained with default method andThen
        Consumer<String> c2 = c.andThen(System.out::println);

        // function: (method: apply)
        Function<String, Integer> f = (String x) -> Integer.MAX_VALUE;
        BiFunction<String, Integer, Double> bf = (String x, Integer y) -> Double.MAX_VALUE;

        // Function can be compose with andThen (new function after) or compose (new function before)
        // BiFunction only support andTHen
        f.compose((x) -> "x"+x);
        f.andThen((x) -> -x);

        // static initializer for identity function
        Function<Integer, Integer> iii = Function.identity();


        // operators are special cases of Function (method: apply)
        UnaryOperator<Integer> uo = (Integer x) -> Integer.MAX_VALUE;
        BinaryOperator<Integer> bo = (Integer x, Integer y) -> Integer.max(x, y);

        // predicate: (method: test)
        Predicate<String> p = (String x) -> true;

        // composable by and, or, or negate
        p.negate().and((x) -> x.length() == 0);

        // static initializer for equality
        Integer val = 5;
        Predicate<Integer> isVal = Predicate.isEqual(val);

        // supplier: (method: get)
        Supplier<Integer> ss = () -> Integer.MAX_VALUE;

        // primitive type versions
        // for all primitive types Int, Long, Double:
        // method name has AsInt, AsDouble, AsLong suffix

        IntConsumer ic = (int x) -> {};
        ObjIntConsumer<String> oic = (String s, int x) -> {};

        IntFunction<String> _if = (int x) -> String.valueOf(x);
        IntToDoubleFunction idf = (int x) -> (double) x;
        IntToLongFunction idl = (int x) -> (long) x;

        ToIntFunction<String> tif = (String s) -> s.length();
        ToIntBiFunction<String, Double> tibf = (String s, Double d) -> 5;

        IntUnaryOperator iuo = (int x) -> -x;
        IntBinaryOperator ibo = (int x, int y) -> x+y;

        IntPredicate ip = (int x) -> true;
        IntSupplier is = () -> 5;
    }
}
