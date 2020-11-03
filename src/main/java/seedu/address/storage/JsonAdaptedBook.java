package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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

/**
 * Jackson-friendly version of {@link Book}.
 */
class JsonAdaptedBook {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Book's %s field is missing!";

    private String name;
    private String isbn;
    private String email;
    private String address;
    private String times;
    private final List<JsonAdaptedCategory> categorised = new ArrayList<>();
    private String author;
    private String publisher;
    private final JsonAdaptedStocking stocking;
    private final List<JsonAdaptedReview> reviews = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedBook} with the given book details.
     */
    @JsonCreator
    public JsonAdaptedBook(@JsonProperty("name") String name, @JsonProperty("isbn") String isbn,
                           @JsonProperty("email") String email, @JsonProperty("address") String address,
                           @JsonProperty("times") String times,
                           @JsonProperty("tagged") List<JsonAdaptedCategory> categorised,
                           @JsonProperty("stocking") JsonAdaptedStocking stocking,
                           @JsonProperty("reviews") List<JsonAdaptedReview> reviews,
                           @JsonProperty("author") String author,
                           @JsonProperty("publisher") String publisher) {
        this.name = name;
        this.isbn = isbn;
        this.email = email;
        this.address = address;
        this.times = times;
        if (categorised != null) {
            this.categorised.addAll(categorised);
        }
        this.author = author;
        this.publisher = publisher;
        this.stocking = stocking;
        if (reviews != null) {
            this.reviews.addAll(reviews);
        }
    }

    /**
     * Converts a given {@code Book} into this class for Jackson use.
     */
    public JsonAdaptedBook(Book source) {
        name = source.getName().fullName;
        isbn = source.getIsbn().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        times = source.getTimes().value;
        categorised.addAll(source.getCategories().stream()
                .map(JsonAdaptedCategory::new)
                .collect(Collectors.toList()));
        author = source.getAuthor().author;
        publisher = source.getPublisher().publisher;
        stocking = new JsonAdaptedStocking(source.getStocking());
        reviews.addAll(source.getReviews().stream()
                .map(JsonAdaptedReview::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted book object into the model's {@code Book} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted book.
     */
    public Book toModelType() throws IllegalValueException {
        final List<Category> bookCategories = new ArrayList<>();
        for (JsonAdaptedCategory category : categorised) {
            bookCategories.add(category.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (isbn == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Isbn.class.getSimpleName()));
        }
        if (!Isbn.isValidIsbn(isbn)) {
            throw new IllegalValueException(Isbn.MESSAGE_CONSTRAINTS);
        }
        final Isbn modelIsbn = new Isbn(isbn);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        final List<Review> modelReviews = new ArrayList<>();

        for (JsonAdaptedReview review : reviews) {
            modelReviews.add(review.toModelType());
        }

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);
        if (times == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Times.class.getSimpleName()));
        }

        final Times modelTimes = new Times(times);
        final Set<Category> modelCategories = new HashSet<>(bookCategories);

        if (author == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Author.class.getSimpleName()));
        }
        if (!Author.isValidAuthor(author)) {
            throw new IllegalValueException(Author.MESSAGE_CONSTRAINTS);
        }
        final Author modelAuthor = new Author(author);

        if (publisher == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Publisher.class.getSimpleName()));
        }
        if (!Publisher.isValidPublisher(publisher)) {
            throw new IllegalValueException(Publisher.MESSAGE_CONSTRAINTS);
        }
        final Publisher modelPublisher = new Publisher(publisher);

        if (stocking == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JsonAdaptedStocking.class.getSimpleName()));
        }
        final Stocking modelStocking = stocking.toModelType();

        return new Book(modelName, modelIsbn, modelEmail, modelAddress, modelTimes,
                modelCategories, modelStocking, modelReviews, modelAuthor, modelPublisher);
    }
}
