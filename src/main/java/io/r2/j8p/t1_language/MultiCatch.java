package io.r2.j8p.t1_language;

import java.io.EOFException;
import java.io.IOException;

/**
 * Develop code that handles multiple Exception types in a single catch block
 *
 * @author robymus <r@r2.io>
 */
public class MultiCatch {

    public void t1() throws IOException {
        throw new IOException();
    }

    public void t2() throws EOFException {
        throw new EOFException();
    }

    public void t3() throws IllegalArgumentException {
        throw new IllegalArgumentException();
    }

    public void catch1() {
        try {
            t1();
            t2();
            t3();
        }
        // can't multicatch subclass:
        // catch (IOException | EOFException e) {
        catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
