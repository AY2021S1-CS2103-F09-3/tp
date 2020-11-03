package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STOCKING_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STOCKING_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Library;
import seedu.address.model.book.Book;

/**
 * A utility class containing a list of {@code Book} objects to be used in tests.
 */
public class TypicalBooks {
    public static final Book ALICE = new BookBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com").withReviews()
            .withIsbn("94351253").withTimes("12")
            .withCategories("friends").withAuthor("a")
            .withPublisher("pub").withStocking("centralLb 10 scienceLb 10").build();
    public static final Book BENSON = new BookBuilder().withName("Benson Meier").withReviews()
            .withAddress("311, Clementi Ave 2, #02-25").withTimes("20")
            .withEmail("johnd@example.com").withIsbn("98765432")
            .withCategories("owesMoney", "friends").withAuthor("a")
            .withPublisher("pub").withStocking("centralLb 10 scienceLb 10").build();
    public static final Book CARL = new BookBuilder().withName("Carl Kurz").withIsbn("95352563")
            .withEmail("heinz@example.com").withReviews().withTimes("20")
            .withAddress("wall street").withAuthor("a")
            .withPublisher("pub").withStocking("centralLb 10 scienceLb 10").build();
    public static final Book DANIEL = new BookBuilder().withName("Daniel Meier").withIsbn("87652533")
            .withEmail("cornelia@example.com").withReviews().withAddress("10th street").withCategories("friends")
            .withAuthor("a").withPublisher("pub").withTimes("20")
            .withStocking("centralLb 10 scienceLb 10").build();
    public static final Book ELLE = new BookBuilder().withName("Elle Meyer").withIsbn("9482224")
            .withEmail("werner@example.com").withReviews().withTimes("20")
            .withAddress("michegan ave").withAuthor("a")
            .withPublisher("pub").withStocking("centralLb 10 scienceLb 10").build();
    public static final Book FIONA = new BookBuilder().withName("Fiona Kunz").withIsbn("9482427")
            .withEmail("lydia@example.com").withReviews().withTimes("20")
            .withAddress("little tokyo").withAuthor("a")
            .withPublisher("pub").withStocking("centralLb 10 scienceLb 10").build();
    public static final Book GEORGE = new BookBuilder().withName("George Best").withIsbn("9482442")
            .withEmail("anna@example.com").withReviews().withTimes("20")
            .withAddress("4th street").withAuthor("a")
            .withPublisher("pub").withStocking("centralLb 10 scienceLb 10").build();

    // all newly added attributes need to be implemented here.
    // Manually added
    public static final Book HOON = new BookBuilder().withName("Hoon Meier").withIsbn("8482424")
            .withEmail("stefan@example.com").withReviews().withAddress("little india").withTimes("12")
            .withPublisher("pub").build();
    public static final Book IDA = new BookBuilder().withName("Ida Mueller").withIsbn("8482131")
            .withEmail("hans@example.com").withReviews().withAddress("chicago ave").withTimes("12")
            .withPublisher("pub").build();

    // Manually added - Book's details found in {@code CommandTestUtil}
    public static final Book AMY = new BookBuilder().withName(VALID_NAME_AMY).withIsbn(VALID_ISBN_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withCategories(VALID_CATEGORY_FRIEND)
            .withTimes("12").withStocking(VALID_STOCKING_AMY).withAuthor("a").withPublisher("pub").build();
    public static final Book BOB = new BookBuilder().withName(VALID_NAME_BOB).withIsbn(VALID_ISBN_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withAuthor("a").withPublisher("pub")
            .withTimes("12").withCategories(VALID_CATEGORY_HUSBAND, VALID_CATEGORY_FRIEND)
            .withStocking(VALID_STOCKING_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalBooks() {} // prevents instantiation

    /**
     * Returns an {@code Library} with all the typical books.
     */
    public static Library getTypicalLibrary() {
        Library lib = new Library();
        for (Book book : getTypicalBooks()) {
            lib.addBook(book);
        }
        return lib;
    }

    public static List<Book> getTypicalBooks() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
