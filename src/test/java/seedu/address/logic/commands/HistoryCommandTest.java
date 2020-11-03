package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_BORROWING_TIMES_HISTORY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.EmptyList.getEmptyLibrary;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


class HistoryCommandTest {

    private Model model;
    private Model expectedModel;

    @Test
    void execute_listOfBooks_success() {
        model = new ModelManager(getTypicalLibrary(), new UserPrefs());
        expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        HistoryCommand historyCommand = new HistoryCommand();
        assertCommandSuccess(historyCommand, model, String.format(MESSAGE_BORROWING_TIMES_HISTORY, 132), expectedModel);
    }

    @Test
    void execute_emptyList_success() {
        model = new ModelManager(getEmptyLibrary(), new UserPrefs());
        expectedModel = new ModelManager(model.getLibrary(), new UserPrefs());
        HistoryCommand historyCommand = new HistoryCommand();
        assertCommandSuccess(historyCommand, model, String.format(MESSAGE_BORROWING_TIMES_HISTORY, 0), expectedModel);
    }

    @Test
    public void equals_sameType_success() {
        assertTrue(new HistoryCommand().equals(new HistoryCommand()));
    }

    @Test
    public void equals_sameObject_success() {
        HistoryCommand historyCommand = new HistoryCommand();
        assertTrue(historyCommand.equals(historyCommand));
    }

    @Test
    public void equals_failure() {
        assertFalse(new HistoryCommand().equals(1));
    }
}
