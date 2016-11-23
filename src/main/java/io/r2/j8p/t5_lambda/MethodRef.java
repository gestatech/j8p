package io.r2.j8p.t5_lambda;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.function.*;

/**
 * Develop code that uses a method reference, including refactoring a lambda expression to a method reference
 */
public class MethodRef<T extends Number> {

    public <U> U generate(U obj) {
        return obj;
    }


    public void methodRef() {

        // method references are automatically converted to lambda and thus functional interface implementations
        // there are different kind of method references

        // static method of a class
        IntUnaryOperator bc = Integer::bitCount;
        IntBinaryOperator max = Integer::max;

        // method of an instance
        Runnable r = this::methodRef;
        Consumer<String> print = System.out::println;

        // constructor
        Supplier<MethodRef> sm = MethodRef::new;

        // using generic methods - type is after ::
        UnaryOperator<ArrayList> uoa = this::<ArrayList>generate;

        // using generic constructors - type is before ::
        Supplier<Object> gc = MethodRef<Double>::new;
    }
}
