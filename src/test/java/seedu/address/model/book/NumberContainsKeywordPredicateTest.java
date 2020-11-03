package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class NumberContainsKeywordPredicateTest {


    @Test
    void equals() {
        List<String> keywords = Arrays.asList("a");
        List<String> secondKeywords = Arrays.asList("a", "b", "c");

        NumberContainsKeywordPredicate numberContainsKeywordPredicate = new NumberContainsKeywordPredicate(keywords);
        NumberContainsKeywordPredicate secondPredicate = new NumberContainsKeywordPredicate(secondKeywords);

        // same object -> returns true
        assertTrue(numberContainsKeywordPredicate.equals(numberContainsKeywordPredicate));

        // same values -> returns true
        NumberContainsKeywordPredicate copyPredicate = new NumberContainsKeywordPredicate(keywords);
        assertTrue(numberContainsKeywordPredicate.equals(copyPredicate));

        // different types -> returns false
        assertFalse(numberContainsKeywordPredicate.equals(1));

        // null -> returns false
        assertFalse(numberContainsKeywordPredicate.equals(null));

        // different book -> returns false
        assertFalse(numberContainsKeywordPredicate.equals(secondPredicate));
    }
}
