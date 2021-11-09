package michal.parser;
import michal.parser.generated.CronGrammarLexer;
import michal.parser.generated.CronGrammarParser;
import org.antlr.v4.runtime.*;

public class CronParser {
    public static CronGrammarParser.LineContext parse(String line) {
        var input = new ANTLRInputStream(line);
        var lexer = new CronGrammarLexer(input);
        var tokens = new CommonTokenStream(lexer);
        var parser = new CronGrammarParser(tokens);
        parser.addErrorListener(new ThrowingErrorListener());
        return parser.line();
    }
}
