package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.ALICE;
import static seedu.address.testutil.TypicalBooks.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BookBuilder;

public class BookTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Book book = new BookBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> book.getCategories().remove(0));
    }

    @Test
    public void isSameBook() {
        // same object -> returns true
        assertTrue(ALICE.isSameBook(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameBook(null));

        // different isbn and email -> returns false
        Book editedAlice = new BookBuilder(ALICE).withIsbn(VALID_ISBN_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameBook(editedAlice));

        // different name -> returns false
        editedAlice = new BookBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameBook(editedAlice));

        // same name, same isbn, different attributes -> returns true
        editedAlice = new BookBuilder(ALICE).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(ALICE.isSameBook(editedAlice));

        // same name, same email, different attributes -> returns true
        editedAlice = new BookBuilder(ALICE).withIsbn(VALID_ISBN_BOB).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(ALICE.isSameBook(editedAlice));

        // same name, same isbn, same email, different attributes -> returns true
        editedAlice = new BookBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND).build();
        assertTrue(ALICE.isSameBook(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Book aliceCopy = new BookBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different book -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Book editedAlice = new BookBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different isbn -> returns false
        editedAlice = new BookBuilder(ALICE).withIsbn(VALID_ISBN_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new BookBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new BookBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different categories -> returns false
        editedAlice = new BookBuilder(ALICE).withCategories(VALID_CATEGORY_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
