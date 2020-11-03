package seedu.address.model.book;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents how many times a book is borrowed before.
 * Guarantees: immutable; is valid as declared in {@link #isValidTimes(String)}
 */
public class Times {
    public static final String MESSAGE_CONSTRAINTS = "times should be a non-negative integer";
    public static final String VALIDATION_REGEX = "[1-9]\\d*|0";
    public final String value;

    /**
     * Constructs a {@code Times}.
     *
     * @param times A valid times.
     */
    public Times(String times) {
        requireNonNull(times);
        checkArgument(isValidTimes(times), MESSAGE_CONSTRAINTS);
        value = times;
    }

    /**
     * Returns true if a given string is a valid times.
     */
    public static boolean isValidTimes(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public int getValue() {
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Times // instanceof handles nulls
                && value.equals(((Times) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
