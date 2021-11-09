package michal;

import michal.parser.CronGrammarSequenceParser;
import michal.parser.CronParser;
import michal.parser.generated.CronGrammarParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class ParseCron {
    public static void main(String[] args) {
        CronGrammarParser.LineContext line = CronParser.parse(args[0]);

        var minutes = parse(line.minute_sequence(), 0, 59);
        var hours = parse(line.hour_sequence(), 0, 23);
        var daysOfMonth = parse(line.day_of_month_sequence(), 1, 31);
        var months = parse(line.month_sequence(), 1, 12);
        var daysOfWeek = parse(line.day_of_week_sequence(), 0, 6);
        String cmd = line.command().getText();

        print(minutes, hours, daysOfMonth, months, daysOfWeek, cmd);
    }

    private static List<Integer> parse(ParseTree tree, int min, int max) {
        ParseTreeWalker walker = new ParseTreeWalker();
        var sequence = new CronGrammarSequenceParser(min, max);
        walker.walk(sequence, tree);
        return sequence.getSequence();
    }

    private static void print(List<Integer> minutes,
                              List<Integer> hours,
                              List<Integer> daysOfMonth,
                              List<Integer> months,
                              List<Integer> daysOfWeek,
                              String cmd) {
        System.out.println(StringUtils.rightPad("minute", 14) + printList(minutes));
        System.out.println(StringUtils.rightPad("hour", 14) + printList(hours));
        System.out.println(StringUtils.rightPad("day of month", 14) + printList(daysOfMonth));
        System.out.println(StringUtils.rightPad("month", 14) + printList(months));
        System.out.println(StringUtils.rightPad("day of week", 14) + printList(daysOfWeek));
        System.out.println(StringUtils.rightPad("command", 14) + cmd);
    }

    private static String printList(List<Integer> daysOfWeek) {
        return daysOfWeek.stream().map(v -> v.toString()).collect(Collectors.joining(" "));
    }
}
