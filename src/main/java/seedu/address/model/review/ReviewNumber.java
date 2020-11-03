package seedu.address.model.review;

import static java.util.Objects.requireNonNull;

/**
 * Represents the position of the review in the review list of a book in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidReviewNumber(String)}
 */
public class ReviewNumber {

    public static final String MESSAGE_CONSTRAINTS = "review index needs to be an integer";

    /*
     * The review number string should represent an integer with a length from 1 to 5 and
     * there can be white space.
     */
    public static final String VALIDATION_REGEX = "\\s*(\\d{1,5})\\s*";

    public final Integer reviewNumber;

    /**
     * Constructs an {@code ReviewNumber}.
     *
     * @param reviewNumber A valid review number.
     */
    public ReviewNumber(Integer reviewNumber) {
        requireNonNull(reviewNumber);
        this.reviewNumber = reviewNumber;
    }
    /**
     * Returns true if a given string is a valid review number.
     */
    public static boolean isValidReviewNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return reviewNumber.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReviewNumber // instanceof handles nulls
                && reviewNumber.equals(((ReviewNumber) other).reviewNumber)); // state check
    }

    @Override
    public int hashCode() {
        return reviewNumber.hashCode();
    }
}
