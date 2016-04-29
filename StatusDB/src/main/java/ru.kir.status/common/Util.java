package ru.kir.status.common;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Kirill Zhitelev on 29.03.2016.
 */
public class Util {
    /**
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return true if intersection between two given dates is one hour or more
     */
    public static Boolean isOverlap(String start1, String end1, String start2, String end2) {
        DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");

        DateTime startDT1 = format.parseDateTime(start1);
        DateTime endDT1 = format.parseDateTime(end1);
        DateTime startDT2 = format.parseDateTime(start2);
        DateTime endDT2 = format.parseDateTime(end2);

        DateTime startMax = (startDT1.isAfter(startDT2)) ? startDT1 : startDT2;
        DateTime endMin = (endDT1.isBefore(endDT2)) ? endDT1 : endDT2;

        return (endMin.getMillis() - startMax.getMillis()) >= 3600000;
    }
}
