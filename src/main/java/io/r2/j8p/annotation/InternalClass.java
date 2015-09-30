package io.r2.j8p.annotation;

/**
 * InternalClass
 */
public class InternalClass implements IInternal {

    static int versionCounter = 0;

    int myVersion;

    public InternalClass() {
        myVersion = versionCounter;
        versionCounter++;
    }

    @Override
    public int getVersion() {
        return myVersion;
    }

    static public void reset() {
        versionCounter = 0;
    }
}
