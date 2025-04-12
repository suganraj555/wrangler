package io.cdap.wrangler.grammar;

import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;
import io.cdap.wrangler.api.parser.Token;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class RecipeVisitor extends DirectiveBaseVisitor<Token> {

    @Override
    public Token visitByteSizeArg(DirectivesParser.ByteSizeArgContext ctx) {
        // Visit ByteSize arguments and return a new ByteSize token
        return new ByteSize(ctx.getText());
    }

    @Override
    public Token visitTimeDurationArg(DirectivesParser.TimeDurationArgContext ctx) {
        // Visit TimeDuration arguments and return a new TimeDuration token
        return new TimeDuration(ctx.getText());
    }

    // Other visit methods for other constructs can be added here
}
