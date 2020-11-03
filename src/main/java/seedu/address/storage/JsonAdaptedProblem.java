package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.problem.Description;
import seedu.address.model.problem.Problem;
import seedu.address.model.problem.Severity;

public class JsonAdaptedProblem {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Book's %s field is missing!";

    private String severity;
    private String description;



    /**
     * Constructs a {@code JsonAdaptedBook} with the given book details.
     */
    @JsonCreator
    public JsonAdaptedProblem(@JsonProperty("severity") String severity,
                              @JsonProperty("description") String description) {
        this.severity = severity;
        this.description = description;
    }

    /**
     * Converts a given {@code Book} into this class for Jackson use.
     */
    public JsonAdaptedProblem(Problem problem) {
        severity = problem.getSeverity().severity;
        description = problem.getDescription().description;
    }

    /**
     * Converts this Jackson-friendly adapted book object into the model's {@code Book} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted book.
     */
    public Problem toModelType() throws IllegalValueException {


        if (severity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Severity.class.getSimpleName()));
        }
        if (!Severity.isValidSeverity(severity)) {
            throw new IllegalValueException(Severity.MESSAGE_CONSTRAINTS);
        }
        final Severity modelSeverity = new Severity(severity);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        return new Problem(modelSeverity, modelDescription);
    }
}
