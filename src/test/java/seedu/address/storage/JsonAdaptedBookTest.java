package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedBook.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.book.Address;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Name;


public class JsonAdaptedBookTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_ISBN = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CATEGORY = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ISBN = BENSON.getIsbn().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_TIMES = BENSON.getTimes().toString();
    private static final List<JsonAdaptedCategory> VALID_CATEGORIES = BENSON.getCategories().stream()
            .map(JsonAdaptedCategory::new)
            .collect(Collectors.toList());
    private static final String VALID_AUTHOR = BENSON.getAuthor().toString();
    private static final String VALID_PUBLISHER = BENSON.getPublisher().toString();
    private static final JsonAdaptedStocking VALID_STOCKING = new JsonAdaptedStocking(BENSON.getStocking());
    private static final List<JsonAdaptedReview> VALID_REVIEWS = BENSON.getReviews().stream()
            .map(JsonAdaptedReview::new)
            .collect(Collectors.toList());

    /* @Test
    public void toModelType_validBookDetails_returnsBook() throws Exception {
        JsonAdaptedBook book = new JsonAdaptedBook(BENSON);
        assertEquals(BENSON, book.toModelType());
    } // storage parser */

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(INVALID_NAME, VALID_ISBN, VALID_EMAIL, VALID_ADDRESS, VALID_TIMES,
                        VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(null, VALID_ISBN, VALID_EMAIL, VALID_ADDRESS,
                VALID_TIMES, VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidIsbn_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_NAME, INVALID_ISBN, VALID_EMAIL, VALID_ADDRESS, VALID_TIMES,
                        VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = Isbn.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullIsbn_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_TIMES, VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Isbn.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_NAME, VALID_ISBN, INVALID_EMAIL, VALID_ADDRESS, VALID_TIMES,
                        VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_NAME, VALID_ISBN, null, VALID_ADDRESS,
                VALID_TIMES, VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_NAME, VALID_ISBN, VALID_EMAIL, INVALID_ADDRESS, VALID_TIMES,
                        VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(VALID_NAME, VALID_ISBN, VALID_EMAIL, null,
                VALID_TIMES, VALID_CATEGORIES, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidCategories_throwsIllegalValueException() {
        List<JsonAdaptedCategory> invalidCategories = new ArrayList<>(VALID_CATEGORIES);
        invalidCategories.add(new JsonAdaptedCategory(INVALID_CATEGORY));
        JsonAdaptedBook book =
                new JsonAdaptedBook(VALID_NAME, VALID_ISBN, VALID_EMAIL, VALID_ADDRESS,
                        VALID_TIMES, invalidCategories, VALID_STOCKING, VALID_REVIEWS, VALID_AUTHOR, VALID_PUBLISHER);
        assertThrows(IllegalValueException.class, book::toModelType);
    }

}
