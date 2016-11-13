package io.r2.j8p.t2_concurrency;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * SynchronizersTest
 *
 * @author robymus <r@r2.io>
 */
public class SynchronizersTest {

    Synchronizers s;

    @BeforeMethod
    public void setUp() throws Exception {
        s = new Synchronizers();
    }

    @Test
    public void testSyncSemaphore() throws Exception {
        assertThat(s.syncSemaphore()).containsExactly("1i", "1o", "2i", "2o");
    }

    @Test
    public void testSyncCountdownLatch() throws Exception {
        assertThat(s.syncCountdownLatch()).isTrue();
    }

    @Test
    public void testSyncCyclicBarrier() throws Exception {
        assertThat(s.syncCyclicBarrier()).containsExactly("1-1", "2-1", "1-2", "2-2");
    }

    @Test
    public void testSyncExchanger() throws Exception {
        assertThat(s.syncExchanger()).containsExactly("W2", "W1");
    }

    @Test
    public void testSyncPhaser() throws Exception {
        assertThat(s.syncPhaser()).containsExactly("1-1", "2-1", "3-1", "2-2", "3-2", "3-3", "FIN");
    }

}