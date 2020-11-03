package seedu.address.model.review;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReviewTest {

    @Test
    void equals() {
        Rating rating = new Rating(5);
        ReviewContent reviewContent = new ReviewContent("The book is quite interesting");
        Rating newRating = new Rating(5);
        ReviewContent newReviewContent = new ReviewContent("The book is quite interesting");

        Review review = new Review(rating, reviewContent);
        Review newReview = new Review(newRating, newReviewContent);
        Review reviewWithDifferentReviewContent =
                new Review(rating, new ReviewContent("The book is really interesting"));
        Review reviewWithDifferentRating = new Review(new Rating(4), reviewContent);

        Assertions.assertTrue(review.equals(review));
        Assertions.assertTrue(review.equals(newReview));
        Assertions.assertFalse(review.equals(reviewWithDifferentRating));
        Assertions.assertFalse(review.equals(reviewWithDifferentReviewContent));
    }
}
