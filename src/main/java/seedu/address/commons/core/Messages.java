package seedu.address.commons.core;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_BOOK_DISPLAYED_INDEX = "The book index provided is invalid";
    public static final String MESSAGE_INVALID_REVIEW_DISPLAYED_INDEX = "The review index provided is invalid";
    public static final String MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW =
            "The review book index provided is invalid.\n"
            + "Note that only index of book shown in the current book list is valid.\n"
            + "To execute the command on the books not shown in the current book list, try to use "
            + "the list command to list all the books and search for "
            + "the corresponding book.";
    public static final String MESSAGE_INVALID_EDIT_REVIEW = "At least 1 of rating or review "
            + "content needs to be present.";
    public static final String MESSAGE_REVIEW_NOT_EDITED = "At least 1 of rating or review "
            + "content needs to be different.";
    public static final String MESSAGE_USAGE_BY = "usageBy"
            + ": Get the usage of the book identified by the name or isbn\n"
            + "Parameters: NAME or ISBN\n"
            + "Example: " + "1. " + "usageBy" + " " + PREFIX_NAME + "Linear Algebra" + "\n"
            + "2. " + "usageBy" + " " + PREFIX_ISBN + "1938431047490";
    public static final String MESSAGE_INVALID_BOOK_DELETE_NAME = "The book to delete is not found";
    public static final String MESSAGE_INVALID_BOOK_CHECK_NAME = "The book to check is not found";
    public static final String MESSAGE_BOOKS_LISTED_OVERVIEW = "%1$d books listed!";
    public static final String MESSAGE_REPORT_LISTED_OVERVIEW = "%1$d reports listed!";
    public static final String MESSAGE_BORROWING_TIMES_HISTORY = "All history borrowing times sum up to %1$d";
    public static final String MESSAGE_USAGE = "usage"
            + ": Usages the book identified by the index number used in the displayed book list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: usage 1";
    public static final String MESSAGE_USAGE_BOOK_SUCCESS = "Usage of selected book: %1$s";
}
