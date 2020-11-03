package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PUBLISHER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STOCKING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMES;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;

/**
 * Adds a book to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String SUGGESTION = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a book to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ISBN + "ISBN "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_CATEGORY + "CATEGORY]..."
            + PREFIX_TIMES + "TIMES "
            + PREFIX_STOCKING + "STOCKINGS "
            + PREFIX_AUTHOR + "AUTHOR "
            + PREFIX_PUBLISHER + "PUBLISHER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Linear Algebra "
            + PREFIX_ISBN + "98765432 "
            + PREFIX_EMAIL + "xxxxxx@example.com "
            + PREFIX_ADDRESS + "xxxxx "
            + PREFIX_CATEGORY + "Science "
            + PREFIX_CATEGORY + "Math "
            + PREFIX_TIMES + "20 "
            + PREFIX_STOCKING + "central library 0 science library 0 "
            + PREFIX_AUTHOR + "Victor "
            + PREFIX_PUBLISHER + "pku";

    public static final String MESSAGE_SUCCESS = "New book added: %1$s";
    public static final String MESSAGE_DUPLICATE_BOOK = "This book already exists in the address book";

    private final Book toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Book}
     */
    public AddCommand(Book book) {
        requireNonNull(book);
        toAdd = book;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasBook(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_BOOK);
        }

        model.addBook(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
