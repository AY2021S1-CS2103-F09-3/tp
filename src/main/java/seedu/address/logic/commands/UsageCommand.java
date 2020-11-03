package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_USAGE_BOOK_SUCCESS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;

/**
 * Check usage of a book identified using its displayed index from the list.
 */
public class UsageCommand extends Command {
    public static final String COMMAND_WORD = "usage";
    public static final String SUGGESTION = "usage <index>";
    private final Index targetIndex;

    /**
     * Constructor for UsageCommand.
     * @param targetIndex index from user input
     */
    public UsageCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Execute usage command on model and return with result.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a new CommandResult object
     * @throws CommandException if size is not legal
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Book> lastShownList = model.getFilteredBookList();
        int size = lastShownList.size();

        if (invalidSizeComparedTo(size)) {
            throw new CommandException(MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        return new CommandResult(String.format(MESSAGE_USAGE_BOOK_SUCCESS,
                getUsage(lastShownList.get(targetIndex.getZeroBased()))));
    }

    /**
     * Checks whether user input size is invalid compared to legel size.
     *
     * @param size upper limit size
     * @return boolean value indicating validity
     */
    private boolean invalidSizeComparedTo(int size) {
        return targetIndex.getZeroBased() >= size || targetIndex.getZeroBased() < 0;
    }

    /**
     * Returns borrowed times of a book.
     *
     * @param book the book we aim to check value
     * @return times value
     */
    private int getUsage(Book book) {
        return book.getTimes().getValue();
    }

    /**
     * Implements equals method.
     *
     * @param other the other object
     * @return boolean value, true for equal, false for unequal
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UsageCommand // instanceof handles nulls
                && targetIndex.equals(((UsageCommand) other).targetIndex)); // state check
    }
}
