package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
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
public class RandomCommand extends Command {

    public static final String COMMAND_WORD = "random";
    public static final String SUGGESTION = "random <keywords>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort the list of books"
            + "of a certain category by their popularity.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " math";

    private final String category;

    public RandomCommand(String category) {
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();
        Comparator<Integer> comparator = (o1, o2) -> o2 - o1;
        Random random = new Random();
        int count = 0;
        for (Book i : lastShownList) {
            if (i.getCategories().contains(new Category(category))) {
                count++;
            }
        }
        Predicate<Book> predicate;
        if (count == 0) {
            predicate = new Predicate<Book>() {
                @Override
                public boolean test(Book book) {
                    return false;
                }
            };
        } else {
            int randomnum = random.nextInt(count);
            Book randomBook = lastShownList.get(randomnum);
            predicate = new Predicate<Book>() {
                @Override
                public boolean test(Book book) {
                    return book.equals(randomBook);
                }
            };
        }

        model.updateFilteredBookList(predicate, Mode.NORMAL);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW, model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RandomCommand // instanceof handles nulls
                && category.equals(((RandomCommand) other).category)); // state check
    }
}
