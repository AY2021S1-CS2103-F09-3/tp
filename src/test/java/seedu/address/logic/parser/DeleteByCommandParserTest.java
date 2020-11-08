package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteByCommand;


class DeleteByCommandParserTest {

    private DeleteByCommandParser parser = new DeleteByCommandParser();

    @Test
    void parse_validArgs_returnsStockCommand() {
        assertParseSuccess(parser, NAME_DESC_BOOK2, new DeleteByCommand(VALID_NAME_BOOK2, 0));
    }
}
