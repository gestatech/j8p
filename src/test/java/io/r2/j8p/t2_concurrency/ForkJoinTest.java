package io.r2.j8p.t2_concurrency;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * ForkJoinTest
 *
 * @author robymus <r@r2.io>
 */
public class ForkJoinTest {
    @Test
    public void testDivconqSum() throws Exception {
        ForkJoin f = new ForkJoin();
        assertThat(f.divconqSum()).isEqualTo(f.sum);
    }

}