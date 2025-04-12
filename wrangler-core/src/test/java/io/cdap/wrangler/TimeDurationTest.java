package io.cdap.wrangler;

import org.junit.Test;
import static org.junit.Assert.*;

public class TimeDurationTest {

    @Test
    public void testTimeDurationParsing() {
        // Test for '2s' to milliseconds
        TimeDuration t1 = new TimeDuration("2s");
        assertEquals(2000L, t1.getMilliseconds());  // 2 seconds = 2000 milliseconds
        
        // Test for '1.5m' to milliseconds
        TimeDuration t2 = new TimeDuration("1.5m");
        assertEquals(90000L, t2.getMilliseconds());  // 1.5 minutes = 90,000 milliseconds
    }
}
