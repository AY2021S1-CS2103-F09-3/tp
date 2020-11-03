package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMES;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;

/**
 * Deletes a book identified using name, isbn, or times.
 */
public class DeleteByCommand extends Command {

    public static final String COMMAND_WORD = "deleteBy";
    public static final String SUGGESTION = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the book identified by the name, isbn or times borrowed.\n"
            + "Parameters: NAME OR ISBN OR TIMES\n"
            + "Example: " + "1. " + COMMAND_WORD + " " + PREFIX_NAME + "Linear Algebra" + "   "
            + "2. " + COMMAND_WORD + " " + PREFIX_ISBN + "123456" + "   "
            + "3. " + COMMAND_WORD + " " + PREFIX_TIMES + "10";

    public static final String MESSAGE_DELETE_BOOK_SUCCESS = "Deleted Book: %1$s";

    private final String target;
    private final int attribute;

    /**
     * Delete a book by name, isbn or times.
     * @param target a string representing the input content.
     * @param attribute indicating which attribute shall we refer to when deleting.
     */
    public DeleteByCommand(String target, int attribute) {
        this.target = target;
        this.attribute = attribute;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();
        Book bookToDelete = null;
        List<Book> deleteList = new ArrayList<>();

        for (Book book : lastShownList) {
            switch (attribute) {
            case 0:
                if (book.getName().fullName.equals(target)) {
                    bookToDelete = book;
                    deleteList.add(bookToDelete);
                }
                break;
            case 1:
                if (book.getIsbn().value.equals(target)) {
                    bookToDelete = book;
                    deleteList.add(bookToDelete);
                }
                break;
            default:
                if (Integer.parseInt(book.getTimes().value) >= Integer.parseInt(target)) {
                    bookToDelete = book;
                    deleteList.add(bookToDelete);
                }
                break;
            }
        }

        if (bookToDelete == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DELETE_NAME);
        }

        for (Book book : deleteList) {
            model.deleteBook(book);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_BOOK_SUCCESS, deleteList.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteByCommand // instanceof handles nulls
                && target.equals(((DeleteByCommand) other).target)); // state check
    }

}
