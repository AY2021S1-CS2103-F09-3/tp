package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMES;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteByCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Name;
import seedu.address.model.book.Times;


/**
 * Parses input arguments and creates a new DeleteByCommand object
 */
public class DeleteByCommandParser implements Parser<DeleteByCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteByCommand object for execution.
     * * @return DeleteByCommand.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteByCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ISBN, PREFIX_TIMES);
        int attribute = 0;
        String content = "";

        boolean isNamePresent = isPrefixesPresent(argMultimap, PREFIX_NAME)
                && !arePrefixesPresent(argMultimap, PREFIX_ISBN, PREFIX_TIMES);
        boolean isIsbnPresent = isPrefixesPresent(argMultimap, PREFIX_ISBN)
                && !arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TIMES);
        boolean isTimesPresent = isPrefixesPresent(argMultimap, PREFIX_TIMES)
                && !arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ISBN);


        if ((arePrefixesPresent(argMultimap, PREFIX_ISBN, PREFIX_TIMES)
                || arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TIMES)
                || arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ISBN))
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteByCommand.MESSAGE_USAGE));
        }

        if (isNamePresent) {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            content = name.fullName;
            attribute = 0;
        } else if (isIsbnPresent) {
            Isbn isbn = ParserUtil.parseIsbn(argMultimap.getValue(PREFIX_ISBN).get());
            content = isbn.value;
            attribute = 1;
        } else if (isTimesPresent) {
            Times times = ParserUtil.parseTimes(argMultimap.getValue(PREFIX_TIMES).get());
            content = times.value;
            attribute = 2;
        }
        try {
            String trimmedArgs = content.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteByCommand.MESSAGE_USAGE));
            }
            return new DeleteByCommand(trimmedArgs, attribute);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteByCommand.MESSAGE_USAGE), pe);
        }
    }

    private static boolean isPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
