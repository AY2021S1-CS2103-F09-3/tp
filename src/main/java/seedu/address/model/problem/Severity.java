package seedu.address.model.problem;

import static java.util.Objects.requireNonNull;

public class Severity {
    public static final String MESSAGE_CONSTRAINTS =
            "Severity should be high/medium/low, case insensitive, and it should not be blank";


    public final String severity;

    /**
     * Constructs a {@code severity}.
     *
     * @param severity A valid severity.
     */
    public Severity(String severity) {
        requireNonNull(severity);
        assert !severity.isEmpty() : "severity cannot be empty";
        this.severity = severity.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidSeverity(String test) {
        test = test.toLowerCase();
        return (test.equals("high")) || (test.equals("medium")) || (test.equals("low"));
    }

    @Override
    public String toString() {
        return severity;
    }
}
