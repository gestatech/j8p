package io.r2.j8p.t3_localization;

import java.util.ListResourceBundle;

/**
 * Default bundle for no matching locales
 *
 * @author robymus <r@r2.io>
 */
public class TextBundle extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            {"name", "Name_def"}
        };
    }
}
