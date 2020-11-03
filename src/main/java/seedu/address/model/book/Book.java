package seedu.address.model.book;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.category.Category;
import seedu.address.model.review.Review;

/**
 * Represents a Book in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Book {

    // Identity fields
    private final Name name;
    private final Isbn isbn;
    private final Email email;
    private final Author author;
    private final Publisher publisher;

    // Data fields
    private final Address address;
    private final Times times;
    private final Set<Category> categories = new HashSet<>();

    private final Stocking stocking;
    private final List<Review> reviews = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Book(Name name, Isbn isbn, Email email, Address address,
                Times times, Set<Category> categories, Stocking stocking, List<Review> reviews,
                Author author, Publisher publisher) {
        requireAllNonNull(name, isbn, email, address, times, categories, author);
        this.name = name;
        this.isbn = isbn;
        this.email = email;
        this.address = address;
        this.times = times;
        this.categories.addAll(categories);
        // add
        this.author = author;
        this.publisher = publisher;
        this.stocking = stocking;
        this.reviews.addAll(reviews);
    }

    public Name getName() {
        return name;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public List<Review> getReviews() {
        return this.reviews;
    }

    public Author getAuthor() {
        return author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Times getTimes() {
        return times;
    }


    /**
     * Returns an immutable category set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public Stocking getStocking() {
        return this.stocking;
    }

    /**
     * Returns true if both books of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two books.
     */
    public boolean isSameBook(Book otherBook) {
        if (otherBook == this) {
            return true;
        }

        return otherBook != null
                && otherBook.getName().equals(getName())
                && (otherBook.getIsbn().equals(getIsbn()) || otherBook.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both books have the same identity and data fields.
     * This defines a stronger notion of equality between two books.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Book)) {
            return false;
        }

        Book otherBook = (Book) other;
        return otherBook.getName().equals(getName())
                && otherBook.getIsbn().equals(getIsbn())
                && otherBook.getEmail().equals(getEmail())
                && otherBook.getAddress().equals(getAddress())
                && otherBook.getCategories().equals(getCategories())
                && otherBook.getAuthor().equals(getAuthor())
                && otherBook.getPublisher().equals(getPublisher());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, isbn, email, address, categories, author, publisher);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Isbn: ")
                .append(getIsbn())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(" Times: ")
                .append(getTimes())
                .append(getAddress())
                .append(" Categories: ");
        getCategories().forEach(builder::append);
        builder.append(" Author: ").append(getAuthor());
        builder.append(" Publisher: ").append(getPublisher());
        return builder.toString();
    }

}
