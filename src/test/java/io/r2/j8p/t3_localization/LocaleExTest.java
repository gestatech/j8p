package io.r2.j8p.t3_localization;

import org.testng.annotations.Test;

import java.util.Locale;

import static org.testng.Assert.*;

/**
 * LocaleExTest
 *
 * @author robymus <r@r2.io>
 */
public class LocaleExTest {
    @Test
    public void debugOutput() throws Exception {
        Locale def = Locale.getDefault();
        System.out.println(LocaleEx.currentLocale());
        System.out.println(LocaleEx.getLocaleHU());
        System.out.println(LocaleEx.build());
        LocaleEx.setDefault(LocaleEx.build());
        System.out.println(LocaleEx.currentLocale());
        Locale.setDefault(def);
    }

}