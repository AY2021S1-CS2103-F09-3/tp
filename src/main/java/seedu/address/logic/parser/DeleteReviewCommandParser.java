package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVIEWNUMBER;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.review.ReviewNumber;

/**
 * Parses input arguments and creates a new DeleteReviewCommand object
 */
public class DeleteReviewCommandParser implements Parser<DeleteReviewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteReviewCommand
     * and returns a DeleteReviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteReviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REVIEWNUMBER);

        Index index;

        if (!argMultimap.getValue(PREFIX_REVIEWNUMBER).isPresent()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteReviewCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteReviewCommand.MESSAGE_USAGE), pe);
        }

        ReviewNumber reviewNumber = ParserUtil.parseReviewNumber(argMultimap.getValue(PREFIX_REVIEWNUMBER).get());

        return new DeleteReviewCommand(index, reviewNumber);
    }
}
