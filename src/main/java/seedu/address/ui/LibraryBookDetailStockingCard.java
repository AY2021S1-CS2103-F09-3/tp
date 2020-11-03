package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.book.Book;

/**
 * An UI component that displays detailed stocking information of a {@code Book}.
 */
public class LibraryBookDetailStockingCard extends UiPart<Region> {

    private static final String FXML = "LibraryBookDetailStockingCard.fxml";
    private static final BookCoverManager BOOK_COVER_MANAGER = new BookCoverManager();

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Book book;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label isbn;
    @FXML
    private Label author;
    @FXML
    private FlowPane categories;
    @FXML
    private FlowPane stocking;
    @FXML
    private ImageView cover;

    /**
     * Creates a {@code LibraryBookDetailCard} with the given {@code Book} and index to display.
     */
    public LibraryBookDetailStockingCard(Book book, int displayedIndex) {
        super(FXML);
        this.book = book;
        id.setText(displayedIndex + ". ");
        name.setText(book.getName().fullName);
        isbn.setText("ISBN " + book.getIsbn().value);
        book.getCategories().stream()
                .sorted(Comparator.comparing(category -> category.categoryName))
                .forEach(category -> categories.getChildren().add(new Label(category.categoryName)));
        book.getStocking().storage.forEach((location, storage) -> {
            if (storage > 0) {
                stocking.getChildren().add(new Label(location.substring(0, location.length() - 2)
                        + " library: " + storage + " "));
            } else {
                stocking.getChildren().add(new Label(location.substring(0, location.length() - 2)
                        + " library: " + "Not available"));
            }
        });
        author.setText("Author " + book.getAuthor().author);
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
        if (!(other instanceof LibraryBookDetailStockingCard)) {
            return false;
        }

        // state check
        LibraryBookDetailStockingCard card = (LibraryBookDetailStockingCard) other;
        return id.getText().equals(card.id.getText())
                && book.equals(card.book);
    }
}
