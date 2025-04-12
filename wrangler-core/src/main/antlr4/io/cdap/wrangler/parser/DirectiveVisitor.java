package io.cdap.wrangler.parser;

public class DirectiveVisitor extends DirectivesBaseVisitor<Token> {
    @Override
    public Token visitByteSizeArg(DirectivesParser.ByteSizeArgContext ctx) {
        return new ByteSize(ctx.getText());
    }

    @Override
    public Token visitTimeDurationArg(DirectivesParser.TimeDurationArgContext ctx) {
        return new TimeDuration(ctx.getText());
    }
}
