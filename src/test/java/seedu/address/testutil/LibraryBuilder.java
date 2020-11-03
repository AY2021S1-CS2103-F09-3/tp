package seedu.address.testutil;

import seedu.address.model.Library;
import seedu.address.model.book.Book;

/**
 * A utility class to help with building Library objects.
 * Example usage: <br>
 *     {@code Library ab = new LibraryBuilder().withBook("John", "Doe").build();}
 */
public class LibraryBuilder {

    private Library library;

    public LibraryBuilder() {
        library = new Library();
    }

    public LibraryBuilder(Library library) {
        this.library = library;
    }

    /**
     * Adds a new {@code Book} to the {@code Library} that we are building.
     */
    public LibraryBuilder withBook(Book book) {
        library.addBook(book);
        return this;
    }

    public Library build() {
        return library;
    }
}
