package seedu.address.model.review;

import static java.util.Objects.requireNonNull;

public class ReviewContent {
    public static final String MESSAGE_CONSTRAINTS = "review cannot be empty";

    /*
     * The review should not be empty
     */
    public static final String VALIDATION_REGEX = "^\\s*(\\S.*)\\s*$";

    public final String content;

    /**
     * Constructs an {@code ReviewContent}.
     *
     * @param content A valid stocking.
     */
    public ReviewContent(String content) {
        requireNonNull(content);
        this.content = content;
    }
    /**
     * Returns true if a given string is valid review content.
     */
    public static boolean isValidContent(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReviewContent // instanceof handles nulls
                && content.equals(((ReviewContent) other).content)); // state check
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
