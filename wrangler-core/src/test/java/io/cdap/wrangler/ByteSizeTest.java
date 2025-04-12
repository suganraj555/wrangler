package io.cdap.wrangler;

import org.junit.Test;
import static org.junit.Assert.*;

public class ByteSizeTest {

    @Test
    public void testByteSizeParsing() {
        // Test for '10KB' to bytes
        ByteSize b1 = new ByteSize("10KB");
        assertEquals(10240L, b1.getBytes());  // 10 KB = 10240 bytes
        
        // Test for '1.5MB' to bytes
        ByteSize b2 = new ByteSize("1.5MB");
        assertEquals(1572864L, b2.getBytes());  // 1.5 MB = 1572864 bytes
    }
}
