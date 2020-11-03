package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.book.Address;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Name;
import seedu.address.model.book.NameMatchesKeywordPredicate;
import seedu.address.model.book.Publisher;
import seedu.address.model.book.Stocking;
import seedu.address.model.book.Times;
import seedu.address.model.category.Category;
import seedu.address.model.review.Review;
import seedu.address.ui.Mode;

/**
 * Adds the review of the corresponding book.
 */
public class AddReviewCommand extends Command {

    public static final String COMMAND_WORD = "addReview";
    public static final String SUGGESTION = "addReview <index> ra/<rating> re/<review content>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add the review to the book at "
            + "the corresponding position in the list, where the rating is an integer between 0 and 5.\n"
            + "Parameters: "
            + "INDEX "
            + "[" + CliSyntax.PREFIX_RATING + "RATING] "
            + "[" + CliSyntax.PREFIX_REVIEW + "REVIEW_CONTENT]\n"
            + "Example: " + COMMAND_WORD + " 1 " + CliSyntax.PREFIX_RATING + "5" + " " + CliSyntax.PREFIX_REVIEW
            + "The book is interesting";

    public static final String MESSAGE_ADD_REVIEW_SUCCESS = "The review has been added to the book %1$s";

    private final Index index;
    private final Review review;

    /**
     * Creates a add review command to add the review of the corresponding book.
     *
     * @param index The index of the book in the list.
     * @param review The review of the book to add.
     */
    public AddReviewCommand(Index index, Review review) {
        requireNonNull(index);
        requireNonNull(review);

        this.index = index;
        this.review = review;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            requireNonNull(model);
            List<Book> lastShownList = model.getFilteredBookList();
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW);
            }
            Book bookToReview = lastShownList.get(index.getZeroBased());
            Book reviewedBook = createdChangedBook(bookToReview, review);

            model.setBook(bookToReview, reviewedBook);

            List<String> keywords = new ArrayList<>(Arrays.asList((reviewedBook.getName().fullName).split(" ")));

            NameMatchesKeywordPredicate nameMacthedKeywordsPredicate = new NameMatchesKeywordPredicate(keywords);
            model.updateFilteredBookList(nameMacthedKeywordsPredicate, Mode.REVIEW);

            return new CommandResult(String.format(MESSAGE_ADD_REVIEW_SUCCESS, reviewedBook));
        } catch (Exception exception) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW);
        }
    }

    /**
     * Creates the book with the new review added to the review list of the book.
     *
     * @param book The corresponding book.
     * @param review The review to add.
     * @return The book with the new review list.
     */
    private static Book createdChangedBook(Book book, Review review) {
        Name name = book.getName();
        Isbn isbn = book.getIsbn();
        Email email = book.getEmail();
        Address address = book.getAddress();
        List<Review> reviews = book.getReviews();
        reviews.add(review);
        Times times = book.getTimes();
        Set<Category> categories = book.getCategories();
        Author author = book.getAuthor();
        Publisher publisher = book.getPublisher();
        Stocking stocking = book.getStocking();

        return new Book(name, isbn, email, address, times, categories, stocking, reviews, author, publisher);
    }
}
