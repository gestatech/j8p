package io.r2.j8p.t4_nio2;

import org.testng.annotations.Test;

import java.nio.file.Paths;

import static org.testng.Assert.*;

/**
 * Created by robymus on 2016-11-21.
 */
public class FilesAdvancedOpsTest {

    // @Test
    public void testLines() throws Exception {
        new FilesAdvancedOps().lines(Paths.get("/tmp/test.txt"));
    }

}