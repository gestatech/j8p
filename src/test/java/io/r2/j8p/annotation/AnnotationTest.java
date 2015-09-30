package io.r2.j8p.annotation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * AnnotationTest
 */
@Test
public class AnnotationTest {

    Annotated annotatedObj;

    @BeforeMethod
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
        assertEquals(annotatedObj.getVersion(), 0);
        CreateNewInstance.recreateSpecial(annotatedObj);
        assertEquals(annotatedObj.getVersion(), 1);
    }

}
