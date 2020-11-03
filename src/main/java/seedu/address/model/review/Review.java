package seedu.address.model.review;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Book's review in the address book.
 */
public class Review {

    private final Rating rating;
    private final ReviewContent reviewContent;

    /**
     * Constructs an {@code Review}.
     *
     * @param rating A valid rating.
     * @param content The review content.
     */
    public Review(Rating rating, ReviewContent content) {
        requireNonNull(rating);
        requireNonNull(content);
        this.rating = rating;
        this.reviewContent = content;
    }


    public Rating getRating() {
        return rating;
    }

    public ReviewContent getContent() {
        return reviewContent;
    }

    @Override
    public String toString() {
        return "rating: " + this.rating.ratingNumber + " content: " + this.reviewContent.content;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Review // instanceof handles nulls
                && rating.equals(((Review) other).rating))
                && reviewContent.equals(((Review) other).reviewContent); // state check
    }

    @Override
    public int hashCode() {
        return rating.hashCode();
    }
}
