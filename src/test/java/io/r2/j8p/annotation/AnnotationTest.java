package io.r2.j8p.annotation;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * AnnotationTest
 */
@Test
public class AnnotationTest {

    Annotated annotatedObj;

    @BeforeTest
    void setup() {
        InternalClass.reset();
        annotatedObj = new Annotated();
    }

    @Test
    void version() {
        assertEquals(annotatedObj.getVersion(), 0);
    }

    @Test
    void recreate() throws Exception {
        CreateNewInstance.recreateSpecial(annotatedObj);
        assertEquals(annotatedObj.getVersion(), 1);
    }

}
