package io.r2.j8p.annotation;

/**
 * InternalClass
 */
public class InternalClass {

    static int versionCounter = 0;

    int myVersion;

    InternalClass() {
        myVersion = versionCounter;
        versionCounter++;
    }

    int getVersion() {
        return myVersion;
    }

    static void reset() {
        versionCounter = 0;
    }
}
