package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.review.Rating;
import seedu.address.model.review.Review;
import seedu.address.model.review.ReviewContent;

public class JsonAdaptedReview {
    private final int ratingNumber;
    private final String reviewContent;

    /**
     * Constructs a {@code JsonAdaptedReview} with the given review information.
     */
    @JsonCreator
    public JsonAdaptedReview(@JsonProperty("ratingNumber") int ratingNumber,
                               @JsonProperty("reviewContent") String reviewContent) {
        this.ratingNumber = ratingNumber;
        this.reviewContent = reviewContent;
    }

    /**
     * Converts a given {@code Stocking} into this class for Jackson use.
     */
    public JsonAdaptedReview(Review review) {
        if (review == null) {
            this.ratingNumber = 0;
            this.reviewContent = "empty review content";
        } else {
            this.ratingNumber = review.getRating().ratingNumber;
            this.reviewContent = review.getContent().content;
        }
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Review} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted review.
     */
    public Review toModelType() throws IllegalValueException {
        if (!Rating.isValidRating(String.valueOf(this.ratingNumber))) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        } else if (!ReviewContent.isValidContent(reviewContent)) {
            throw new IllegalValueException(ReviewContent.MESSAGE_CONSTRAINTS);
        }

        Rating bookRating = new Rating(this.ratingNumber);
        ReviewContent bookReviewContent = new ReviewContent(this.reviewContent);

        return new Review(bookRating, bookReviewContent);
    }
}
