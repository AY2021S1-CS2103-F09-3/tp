package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBookAtIndex;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Library;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.model.book.Times;
import seedu.address.testutil.BookBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TimesCommand.
 */
public class TimesCommandTest {

    private static final String TIMES_STUB = "12345";

    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        Book firstBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        Book editedBook = new BookBuilder(firstBook).withTimes(TIMES_STUB).build();

        TimesCommand remarkCommand = new TimesCommand(INDEX_FIRST_BOOK, new Times(editedBook.getTimes().value));

        String expectedMessage = String.format(TimesCommand.MESSAGE_ADD_TIMES_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(firstBook, editedBook);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);

        Book firstBook = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        Book editedBook = new BookBuilder(model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased()))
                .withTimes(TIMES_STUB).build();

        TimesCommand remarkCommand = new TimesCommand(INDEX_FIRST_BOOK, new Times(editedBook.getTimes().value));

        String expectedMessage = String.format(TimesCommand.MESSAGE_ADD_TIMES_SUCCESS, editedBook);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setBook(firstBook, editedBook);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidBookIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        TimesCommand remarkCommand = new TimesCommand(outOfBoundIndex, new Times(VALID_TIMES_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidBookIndexFilteredList_failure() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);
        Index outOfBoundIndex = INDEX_SECOND_BOOK;
        // ensures that outOfBoundIndex is still in bounds of library list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibrary().getBookList().size());

        TimesCommand remarkCommand = new TimesCommand(outOfBoundIndex, new Times(VALID_TIMES_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final TimesCommand standardCommand = new TimesCommand(INDEX_FIRST_BOOK, new Times(VALID_TIMES_AMY));

        // same values -> returns true
        TimesCommand commandWithSameValues = new TimesCommand(INDEX_FIRST_BOOK, new Times (VALID_TIMES_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TimesCommand(INDEX_SECOND_BOOK, new Times(VALID_TIMES_AMY))));

        // different times -> returns false
        assertFalse(standardCommand.equals(new TimesCommand(INDEX_FIRST_BOOK, new Times(VALID_TIMES_BOB))));
    }
}
