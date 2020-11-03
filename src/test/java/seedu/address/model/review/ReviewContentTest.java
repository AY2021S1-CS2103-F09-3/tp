package seedu.address.model.review;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReviewContentTest {

    @Test
    void isValidContent() {
        Assertions.assertTrue(ReviewContent.isValidContent("The book is really interesting"));
        Assertions.assertFalse(ReviewContent.isValidContent(""));
    }
}
