package michal.parser;

import michal.parser.generated.CronGrammarBaseListener;
import michal.parser.generated.CronGrammarParser;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class CronGrammarSequenceParser extends CronGrammarBaseListener {
    private final int minValue;
    private final int maxValue;
    private final List<Integer> sequence;
    private final String name;

    public CronGrammarSequenceParser(String name, int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.sequence = new ArrayList<>();
        this.name = name;
    }

    @Override
    public void enterExplicit(CronGrammarParser.ExplicitContext ctx) {
        for (TerminalNode valNode : ctx.INT()) {
            sequence.add(Integer.parseInt(valNode.getText()));
        }

        validate();
    }

    @Override
    public void enterRange(CronGrammarParser.RangeContext ctx) {
        int start = Integer.parseInt(ctx.INT(0).getText());
        int stop = Integer.parseInt(ctx.INT(1).getText());
        generateArithmetical(start, stop, 1);
        validate();
    }

    @Override
    public void enterArithmetical(CronGrammarParser.ArithmeticalContext ctx) {
        int step = Integer.parseInt(ctx.arithmetical_step().getText());
        generateArithmetical(minValue, maxValue, step);
        validate();
    }

    @Override
    public void enterAll(CronGrammarParser.AllContext ctx) {
        generateArithmetical(minValue, maxValue, 1);
        validate();
    }

    private void validate() {
        for (int x: sequence) {
            if (x < minValue || x > maxValue) {
                var msg = String.format("Number %d is out of %s range", x, name);
                throw new RuntimeException(msg);
            }
        }
    }

    private void generateArithmetical(int min, int max, int step) {
        for (int n = min; n <= max; n += step) {
            sequence.add(n);
        }
    }

    public List<Integer> getSequence() {
        return sequence;
    }
}
