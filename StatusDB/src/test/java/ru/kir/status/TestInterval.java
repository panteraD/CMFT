package ru.kir.status;

import org.junit.Assert;
import org.junit.Test;
import ru.kir.status.common.Util;

/**
 * Created by Kirill Zhitelev on 29.03.2016.
 */
public class TestInterval {

    @Test
    public void test() {
        //window is 0.5 hour
        Assert.assertEquals(Boolean.FALSE, Util.isOverlap("13:00", "14:00", "13:30", "14:30"));
        //window is 1 hour
        Assert.assertEquals(Boolean.TRUE, Util.isOverlap("13:00", "17:00", "13:30", "14:30"));
        Assert.assertEquals(Boolean.TRUE, Util.isOverlap("13:30", "14:30", "13:00", "17:00"));
        //window is 59 min
        Assert.assertEquals(Boolean.FALSE, Util.isOverlap("13:00", "14:00", "13:00", "13.59"));
        //window is 0
        Assert.assertEquals(Boolean.FALSE, Util.isOverlap("13:00", "14:00", "15:00", "17.59"));
        Assert.assertEquals(Boolean.FALSE, Util.isOverlap("15:00", "17.59", "13:00", "14:00"));

    }

}
