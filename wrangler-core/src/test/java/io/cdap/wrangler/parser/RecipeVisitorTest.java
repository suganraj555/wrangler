/*
 * Copyright © 2024
 * Licensed under the Apache License, Version 2.0
 */

package io.cdap.wrangler.parser;

import io.cdap.wrangler.grammar.DirectivesLexer;
import io.cdap.wrangler.grammar.DirectivesParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for parsing ByteSize and TimeDuration arguments
 */
public class RecipeVisitorTest {

  @Test
  public void testByteSizeAndTimeDurationParsing() {
    String input = "100KB 60s";

    // Setup lexer and parser
    DirectivesLexer lexer = new DirectivesLexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    DirectivesParser parser = new DirectivesParser(tokens);

    // Create the visitor
    DirectiveVisitor visitor = new DirectiveVisitor();

    // Visit each argument explicitly
    DirectivesParser.ByteSizeArgContext byteSizeCtx = parser.byteSizeArg();
    Token byteSizeToken = visitor.visitByteSizeArg(byteSizeCtx);

    DirectivesParser.TimeDurationArgContext timeCtx = parser.timeDurationArg();
    Token timeToken = visitor.visitTimeDurationArg(timeCtx);

    assertNotNull(byteSizeToken);
    assertEquals("100KB", byteSizeToken.getText());

    assertNotNull(timeToken);
    assertEquals("60s", timeToken.getText());
  }
}
