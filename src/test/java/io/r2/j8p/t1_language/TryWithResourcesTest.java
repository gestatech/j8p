package io.r2.j8p.t1_language;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * TryWithResourcesTest
 *
 * @author robymus <r@r2.io>
 */
public class TryWithResourcesTest {

    @Test
    public void testGetFibonacciProvider() throws Exception {
        TryWithResources t = new TryWithResources();
        try (TryWithResources.FibonacciProvider f = t.getFibonacciProvider(20)) {
            List<Integer> fib = new ArrayList<>();
            f.forEachRemaining(fib::add);
            assertThat(fib).containsExactly(1, 1, 2, 3, 5, 8, 13);
        }
        assertThat(t.getLastProvider().isClosed()).isTrue();
    }

}