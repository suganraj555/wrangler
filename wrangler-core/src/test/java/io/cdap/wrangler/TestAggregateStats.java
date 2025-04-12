package io.cdap.wrangler;

import io.cdap.wrangler.api.Row;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class TestAggregateStats {

    @Test
    public void testAggregateStats() throws Exception {
        // Sample rows with data for transfer size and response time
        List<Row> rows = List.of(
            new Row().add("data_transfer_size", "10KB").add("response_time", "200ms"),
            new Row().add("data_transfer_size", "1MB").add("response_time", "1.8s")
        );

        // Define the recipe that uses the aggregate-stats directive
        String[] recipe = {
            "aggregate-stats :data_transfer_size :response_time total_size_mb total_time_sec"
        };

        // Execute the recipe with the input rows
        List<Row> results = TestingRig.execute(recipe, rows);

        // Verify the result: only one row with aggregated values
        assertEquals(1, results.size());
        
        // Verify the aggregated size (in MB) and time (in seconds)
        assertEquals(1.009765625, results.get(0).getValue("total_size_mb"), 0.001);
        assertEquals(2.0, results.get(0).getValue("total_time_sec"), 0.001);
    }
}
