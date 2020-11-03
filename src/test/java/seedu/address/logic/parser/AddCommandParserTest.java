package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.AUTHOR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.AUTHOR_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AUTHOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ISBN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PUBLISHER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STOCKING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIMES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.ISBN_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ISBN_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PUBLISHER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PUBLISHER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.STOCKING_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STOCKING_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TIMES_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TIMES_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AUTHOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PUBLISHER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STOCKING_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMES_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalBooks.AMY;
import static seedu.address.testutil.TypicalBooks.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.book.Address;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Name;
import seedu.address.model.book.Publisher;
import seedu.address.model.book.Stocking;
import seedu.address.model.book.Times;
import seedu.address.model.category.Category;
import seedu.address.testutil.BookBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Book expectedBook = new BookBuilder(BOB).withCategories(VALID_CATEGORY_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB + TIMES_DESC_BOB + AUTHOR_DESC_BOB
                + PUBLISHER_DESC_BOB,
                new AddCommand(expectedBook));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CATEGORY_DESC_FRIEND + TIMES_DESC_BOB + STOCKING_DESC_BOB + AUTHOR_DESC_BOB
                + PUBLISHER_DESC_BOB,
                new AddCommand(expectedBook));

        // multiple isbns - last isbn accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ISBN_DESC_AMY + ISBN_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CATEGORY_DESC_FRIEND + TIMES_DESC_BOB + STOCKING_DESC_BOB + AUTHOR_DESC_BOB
                + PUBLISHER_DESC_BOB,
                new AddCommand(expectedBook));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + CATEGORY_DESC_FRIEND + TIMES_DESC_BOB + STOCKING_DESC_BOB + AUTHOR_DESC_BOB
                + PUBLISHER_DESC_BOB,
                new AddCommand(expectedBook));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + CATEGORY_DESC_FRIEND + TIMES_DESC_BOB + STOCKING_DESC_BOB + AUTHOR_DESC_BOB
                + PUBLISHER_DESC_AMY,
                new AddCommand(expectedBook));

        // multiple categories - all accepted
        Book expectedBookMultipleCategories = new BookBuilder(BOB).withCategories(VALID_CATEGORY_FRIEND,
                VALID_CATEGORY_HUSBAND).build();
        assertParseSuccess(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TIMES_DESC_BOB + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB
                        + AUTHOR_DESC_BOB + PUBLISHER_DESC_BOB, new AddCommand(expectedBookMultipleCategories));

        //multiple stocking - last stocking accepted
        assertParseSuccess(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                        + ADDRESS_DESC_BOB + TIMES_DESC_BOB + CATEGORY_DESC_FRIEND + STOCKING_DESC_AMY
                        + STOCKING_DESC_BOB + AUTHOR_DESC_BOB + PUBLISHER_DESC_BOB, new AddCommand(expectedBook));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero categories
        Book expectedBook = new BookBuilder(AMY).withCategories().build();
        assertParseSuccess(parser, NAME_DESC_AMY + ISBN_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + TIMES_DESC_AMY + STOCKING_DESC_AMY + AUTHOR_DESC_AMY + PUBLISHER_DESC_AMY,
                new AddCommand(expectedBook));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TIMES_DESC_BOB + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB
                        + AUTHOR_DESC_BOB + PUBLISHER_DESC_BOB,
                expectedMessage);

        // missing isbn prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_ISBN_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + TIMES_DESC_BOB + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB
                        + AUTHOR_DESC_BOB + PUBLISHER_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + ISBN_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                        + TIMES_DESC_BOB + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB
                        + AUTHOR_DESC_BOB + PUBLISHER_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                        + TIMES_DESC_BOB + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB
                        + AUTHOR_DESC_BOB + PUBLISHER_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_ISBN_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                        + VALID_TIMES_BOB + CATEGORY_DESC_FRIEND + VALID_STOCKING_BOB
                        + VALID_AUTHOR_BOB + VALID_PUBLISHER_BOB,
                expectedMessage);
    }

    // new attributes need to be added here
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + ISBN_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TIMES_DESC_BOB + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB + AUTHOR_DESC_BOB
                + PUBLISHER_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid isbn
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_ISBN_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TIMES_DESC_BOB + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB + AUTHOR_DESC_BOB
                + PUBLISHER_DESC_BOB,
                Isbn.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + ISBN_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TIMES_DESC_BOB + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB + AUTHOR_DESC_BOB
                + PUBLISHER_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TIMES_DESC_BOB + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB + AUTHOR_DESC_BOB
                + PUBLISHER_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid category
        assertParseFailure(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TIMES_DESC_BOB + INVALID_CATEGORY_DESC + VALID_CATEGORY_FRIEND + STOCKING_DESC_BOB + AUTHOR_DESC_BOB
                + PUBLISHER_DESC_BOB,
                Category.MESSAGE_CONSTRAINTS);

        // invalid stocking
        assertParseFailure(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TIMES_DESC_BOB + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + INVALID_STOCKING_DESC
                + AUTHOR_DESC_BOB + PUBLISHER_DESC_BOB, Stocking.MESSAGE_CONSTRAINTS);

        // invalid author
        assertParseFailure(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TIMES_DESC_BOB + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB
                + INVALID_AUTHOR_DESC
                + PUBLISHER_DESC_BOB, Author.MESSAGE_CONSTRAINTS);

        // invalid publisher
        assertParseFailure(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TIMES_DESC_BOB + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB
                + AUTHOR_DESC_BOB + INVALID_PUBLISHER_DESC, Publisher.MESSAGE_CONSTRAINTS);

        // invalid times
        assertParseFailure(parser, NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_TIMES_DESC + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB
                + AUTHOR_DESC_BOB + PUBLISHER_DESC_BOB, Times.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + ISBN_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + TIMES_DESC_BOB + STOCKING_DESC_BOB + AUTHOR_DESC_BOB + PUBLISHER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + ISBN_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TIMES_DESC_BOB + CATEGORY_DESC_HUSBAND + CATEGORY_DESC_FRIEND + STOCKING_DESC_BOB
                        + AUTHOR_DESC_BOB + PUBLISHER_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
