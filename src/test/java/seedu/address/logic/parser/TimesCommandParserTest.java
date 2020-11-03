package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TimesCommand;
import seedu.address.model.book.Times;

public class TimesCommandParserTest {
    private TimesCommandParser parser = new TimesCommandParser();
    private final String nonEmptyTimes = "123";

    @Test
    public void parse_indexSpecified_success() {
        // have times
        Index targetIndex = INDEX_FIRST_BOOK;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_TIMES + nonEmptyTimes;
        TimesCommand expectedCommand = new TimesCommand(INDEX_FIRST_BOOK, new Times(nonEmptyTimes));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimesCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, TimesCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, TimesCommand.COMMAND_WORD + " " + nonEmptyTimes, expectedMessage);
    }
}
