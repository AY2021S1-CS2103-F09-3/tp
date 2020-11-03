package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.category.Category;
import seedu.address.ui.Mode;



/**
 * Finds and lists all books in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String SUGGESTION = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort the list of books"
            + "of a certain category by their popularity.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " math";

    private final String category;

    public SortCommand(String category) {
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();
        Comparator<Integer> comparator = (o1, o2) -> o2 - o1;
        int maxTime = -1;
        for (Book i : lastShownList) {
            if (i.getCategories().contains(new Category(category))) {
                if (Integer.parseInt(i.getTimes().value) > maxTime) {
                    maxTime = Integer.parseInt(i.getTimes().value);
                }
            }
        }
        int finalMaxTime = maxTime;
        Predicate<Book> predicate = new Predicate<Book>() {
            @Override
            public boolean test(Book book) {
                return book.getCategories().contains(new Category(category))
                        && (Integer.parseInt(book.getTimes().value) == finalMaxTime);
            }
        };
        model.updateFilteredBookList(predicate, Mode.NORMAL);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW, model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && category.equals(((SortCommand) other).category)); // state check
    }
}
