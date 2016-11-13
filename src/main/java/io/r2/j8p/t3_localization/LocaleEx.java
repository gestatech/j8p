package io.r2.j8p.t3_localization;

import java.util.Locale;

/**
 * Describe the advantages of localizing an application and
 * developing code that defines, reads, and sets the locale with a Locale object
 *
 * @author robymus <r@r2.io>
 */
public class LocaleEx {

    public static Locale getLocaleHU() {
        return new Locale("hu", "HU");
    }

    public static Locale getUS() {
        return Locale.US;
    }

    public static Locale build() {
        return new Locale.Builder()
                .setLanguage("hu")
                .setRegion("HU")
                .build();
    }

    public static Locale currentLocale() {
        return Locale.getDefault();
    }

    public static void setDefault(Locale l) {
        Locale.setDefault(l);
    }
}
