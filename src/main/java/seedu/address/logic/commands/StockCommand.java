package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.book.NameMatchesKeywordPredicate;
import seedu.address.model.book.NumberContainsKeywordPredicate;
import seedu.address.ui.Mode;

/**
 * Searches for the stocking of the corresponding book.
 */
public class StockCommand extends Command {

    public static final String COMMAND_WORD = "stock";
    public static final String SUGGESTION = "stock n/<book name>\n" + "stock i/<isbn>\n"
            + "stock n/<book name> stock i/<isbn>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Search for the stocking of all the books with"
            + "the corresponding keyword and shows them as a list.\n"
            + "Parameters: [" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_ISBN + "ISBN]\n"
            + "Example: " + COMMAND_WORD + " " + CliSyntax.PREFIX_NAME + "a brief history of time";

    private Predicate<Book> predicate;

    /**
     * Creates a StockCommand to search for the stocking information in each location.
     *
     * @param names The list of names that are used as keyword.
     * @param numbers The list of numbers that are used as keyword.
     */
    public StockCommand(List<String> names, List<String> numbers) {
        NameMatchesKeywordPredicate nameMatchesKeywordsPredicate;
        NumberContainsKeywordPredicate numberContainsKeywordPredicate;
        if (names != null && !names.get(0).equals("") && numbers != null) {
            nameMatchesKeywordsPredicate = new NameMatchesKeywordPredicate(names);
            numberContainsKeywordPredicate = new NumberContainsKeywordPredicate(numbers);
            predicate = (book -> nameMatchesKeywordsPredicate.test(book)
                    || numberContainsKeywordPredicate.test(book));
        } else if (names != null && !names.get(0).equals("")) {
            predicate = new NameMatchesKeywordPredicate(names);
        } else if (numbers != null) {
            predicate = new NumberContainsKeywordPredicate(numbers);
        } else {
            predicate = Model.PREDICATE_SHOW_ALL_BOOKS;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredBookList((book -> false), Mode.NORMAL);
        model.updateFilteredBookList(predicate, Mode.DETAIL);
        return new CommandResult(String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW,
                model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof StockCommand
                    && this.predicate.equals(((StockCommand) other).predicate));
    }
}
