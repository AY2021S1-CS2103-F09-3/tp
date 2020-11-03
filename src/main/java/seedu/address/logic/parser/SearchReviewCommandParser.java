package seedu.address.logic.parser;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.SearchReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class SearchReviewCommandParser implements Parser<SearchReviewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the StockingCommand
     * and returns a StockingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SearchReviewCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(
                userInput,
                CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_ISBN
        );
        if (!argumentMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()
                && !argumentMultimap.getValue(CliSyntax.PREFIX_ISBN).isPresent()
                && !userInput.strip().equals("")) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    SearchReviewCommand.MESSAGE_USAGE));
        }

        List<String> names = null;
        List<String> numbers = null;

        if (argumentMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            names = Arrays.asList(argumentMultimap.getValue(CliSyntax.PREFIX_NAME).get().split("\\s+"));
        }
        if (argumentMultimap.getValue(CliSyntax.PREFIX_ISBN).isPresent()) {
            numbers = Arrays.asList(argumentMultimap.getValue(CliSyntax.PREFIX_ISBN).get().split("\\s+"));
        }

        return new SearchReviewCommand(names, numbers);
    }
}
