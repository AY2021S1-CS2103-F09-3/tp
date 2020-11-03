package seedu.address.model.book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StockingTest {

    @Test
    void isValidStocking() {
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 10 scienceLb 10"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 30 scienceLb 45"));
        Assertions.assertTrue(Stocking.isValidStocking("centralLb 10"));
        Assertions.assertTrue(Stocking.isValidStocking("scienceLb 10"));
        Assertions.assertTrue(Stocking.isValidStocking(" "));
        Assertions.assertTrue(Stocking.isValidStocking(""));
        Assertions.assertFalse(Stocking.isValidStocking("library 10"));
        Assertions.assertFalse(Stocking.isValidStocking("library"));
    }
}
