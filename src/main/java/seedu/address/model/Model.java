package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.book.Book;
import seedu.address.model.problem.Problem;
import seedu.address.ui.Mode;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Book> PREDICATE_SHOW_ALL_BOOKS = unused -> true;
    Predicate<Problem> PREDICATE_SHOW_ALL_PROBLEMS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' library file path.
     */
    Path getLibraryFilePath();

    /**
     * Sets the user prefs' library file path.
     */
    void setLibraryFilePath(Path libraryFilePath);

    /**
     * Replaces library data with the data in {@code library}.
     */
    void setLibrary(ReadOnlyLibrary library);

    /**
     * Returns the Library
     */
    ReadOnlyLibrary getLibrary();

    /**
     * Returns true if a book with the same identity as {@code book} exists in the library.
     */
    boolean hasBook(Book book);

    /**
     * Deletes the given book.
     * The book must exist in the library.
     */
    void deleteBook(Book target);

    /**
     * Adds the given book.
     * {@code book} must not already exist in the a library.
     */
    void addBook(Book book);

    /**
     * Replaces the given book {@code target} with {@code editedBook}.
     * {@code target} must exist in the library.
     * The book identity of {@code editedBook} must not be the same as another existing book in the Llibrary.
     */
    void setBook(Book target, Book editedBook);

    /**
     * Returns an unmodifiable view of the filtered book list
     */
    ObservableList<Book> getFilteredBookList();

    /**
     * Updates the filter of the filtered book list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBookList(Predicate<Book> predicate, Mode mode);

    // problems

    /**
     * Returns true if a book with the same identity as {@code book} exists in the library.
     */
    boolean hasProblem(Problem problem);

    /**
     * Deletes the given book.
     * The book must exist in the library.
     */
    void deleteProblem(Problem problem);

    /**
     * Adds the given book.
     * {@code book} must not already exist in the a library.
     */
    void addProblem(Problem problem);

    /**
     * Replaces the given book {@code target} with {@code editedBook}.
     * {@code target} must exist in the library.
     * The book identity of {@code editedBook} must not be the same as another existing book in the Llibrary.
     */
    void setProblem(Problem target, Problem problem);

    /**
     * Returns an unmodifiable view of the filtered book list
     */
    ObservableList<Problem> getFilteredProblemList();

    /**
     * Updates the filter of the filtered book list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProblemList(Predicate<Problem> predicate, Mode mode);

    /**
     * @return a string that represents the problems.
     */
    String getProblemString();
}
