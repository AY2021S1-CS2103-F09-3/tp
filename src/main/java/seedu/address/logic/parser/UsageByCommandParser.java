package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_USAGE_BY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.UsageByCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Name;

/**
 * Parses input arguments and creates a new UsageByCommand object.
 */
public class UsageByCommandParser implements Parser<UsageByCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UsageCommand
     * and returns a UsageByCommand object for execution.
     *
     * @return UsageByCommand
     * @throws ParseException if the user input does not conform the expected format
     */
    public UsageByCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ISBN);
        String content = "";

        boolean isNamePresent = isPrefixesPresent(argMultimap, PREFIX_NAME)
                && !isPrefixesPresent(argMultimap, PREFIX_ISBN);
        boolean isIsbnPresent = isPrefixesPresent(argMultimap, PREFIX_ISBN)
                && !isPrefixesPresent(argMultimap, PREFIX_NAME);

        if (arePrefixesPresent(argMultimap, PREFIX_ISBN, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MESSAGE_USAGE_BY));
        }

        if (isNamePresent) {
            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).orElse(""));
            content = name.getName();
        } else if (isIsbnPresent) {
            Isbn isbn = ParserUtil.parseIsbn(argMultimap.getValue(PREFIX_ISBN).orElse(""));
            content = isbn.getIsbn();
        }
        try {
            String trimmedArgs = content.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE_BY));
            }
            return new UsageByCommand(trimmedArgs);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE_BY), pe);
        }
    }

    /**
     * Checks whether prefix is present.
     *
     * @param argumentMultimap argument multimap
     * @param prefix prefix
     * @return boolean value
     */
    private static boolean isPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }

    /**
     * Checks whether prefixes are present.
     *
     * @param argumentMultimap argument multimap
     * @param prefixes prefixes
     * @return boolean value
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
