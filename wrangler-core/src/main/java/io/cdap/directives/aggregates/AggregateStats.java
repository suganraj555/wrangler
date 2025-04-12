package io.cdap.directives.aggregates;

import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.annotations.Public;
import io.cdap.wrangler.api.parser.Directive;
import io.cdap.wrangler.api.parser.DirectiveContext;
import io.cdap.wrangler.api.parser.DirectiveExecutionException;
import io.cdap.wrangler.api.parser.DirectiveInfo;
import io.cdap.wrangler.api.parser.DirectiveType;
import io.cdap.wrangler.api.parser.Token;
import io.cdap.wrangler.api.parser.TokenGroup;
import io.cdap.wrangler.api.ExecutorContext;

import java.util.List;

@Public
@DirectiveInfo(
  name = "aggregate-stats",
  description = "Aggregates byte size and time duration across rows.",
  type = DirectiveType.AGGREGATE
)
public class AggregateStats implements Directive {
  private String sizeCol;
  private String timeCol;
  private String outSizeCol;
  private String outTimeCol;
  private long totalBytes = 0;
  private long totalTime = 0;

  @Override
  public void initialize(DirectiveContext ctx, TokenGroup args) throws DirectiveExecutionException {
    this.sizeCol = ((Token) args.getValue(0)).value();
    this.timeCol = ((Token) args.getValue(1)).value();
    this.outSizeCol = ((Token) args.getValue(2)).value();
    this.outTimeCol = ((Token) args.getValue(3)).value();
  }

  @Override
  public List<Row> execute(List<Row> rows, ExecutorContext ctx) throws DirectiveExecutionException {
    for (Row row : rows) {
      String byteStr = row.getValue(sizeCol).toString();
      String timeStr = row.getValue(timeCol).toString();
      totalBytes += new ByteSize(byteStr).getBytes();
      totalTime += new TimeDuration(timeStr).getMilliseconds();
    }

    Row result = new Row();
    result.add(outSizeCol, totalBytes / (1024.0 * 1024)); // MB
    result.add(outTimeCol, totalTime / 1000.0); // seconds
    return List.of(result);
  }
}
