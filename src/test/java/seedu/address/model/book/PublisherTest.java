
package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PublisherTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Publisher(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Publisher(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Publisher.isValidPublisher(null));

        // invalid name
        assertFalse(Publisher.isValidPublisher("")); // empty string
        assertFalse(Publisher.isValidPublisher(" ")); // spaces only
        assertFalse(Publisher.isValidPublisher("^")); // only non-alphanumeric characters
        assertFalse(Publisher.isValidPublisher("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Publisher.isValidPublisher("pku")); // alphabets only
        assertTrue(Publisher.isValidPublisher("12345")); // numbers only
        assertTrue(Publisher.isValidPublisher("pku 2nd")); // alphanumeric characters
        assertTrue(Publisher.isValidPublisher("PKU")); // with capital letters
        assertTrue(Publisher.isValidPublisher("PKU THU NUS")); // long names
    }
}
