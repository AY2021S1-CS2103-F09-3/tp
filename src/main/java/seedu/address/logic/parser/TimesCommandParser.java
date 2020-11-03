package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMES;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.TimesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.book.Times;

/**
 * Parses input arguments and creates a new {@code TimesCommand} object
 */
public class TimesCommandParser implements Parser<TimesCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code TimesCommand}
     * and returns a {@code TimesCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TimesCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TIMES);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimesCommand.MESSAGE_USAGE), ive);
        }

        String times = argMultimap.getValue(PREFIX_TIMES).orElse("");

        return new TimesCommand(index, new Times(times));
    }
}
