package io.r2.j8p.t2_concurrency;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * ExecutorExTest
 *
 * @author robymus <r@r2.io>
 */
public class ExecutorExTest {

    private ExecutorEx ex;

    @BeforeMethod
    public void setUp() {
        ex = new ExecutorEx();
    }

    @Test
    public void testParallelAdd_class() throws Exception {
        assertThat(ex.parallelAdd_class(5,7)).isEqualTo(12);
    }

    @Test
    public void testParallelAdd_lambda() throws Exception {
        assertThat(ex.parallelAdd_lambda(5, 7, 8, 2)).isEqualTo(22);
    }

    @Test
    public void testCompletableFutureAdd() throws Exception {
        assertThat(ex.completableFutureAdd(5, 7, 8, 2)).isEqualTo(22);
    }


}