package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AUTHOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PUBLISHER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMES_AMY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditBookDescriptorBuilder;

public class EditBookDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditBookDescriptor descriptorWithSameValues = new EditCommand.EditBookDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditCommand.EditBookDescriptor editedAmy = new EditBookDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB)
                .build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different isbn -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_AMY).withIsbn(VALID_ISBN_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different categories -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_AMY).withCategories(VALID_CATEGORY_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different times -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_AMY).withCategories(VALID_TIMES_AMY).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different authors -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_AMY).withCategories(VALID_AUTHOR_AMY).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different publishers -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_AMY).withCategories(VALID_PUBLISHER_AMY).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
