package seedu.address.model.book;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;

public class Stocking {
    public static final String MESSAGE_CONSTRAINTS = "stocking needs to map from a location to the"
            + " number of stocking in that location.\n"
            + "The example is: centralLb 30 scienceLb 20 HSSMLb 10\n"
            + "The library location name is case sensitive.\n"
            + "Note that only central library, science library, and HSSM library are available "
            + "and no stocking information of other library can be added currently.\n"
            + "Given that there are only 3 locations available, the number of location argument can be at most 3.\n"
            + "If there are duplicate location argument, such as centralLb 10 centralLb 20, then "
            + "the later one will cover the previous one.\n"
            + "Additionally, to make the recorded stocking more reasonable, the stocking of a "
            + "book in a location should be an integer between 0 and 99999.\n"
            + "If the stocking of a location has a value of 0, then the stocking information shown for "
            + "the book in that location will be: Not available.\n"
            + "Note that if the stocking information of some of the libraries is not provided, then the stocking "
            + "information shown for the book in that location will be: Not available";

    public static final String NUMBER_CONSTRAINTS = "to make the recorded stocking more reasonable, the stocking of a "
            + "book in a location should be an integer between 1 and 99999.\n";

    public static final String[] LOCATION = new String[]{"centralLb", "scienceLb", "HSSMLb"};
    /*
     * The stocking string should map from the location to
     * the number of book in the location.
     */
    public static final String VALIDATION_REGEX =
            "^\\s*(centralLb|scienceLb|HSSMLb)?\\s*(\\d{1,5})?\\s*(centralLb|scienceLb|HSSMLb)?\\s*(\\d{1,5})?\\s*"
            + "(centralLb|scienceLb|HSSMLb)?\\s*(\\d{1,5})?\\s*$";

    public final HashMap<String, Integer> storage = new HashMap<>();

    /**
     * Constructs an {@code Stocking}.
     *
     * @param stocking A valid stocking.
     */
    public Stocking(HashMap<String, Integer> stocking) {
        requireNonNull(stocking);

        for (int i = 0; i < LOCATION.length; i = i + 1) {
            this.storage.put(LOCATION[i], 0);
        }

        this.storage.putAll(stocking);
    }


    /**
     * Returns true if a given string is a valid stocking.
     */
    public static boolean isValidStocking(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "centralLb " + storage.getOrDefault("centralLb", 0) + " "
                + "scienceLb " + storage.getOrDefault("scienceLb", 0) + " "
                + "HSSMLb " + storage.getOrDefault("HSSMLb", 0);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Stocking // instanceof handles nulls
                && storage.equals(((Stocking) other).storage)); // state check
    }

    @Override
    public int hashCode() {
        return storage.hashCode();
    }

}
