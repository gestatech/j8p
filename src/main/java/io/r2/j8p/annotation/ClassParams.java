package io.r2.j8p.annotation;

import java.lang.annotation.*;

/**
 * ClassParams
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassParams {
    String version() default "unknown";
    String[] tags();
}
