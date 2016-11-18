package io.r2.j8p.t3_localization;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * NumberDateFormatTest
 *
 * @author robymus <r@r2.io>
 */
public class NumberDateFormatTest {

    @Test
    public void testJava_text_DateFormat() throws Exception {
        NumberDateFormat n = new NumberDateFormat();
        assertThat(n.java_text_DateFormat()).containsExactly(
                "Dienstag, 13. Dezember 2011",
                "Dec 13, 2011 7:08:09 AM",
                "7:08 AM"
        );
    }

    @Test
    public void testJava_time_DateFormatter() throws Exception {
        NumberDateFormat n = new NumberDateFormat();
        assertThat(n.java_time_DateFormatter()).containsExactly(
                "2011-12-13",
                "Dienstag, 13. Dezember 2011",
                "Samstag, 12. Dezember 1998",
                "Dec 13, 2011 7:08:09 AM",
                "7:08 AM"
        );
    }

    @Test
    public void testJava_text_NumberFormat() throws Exception {
        NumberDateFormat n = new NumberDateFormat();
        assertThat(n.java_text_NumberFormat()).containsExactly(
                "1 234 567,891",
                "$5,666,777.23",
                "94%",
                "987,654,321"
        );
    }

}