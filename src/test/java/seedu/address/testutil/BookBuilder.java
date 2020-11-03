package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
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
import seedu.address.model.review.Review;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Book objects.
 */
public class BookBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_ISBN = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TIMES = "10";
    public static final String DEFAULT_AUTHOR = "a";
    public static final String DEFAULT_PUBLISHER = "pub";
    public static final HashMap<String, Integer> DEFAULT_STOCKING = new HashMap<>();

    private Name name;
    private Isbn isbn;
    private Email email;
    private Address address;
    private Times times;
    private Set<Category> categories;
    private Author author;
    private Publisher publisher;
    private Stocking stocking;
    private List<Review> reviews;

    /**
     * Creates a {@code BookBuilder} with the default details.
     */
    public BookBuilder() {
        name = new Name(DEFAULT_NAME);
        isbn = new Isbn(DEFAULT_ISBN);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        times = new Times(DEFAULT_TIMES);
        categories = new HashSet<>();
        author = new Author(DEFAULT_AUTHOR);
        publisher = new Publisher(DEFAULT_PUBLISHER);
        stocking = new Stocking(DEFAULT_STOCKING);
        reviews = new ArrayList<>();
    }

    /**
     * Initializes the BookBuilder with the data of {@code bookToCopy}.
     */
    public BookBuilder(Book bookToCopy) {
        name = bookToCopy.getName();
        isbn = bookToCopy.getIsbn();
        email = bookToCopy.getEmail();
        address = bookToCopy.getAddress();
        times = bookToCopy.getTimes();
        categories = new HashSet<>(bookToCopy.getCategories());
        author = bookToCopy.getAuthor();
        publisher = bookToCopy.getPublisher();
        stocking = bookToCopy.getStocking();
        reviews = bookToCopy.getReviews();
    }

    /**
     * Sets the {@code Name} of the {@code Book} that we are building.
     */
    public BookBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code categories} into a {@code Set<Category>} and set it to the {@code Book} that we are building.
     */
    public BookBuilder withCategories(String ... categories) {
        this.categories = SampleDataUtil.getCategorySet(categories);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Book} that we are building.
     */
    public BookBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Isbn} of the {@code Book} that we are building.
     */
    public BookBuilder withIsbn(String isbn) {
        this.isbn = new Isbn(isbn);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Book} that we are building.
     */
    public BookBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code reviews} of the {@code Book} that we are building.
     */
    public BookBuilder withReviews(Review ... reviews) {
        this.reviews = SampleDataUtil.getReviews(reviews);
        return this;
    }

    /**
     * Sets the {@code Times} of the {@code Book} that we are building.
     */
    public BookBuilder withTimes(String times) {
        this.times = new Times(times);
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code Book} that we are building.
     */
    public BookBuilder withAuthor(String author) {
        this.author = new Author(author);
        return this;
    }

    /**
     * Sets the {@code Publisher} of the {@code Book} that we are building.
     */
    public BookBuilder withPublisher(String publisher) {
        this.publisher = new Publisher(publisher);
        return this;
    }

    /**
     * Sets the {@code Stocking} of the {@code Book} that we are building.
     */
    public BookBuilder withStocking(String storage) {
        try {
            Stocking stocking = ParserUtil.parseStocking(storage);
            this.stocking = stocking;
        } catch (Exception exception) {
            this.stocking = new Stocking(new HashMap<>());
        }
        return this;
    }

    public Book build() {
        return new Book(name, isbn, email, address, times, categories, stocking, reviews, author, publisher);
    }
}
