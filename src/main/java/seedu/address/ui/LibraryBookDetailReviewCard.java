package seedu.address.ui;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.book.Book;
import seedu.address.model.review.Review;

public class LibraryBookDetailReviewCard extends UiPart<Region> {
    private static final String FXML = "LibraryBookDetailReviewCard.fxml";
    private static final BookCoverManager BOOK_COVER_MANAGER = new BookCoverManager();

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Book book;

    @FXML
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
    private FlowPane reviews;
    @FXML
    private ImageView cover;

    /**
     * Creates a {@code BookCode} with the given {@code Book} and index to display.
     */
    public LibraryBookDetailReviewCard(Book book, int displayedIndex) {
        super(FXML);
        this.book = book;
        id.setText(displayedIndex + ". ");
        name.setText(book.getName().fullName);
        isbn.setText("isbn: " + book.getIsbn().value);
        book.getCategories().stream()
                .sorted(Comparator.comparing(category -> category.categoryName))
                .forEach(category -> categories.getChildren().add(new Label(category.categoryName)));
        author.setText("author: " + book.getAuthor().author);

        List<Review> reviewList = book.getReviews().stream()
                .sorted(Comparator.comparing(review -> review.getContent().content))
                .collect(Collectors.toList());

        for (int i = 0; i < reviewList.size(); i = i + 1) {
            BookReviewCard bookReviewCard = new BookReviewCard(reviewList.get(i), i + 1);
            Separator separator = new Separator(Orientation.HORIZONTAL);
            reviews.getChildren().add(separator);
            reviews.getChildren().add(bookReviewCard.getRoot());
        }

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
        if (!(other instanceof LibraryBookDetailReviewCard)) {
            return false;
        }

        // state check
        LibraryBookDetailReviewCard card = (LibraryBookDetailReviewCard) other;
        return id.getText().equals(card.id.getText())
                && book.equals(card.book);
    }
}
