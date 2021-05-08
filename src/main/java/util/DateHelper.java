package util;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("javadoc")
public class DateHelper {

    public static String sFormat = "yyyy-MM-dd";

    public static Date getDate(final String sDate) throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat(sFormat);
        return sdf.parse(sDate);
    }

    public static Date getDate(final String sDate, final String anotherFormat) throws Exception {
        final SimpleDateFormat sdf = new SimpleDateFormat(anotherFormat);
        return sdf.parse(sDate);
    }
}
