package io.r2.j8p.t3_localization;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Build a resource bundle for a locale and call a resource bundle from an application
 *
 * @author robymus <r@r2.io>
 */
public class Bundles {

    public static final String BUNDLENAME="io.r2.j8p.t3_localization.TextBundle";

    public static ResourceBundle getBundleFR() {
        Locale.setDefault(Locale.FRANCE);
        return ResourceBundle.getBundle(BUNDLENAME);
    }

    public static ResourceBundle getBundleUS() {
        Locale.setDefault(Locale.US);
        return ResourceBundle.getBundle(BUNDLENAME);
    }

    public static ResourceBundle getBundleHU() {
        Locale.setDefault(LocaleEx.getLocaleHU());
        return ResourceBundle.getBundle(BUNDLENAME);
    }

}
