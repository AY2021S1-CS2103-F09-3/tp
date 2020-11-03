package seedu.address.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Book's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPublisher(String)}
 */
public class Publisher {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String publisher;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Publisher(String name) {
        requireNonNull(name);
        checkArgument(isValidPublisher(name), MESSAGE_CONSTRAINTS);
        publisher = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPublisher(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return publisher;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Publisher // instanceof handles nulls
                && publisher.equals(((Publisher) other).publisher)); // state check
    }

    @Override
    public int hashCode() {
        return publisher.hashCode();
    }

}
