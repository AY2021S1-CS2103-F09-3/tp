package seedu.address.testutil;

import seedu.address.model.Library;

public class EmptyList {
    /**
     * Returns an {@code Library} with no books.
     */
    public static Library getEmptyLibrary() {
        return new Library();
    }
}
