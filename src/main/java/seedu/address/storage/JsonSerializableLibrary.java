package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Library;
import seedu.address.model.ReadOnlyLibrary;
import seedu.address.model.book.Book;
import seedu.address.model.problem.Problem;



/**
 * An Immutable Library that is serializable to JSON format.
 */
@JsonRootName(value = "library")
class JsonSerializableLibrary {

    public static final String MESSAGE_DUPLICATE_BOOK = "Books list contains duplicate book(s).";

    private final List<JsonAdaptedBook> books = new ArrayList<>();
    private final List<JsonAdaptedProblem> problems = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLibrary} with the given books.
     */
    @JsonCreator
    public JsonSerializableLibrary(@JsonProperty("books") List<JsonAdaptedBook> books,
                                   @JsonProperty("problems") List<JsonAdaptedProblem> problems) {
        this.books.addAll(books);
        this.problems.addAll(problems);
    }

    /**
     * Converts a given {@code ReadOnlyLibrary} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLibrary}.
     */
    public JsonSerializableLibrary(ReadOnlyLibrary source) {
        books.addAll(source.getBookList().stream().map(JsonAdaptedBook::new).collect(Collectors.toList()));
        problems.addAll(source.getProblemList().stream().map(JsonAdaptedProblem::new).collect(Collectors.toList()));
    }

    /**
     * Converts this library into the model's {@code Library} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Library toModelType() throws IllegalValueException {
        Library library = new Library();
        for (JsonAdaptedBook jsonAdaptedBook : books) {
            Book book = jsonAdaptedBook.toModelType();
            if (library.hasBook(book)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BOOK);
            }
            library.addBook(book);
        }

        for (JsonAdaptedProblem jsonAdaptedProblem : problems) {
            Problem problem = jsonAdaptedProblem.toModelType();
            if (library.hasProblem(problem)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BOOK);
            }
            library.addProblem(problem);
        }
        return library;
    }

}
