package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVIEW;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddReviewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.review.Rating;
import seedu.address.model.review.Review;
import seedu.address.model.review.ReviewContent;

/**
 * Parses input arguments and creates a new AddReviewCommand object
 */
public class AddReviewCommandParser implements Parser<AddReviewCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddReviewCommand
     * and returns a AddReviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddReviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_RATING, PREFIX_REVIEW);

        Index index;

        if (!argMultimap.getValue(PREFIX_RATING).isPresent()
                || !argMultimap.getValue(PREFIX_REVIEW).isPresent()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddReviewCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReviewCommand.MESSAGE_USAGE), pe);
        }

        Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get());
        ReviewContent reviewContent = ParserUtil.parseReviewContent(argMultimap.getValue(PREFIX_REVIEW).get());
        Review review = new Review(rating, reviewContent);

        return new AddReviewCommand(index, review);
    }
}
