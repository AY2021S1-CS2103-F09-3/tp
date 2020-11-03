package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.book.Book;

/**
 * An UI component that displays information of a {@code Book} with book cover.
 */
public class BookCardWithCover extends UiPart<Region> {

    private static final String FXML = "BookListCardWithCover.fxml";
    private static final BookCoverManager BOOK_COVER_MANAGER = new BookCoverManager();

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Book book;

    private final Logger logger = LogsCenter.getLogger(getClass());

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label isbn;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label times;
    @FXML
    private FlowPane categories;
    @FXML
    private Label author;
    @FXML
    private Label publisher;
    @FXML
    private ImageView cover;

    /**
     * Creates a {@code BookCardWithCover} with the given {@code Book} and index to display.
     *
     * @param book The corresponding book.
     * @param displayedIndex The number corresponding to the order of the book.
     */
    public BookCardWithCover(Book book, int displayedIndex) {
        super(FXML);
        this.book = book;
        id.setText(displayedIndex + ". ");
        name.setText(book.getName().fullName);
        isbn.setText("ISBN              " + book.getIsbn().value);
        address.setText("LANGUAGE   " + book.getAddress().value);
        email.setText("CONTACT     " + book.getEmail().value);
        times.setText("NUMBER OF TIMES BORROWED   " + book.getTimes().value);
        book.getCategories().stream()
                .sorted(Comparator.comparing(category -> category.categoryName))
                .forEach(category -> categories.getChildren().add(new Label(category.categoryName)));
        author.setText("AUTHOR       " + book.getAuthor().author);
        publisher.setText("PUBLISHER   " + book.getPublisher().publisher);
        logger.info("Get the book cover");
        cover.setImage(BOOK_COVER_MANAGER.getCategoryBookCover(book.getName().fullName, book.getCategories()));
        cover.setPreserveRatio(false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookCardWithCover)) {
            return false;
        }

        // state check
        BookCardWithCover card = (BookCardWithCover) other;
        return id.getText().equals(card.id.getText())
                && book.equals(card.book);
    }
}
