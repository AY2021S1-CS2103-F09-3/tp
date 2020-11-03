
package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AuthorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Author(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Author(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Author.isValidAuthor(null));

        // invalid name
        assertFalse(Author.isValidAuthor("")); // empty string
        assertFalse(Author.isValidAuthor(" ")); // spaces only
        assertFalse(Author.isValidAuthor("^")); // only non-alphanumeric characters
        assertFalse(Author.isValidAuthor("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Author.isValidAuthor("peter jack")); // alphabets only
        assertTrue(Author.isValidAuthor("12345")); // numbers only
        assertTrue(Author.isValidAuthor("peter the 2nd")); // alphanumeric characters
        assertTrue(Author.isValidAuthor("Capital Tan")); // with capital letters
        assertTrue(Author.isValidAuthor("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
