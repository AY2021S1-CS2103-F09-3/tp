package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ISBN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PUBLISHER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STOCKING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMES;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Library;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.book.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditBookDescriptorBuilder;
import seedu.address.ui.Mode;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_ISBN_AMY = "11111111";
    public static final String VALID_ISBN_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TIMES_AMY = "23";
    public static final String VALID_TIMES_BOB = "2";
    public static final String VALID_CATEGORY_HUSBAND = "husband";
    public static final String VALID_CATEGORY_FRIEND = "friend";
    public static final String VALID_STOCKING_AMY = "centralLb 10 scienceLb 10";
    public static final String VALID_STOCKING_BOB = "centralLb 30 scienceLb 15";
    public static final String VALID_AUTHOR_BOB = "a";
    public static final String VALID_PUBLISHER_BOB = "pub";
    public static final String VALID_AUTHOR_AMY = "a";
    public static final String VALID_PUBLISHER_AMY = "pub";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String ISBN_DESC_AMY = " " + PREFIX_ISBN + VALID_ISBN_AMY;
    public static final String ISBN_DESC_BOB = " " + PREFIX_ISBN + VALID_ISBN_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TIMES_DESC_AMY = " " + PREFIX_TIMES + VALID_TIMES_AMY;
    public static final String TIMES_DESC_BOB = " " + PREFIX_TIMES + VALID_TIMES_BOB;
    public static final String CATEGORY_DESC_FRIEND = " " + PREFIX_CATEGORY + VALID_CATEGORY_FRIEND;
    public static final String CATEGORY_DESC_HUSBAND = " " + PREFIX_CATEGORY + VALID_CATEGORY_HUSBAND;
    public static final String STOCKING_DESC_AMY = " " + PREFIX_STOCKING + VALID_STOCKING_AMY;
    public static final String STOCKING_DESC_BOB = " " + PREFIX_STOCKING + VALID_STOCKING_BOB;
    public static final String AUTHOR_DESC_BOB = " " + PREFIX_AUTHOR + VALID_AUTHOR_BOB;
    public static final String PUBLISHER_DESC_BOB = " " + PREFIX_PUBLISHER + VALID_PUBLISHER_BOB;
    public static final String AUTHOR_DESC_AMY = " " + PREFIX_AUTHOR + VALID_AUTHOR_AMY;
    public static final String PUBLISHER_DESC_AMY = " " + PREFIX_PUBLISHER + VALID_PUBLISHER_AMY;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_ISBN_DESC = " " + PREFIX_ISBN + "911a"; // 'a' not allowed in isbns
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + "hubby*"; // '*' not allowed in
    // categories
    public static final String INVALID_STOCKING_DESC = " " + PREFIX_STOCKING + "central library: 20 science library:10";
    public static final String INVALID_TIMES_DESC = " " + PREFIX_TIMES + "03282";
    public static final String INVALID_AUTHOR_DESC = " " + PREFIX_AUTHOR + "james&&";
    public static final String INVALID_PUBLISHER_DESC = " " + PREFIX_PUBLISHER + "david&&";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditBookDescriptor DESC_AMY;
    public static final EditCommand.EditBookDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditBookDescriptorBuilder().withName(VALID_NAME_AMY).withIsbn(VALID_ISBN_AMY)
                .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withCategories(VALID_CATEGORY_FRIEND)
                .withTimes(VALID_TIMES_AMY).withStockings(VALID_STOCKING_AMY).withAuthor(VALID_AUTHOR_AMY)
                .withPublisher(VALID_PUBLISHER_AMY).build();
        DESC_BOB = new EditBookDescriptorBuilder().withName(VALID_NAME_BOB).withIsbn(VALID_ISBN_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withStockings(VALID_STOCKING_BOB)
                .withTimes(VALID_TIMES_BOB).withPublisher(VALID_PUBLISHER_BOB)
                .withCategories(VALID_CATEGORY_HUSBAND, VALID_CATEGORY_FRIEND).withAuthor(VALID_AUTHOR_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult}
     * <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException | IOException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the library, filtered book list and selected book in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Library expectedLibrary = new Library(actualModel.getLibrary());
        List<Book> expectedFilteredList = new ArrayList<>(actualModel.getFilteredBookList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLibrary, actualModel.getLibrary());
        assertEquals(expectedFilteredList, actualModel.getFilteredBookList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the book at the given {@code targetIndex} in the
     * {@code model}'s library.
     */
    public static void showBookAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBookList().size());

        Book book = model.getFilteredBookList().get(targetIndex.getZeroBased());
        final String[] splitName = book.getName().fullName.split("\\s+");
        model.updateFilteredBookList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])), Mode.NORMAL);

        assertEquals(1, model.getFilteredBookList().size());
    }

}
