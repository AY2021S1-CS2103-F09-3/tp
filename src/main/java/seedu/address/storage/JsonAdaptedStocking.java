package seedu.address.storage;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.book.Stocking;



/**
 * Jackson-friendly version of {@link Stocking}.
 */
public class JsonAdaptedStocking {
    private final int centralLibrary;
    private final int scienceLibrary;
    private final int hssmLibrary;

    /**
     * Constructs a {@code JsonAdaptedStocking} with the given stocking information.
     */
    @JsonCreator
    public JsonAdaptedStocking(@JsonProperty("centralLibrary") int centralLibrary,
                               @JsonProperty("scienceLibrary") int scienceLibrary,
                               @JsonProperty("HSSMLibrary") int hssmLibrary) {
        this.centralLibrary = centralLibrary;
        this.scienceLibrary = scienceLibrary;
        this.hssmLibrary = hssmLibrary;
    }

    /**
     * Converts a given {@code Stocking} into this class for Jackson use.
     */
    public JsonAdaptedStocking(Stocking stocking) {
        if (stocking == null) {
            this.centralLibrary = 0;
            this.scienceLibrary = 0;
            this.hssmLibrary = 0;
        } else {
            this.centralLibrary = stocking.storage.getOrDefault("centralLb", 0);
            this.scienceLibrary = stocking.storage.getOrDefault("scienceLb", 0);
            this.hssmLibrary = stocking.storage.getOrDefault("HSSMLb", 0);
        }
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Stocking} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted stocking.
     */
    public Stocking toModelType() throws IllegalValueException {
        HashMap<String, Integer> storage = new HashMap<>();
        if (!Stocking.isValidStocking("centralLb " + centralLibrary + " scienceLb " + scienceLibrary
                + " HSSMLb " + hssmLibrary)) {
            throw new IllegalValueException(Stocking.MESSAGE_CONSTRAINTS);
        }
        storage.put("centralLb", this.centralLibrary);
        storage.put("scienceLb", this.scienceLibrary);
        storage.put("HSSMLb", this.hssmLibrary);
        return new Stocking(storage);
    }
}
