package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BOOKS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.book.Times;
import seedu.address.ui.Mode;

/**
 * Changes the times of an existing book in the database.
 */
public class TimesCommand extends Command {
    public static final String COMMAND_WORD = "times";
    public static final String SUGGESTION = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the times of the book identified "
            + "by the index number used in the last book listing. "
            + "Existing times will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TIMES + "[TIMES]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TIMES + "Likes to swim.";

    public static final String MESSAGE_ADD_TIMES_SUCCESS = "Added times to Book: %1$s";
    public static final String MESSAGE_DELETE_TIMES_SUCCESS = "Removed times from Book: %1$s";

    private final Index index;
    private final Times times;
    /**
     * @param index of the book in the filtered book list to edit the times
     * @param times of the book being borrowed to be updated to
     */
    public TimesCommand(Index index, Times times) {
        requireAllNonNull(index, times);
        this.index = index;
        this.times = times;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToEdit = lastShownList.get(index.getZeroBased());
        Book editedBook = new Book(bookToEdit.getName(), bookToEdit.getIsbn(), bookToEdit.getEmail(),
                bookToEdit.getAddress(), times, bookToEdit.getCategories(), bookToEdit.getStocking(),
                bookToEdit.getReviews(), bookToEdit.getAuthor(), bookToEdit.getPublisher());

        model.setBook(bookToEdit, editedBook);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS, Mode.NORMAL);

        return new CommandResult(generateSuccessMessage(editedBook));
    }

    /**
     * Generates a command execution success message based on whether the times is added to or removed from
     * {@code bookToEdit}.
     */
    private String generateSuccessMessage(Book bookToEdit) {
        String message = !times.value.isEmpty() ? MESSAGE_ADD_TIMES_SUCCESS : MESSAGE_DELETE_TIMES_SUCCESS;
        return String.format(message, bookToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof TimesCommand)) {
            return false;
        }
        // state check
        TimesCommand e = (TimesCommand) other;
        return index.equals(e.index)
                && times.equals(e.times);
    }
}
