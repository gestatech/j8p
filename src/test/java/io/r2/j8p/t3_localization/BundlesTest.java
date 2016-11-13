package io.r2.j8p.t3_localization;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

/**
 * BundlesTest
 *
 * @author robymus <r@r2.io>
 */
public class BundlesTest {

    Locale defLocale;

    @BeforeMethod
    public void saveLocale() {
        defLocale = Locale.getDefault();
    }

    @AfterMethod
    public void restoreLocale() {
        Locale.setDefault(defLocale);
    }

    @Test
    public void testGetBundleFR() throws Exception {
        assertThat(Bundles.getBundleFR().getString("name")).isEqualTo("Name_def");
    }

    @Test
    public void testGetBundleUS() throws Exception {
        assertThat(Bundles.getBundleUS().getString("name")).isEqualTo("Name");
    }

    @Test
    public void testGetBundleHU() throws Exception {
        assertThat(Bundles.getBundleHU().getString("name")).isEqualTo("Név");
    }

}