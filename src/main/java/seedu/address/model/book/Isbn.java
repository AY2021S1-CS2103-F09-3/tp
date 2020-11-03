package seedu.address.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Book's isbn number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIsbn(String)}
 */
public class Isbn {


    public static final String MESSAGE_CONSTRAINTS =
            "Isbn numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Isbn}.
     *
     * @param isbn A valid isbn number.
     */
    public Isbn(String isbn) {
        requireNonNull(isbn);
        checkArgument(isValidIsbn(isbn), MESSAGE_CONSTRAINTS);
        value = isbn;
    }

    /**
     * Returns true if a given string is a valid isbn number.
     */
    public static boolean isValidIsbn(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Isbn // instanceof handles nulls
                && value.equals(((Isbn) other).value)); // state check
    }

    public String getIsbn() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
