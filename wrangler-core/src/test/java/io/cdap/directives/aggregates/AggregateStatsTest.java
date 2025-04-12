package io.cdap.directives.aggregates;

import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.parser.DirectiveContext;
import io.cdap.wrangler.api.parser.DirectiveExecutionException;
import io.cdap.wrangler.api.parser.Token;
import io.cdap.wrangler.api.parser.TokenGroup;
import io.cdap.wrangler.api.ExecutorContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AggregateStatsTest {

  @Test
  public void testAggregateStats() throws DirectiveExecutionException {
    AggregateStats directive = new AggregateStats();

    // Prepare mock arguments for initialize()
    TokenGroup args = new TokenGroup();
    args.add(new Token("inputSize", Token.TokenType.STRING));
    args.add(new Token("inputTime", Token.TokenType.STRING));
    args.add(new Token("totalSizeMB", Token.TokenType.STRING));
    args.add(new Token("totalTimeSec", Token.TokenType.STRING));

    directive.initialize(new DirectiveContext(), args);

    // Create sample rows
    Row row1 = new Row();
    row1.add("inputSize", "100KB");
    row1.add("inputTime", "30s");

    Row row2 = new Row();
    row2.add("inputSize", "200KB");
    row2.add("inputTime", "45s");

    List<Row> result = directive.execute(Arrays.asList(row1, row2), new ExecutorContext());

    Assert.assertEquals(1, result.size());
    Row aggregated = result.get(0);

    double expectedSizeMB = 300.0 / 1024;
    double expectedTimeSec = 75.0;

    Assert.assertEquals(expectedSizeMB, (double) aggregated.getValue("totalSizeMB"), 0.001);
    Assert.assertEquals(expectedTimeSec, (double) aggregated.getValue("totalTimeSec"), 0.001);
  }
}
