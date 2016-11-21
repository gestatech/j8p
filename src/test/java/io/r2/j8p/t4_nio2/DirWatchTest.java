package io.r2.j8p.t4_nio2;

import org.testng.annotations.Test;

import java.nio.file.Paths;

import static org.testng.Assert.*;

/**
 * Not real test, just writing to stdout
 */
public class DirWatchTest {
    //@Test
    public void testWatch() throws Exception {
        new DirWatch().watch(Paths.get("/tmp/w"));
    }

}