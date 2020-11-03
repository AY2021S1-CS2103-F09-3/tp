package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.*;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.LibraryParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.book.Book;
import seedu.address.model.problem.Problem;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final LibraryParser libraryParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        libraryParser = new LibraryParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException, IOException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = libraryParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveLibrary(model.getLibrary());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyLibrary getLibrary() {
        return model.getLibrary();
    }

    @Override
    public ObservableList<Book> getFilteredBookList() {
        return model.getFilteredBookList();
    }

    @Override
    public ObservableList<Problem> getFilteredProblemReportList() {
        return model.getFilteredProblemList();
    }

    @Override
    public Path getLibraryFilePath() {
        return model.getLibraryFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public List<String> getSuggestions() {
        List<String> keywords = new ArrayList<>();
        keywords.add(AddCommand.SUGGESTION);
        keywords.add(AddProblemCommand.SUGGESTION);
        keywords.add(AddReviewCommand.SUGGESTION);
        keywords.add(ClearCommand.COMMAND_WORD);
        keywords.add(DeleteCommand.SUGGESTION);
        keywords.add(DeleteByCommand.SUGGESTION);
        keywords.add(DeleteProblemCommand.SUGGESTION);
        keywords.add(DeleteReviewCommand.SUGGESTION);
        keywords.add(EditCommand.SUGGESTION);
        keywords.add(EditReviewCommand.SUGGESTION);
        keywords.add(ExitCommand.COMMAND_WORD);
        keywords.add(FindCommand.SUGGESTION);
        keywords.add(FindProblemReportCommand.SUGGESTION);
        keywords.add(HelpCommand.COMMAND_WORD);
        keywords.add(HistoryCommand.COMMAND_WORD);
        keywords.add(ListCommand.COMMAND_WORD);
        keywords.add(RandomCommand.SUGGESTION);
        keywords.add(SearchReviewCommand.SUGGESTION);
        keywords.add(SortCommand.SUGGESTION);
        keywords.add(StockCommand.SUGGESTION);
        keywords.add(TimesCommand.SUGGESTION);
        keywords.add(UsageCommand.SUGGESTION);
        keywords.add(UsageByCommand.SUGGESTION);
        keywords.add(ViewProblemCommand.SUGGESTION);
        return keywords;
    }
}
