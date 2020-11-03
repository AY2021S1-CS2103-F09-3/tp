package seedu.address.model.review;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReviewNumberTest {

    @Test
    void isValidReviewNumber() {
        Assertions.assertTrue(ReviewNumber.isValidReviewNumber("6"));
        Assertions.assertFalse(ReviewNumber.isValidReviewNumber("a"));
    }
}
