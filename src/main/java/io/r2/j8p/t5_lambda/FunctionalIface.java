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
        Consumer<String> c = (String x) -> {};
        BiConsumer<String, Integer> bc = (String x, Integer y) -> {};

        Function<String, Integer> f = (String x) -> Integer.MAX_VALUE;
        BiFunction<String, Integer, Double> bf = (String x, Integer y) -> Double.MAX_VALUE;

        UnaryOperator<Integer> uo = (Integer x) -> Integer.MAX_VALUE;
        BinaryOperator<Integer> bo = (Integer x, Integer y) -> Integer.max(x, y);

        Predicate<String> p = (String x) -> true;
        Supplier<Integer> ss = () -> Integer.MAX_VALUE;

        // primitive type versions
        // for all primitive types Int, Long, Double:

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
