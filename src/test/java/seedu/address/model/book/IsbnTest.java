package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IsbnTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Isbn(null));
    }

    @Test
    public void constructor_invalidIsbn_throwsIllegalArgumentException() {
        String invalidIsbn = "";
        assertThrows(IllegalArgumentException.class, () -> new Isbn(invalidIsbn));
    }

    @Test
    public void isValidIsbn() {
        // null isbn number
        assertThrows(NullPointerException.class, () -> Isbn.isValidIsbn(null));

        // invalid isbn numbers
        assertFalse(Isbn.isValidIsbn("")); // empty string
        assertFalse(Isbn.isValidIsbn(" ")); // spaces only
        assertFalse(Isbn.isValidIsbn("91")); // less than 3 numbers
        assertFalse(Isbn.isValidIsbn("isbn")); // non-numeric
        assertFalse(Isbn.isValidIsbn("9011p041")); // alphabets within digits
        assertFalse(Isbn.isValidIsbn("9312 1534")); // spaces within digits

        // valid isbn numbers
        assertTrue(Isbn.isValidIsbn("911")); // exactly 3 numbers
        assertTrue(Isbn.isValidIsbn("93121534"));
        assertTrue(Isbn.isValidIsbn("124293842033123")); // long isbn numbers
    }
}
