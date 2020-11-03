package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;

/**
 * Check usage of a book identified using name, isbn, or times.
 */
public class UsageByCommand extends Command {

    public static final String COMMAND_WORD = "usageBy";
    public static final String SUGGESTION = "usageBy n/<book name>\n" + "usageBy i/<isbn>";

    public static final String MESSAGE_USAGE_BOOK_SUCCESS = "Usage of selected book: %1$s";

    private final String target;

    /**
     * Check usage of a book by name or isbn.
     * @param target a string representing the input content.
     */
    public UsageByCommand(String target) {
        this.target = target;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();
        Book bookToCheck = null;

        for (Book book : lastShownList) {
            if (book.getName().fullName.equals(target) || book.getIsbn().value.equals(target)) {
                bookToCheck = book;
                break;
            }
        }

        if (bookToCheck == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_CHECK_NAME);
        }

        return new CommandResult(String.format(MESSAGE_USAGE_BOOK_SUCCESS, bookToCheck.getTimes().getValue()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UsageByCommand // instanceof handles nulls
                && target.equals(((UsageByCommand) other).target)); // state check
    }

}
