package io.r2.j8p.t4_nio2;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Not real test, just writing to stdout
 */
public class DirWalkingTest {

    // @Test
    public void testList() throws Exception {
        DirWalking.list("/tmp/1");
    }

    // @Test
    public void testFind() throws Exception {
        DirWalking.find("/tmp", 2, "glob:**/*.pdf");
    }

    // @Test
    public void testDirStream() throws Exception {
        DirWalking.dirStream("/tmp");
    }

    // @Test
    public void testWalkStream() throws Exception {
        DirWalking.walkStream("/tmp", "application/x-zip-compressed");
    }

    // @Test
    public void testWalkTree() throws Exception {
        new DirWalking().walkFileTree("/tmp");
    }


}