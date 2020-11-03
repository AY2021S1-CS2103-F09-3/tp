package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.book.Book;
import seedu.address.model.problem.Problem;
import seedu.address.ui.BookListPanel;
import seedu.address.ui.Mode;

/**
 * Represents the in-memory model of the library data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Library library;
    private final UserPrefs userPrefs;
    private final FilteredList<Book> filteredBooks;
    private final FilteredList<Problem> filteredProblems;


    /**
     * Initializes a ModelManager with the given library and userPrefs.
     */
    public ModelManager(ReadOnlyLibrary library, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(library, userPrefs);

        logger.fine("Initializing with library: " + library + " and user prefs " + userPrefs);

        this.library = new Library(library);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredBooks = new FilteredList<>(this.library.getBookList());
        filteredProblems = new FilteredList<>(this.library.getProblemList());
    }

    public ModelManager() {
        this(new Library(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getLibraryFilePath() {
        return userPrefs.getLibraryFilePath();
    }

    @Override
    public void setLibraryFilePath(Path libraryFilePath) {
        requireNonNull(libraryFilePath);
        userPrefs.setLibraryFilePath(libraryFilePath);
    }

    // =========== Library
    // ================================================================================

    @Override
    public void setLibrary(ReadOnlyLibrary library) {
        this.library.resetData(library);
    }

    @Override
    public ReadOnlyLibrary getLibrary() {
        return library;
    }

    @Override
    public boolean hasBook(Book book) {
        requireNonNull(book);
        return library.hasBook(book);
    }

    @Override
    public void deleteBook(Book target) {
        library.removeBook(target);
    }

    @Override
    public void addBook(Book book) {
        library.addBook(book);
        updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS, Mode.NORMAL);
    }

    @Override
    public void setBook(Book target, Book editedBook) {
        requireAllNonNull(target, editedBook);

        library.setBook(target, editedBook);
    }

    // =========== Filtered Book List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Book} backed by the
     * internal list of {@code versionedLibrary}
     */
    @Override
    public ObservableList<Book> getFilteredBookList() {
        return filteredBooks;
    }

    @Override
    public void updateFilteredBookList(Predicate<Book> predicate, Mode mode) {
        requireNonNull(predicate);
        BookListPanel.setMode(mode);
        filteredBooks.setPredicate(predicate);
    }

    /**
     * Updates the book list with the predicate.
     *
     * @param predicate The predicate that is used to update the list.
     */
    public void updateFilteredBookList(Predicate<Book> predicate) {
        requireNonNull(predicate);
        filteredBooks.setPredicate(predicate);
    }

    @Override
    public boolean hasProblem(Problem problem) {
        requireNonNull(problem);
        return library.hasProblem(problem);
    }

    @Override
    public void deleteProblem(Problem problem) {
        library.removeProblem(problem);
    }

    @Override
    public void addProblem(Problem problem) {
        library.addProblem(problem);
        updateFilteredProblemList(PREDICATE_SHOW_ALL_PROBLEMS, Mode.NORMAL);
    }

    @Override
    public void setProblem(Problem target, Problem problem) {
        requireAllNonNull(target, problem);

        library.setProblem(target, problem);
    }

    @Override
    public ObservableList<Problem> getFilteredProblemList() {
        return filteredProblems;
    }

    @Override
    public String getProblemString() {
        return library.getProblemString();
    }

    @Override
    public void updateFilteredProblemList(Predicate<Problem> predicate, Mode mode) {
        requireNonNull(predicate);
        filteredProblems.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return library.equals(other.library) && userPrefs.equals(other.userPrefs)
                && filteredBooks.equals(other.filteredBooks);
    }
}
