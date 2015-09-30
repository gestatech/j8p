package io.r2.j8p.annotation;

/**
 * Annotated
 * Annotations can be applied to declarations: declarations of classes, fields, methods, and other program elements.
 * When used on a declaration, each annotation often appears, by convention, on its own line.
 */
@ClassParams(version="1.0", tags={"simple", "annotation", "testing"})
public class Annotated {

    @SpecialField public IInternal internal;

    public Annotated() {
        internal = new InternalClass();
    }

    public int getVersion() {
        return internal.getVersion();
    }

}
