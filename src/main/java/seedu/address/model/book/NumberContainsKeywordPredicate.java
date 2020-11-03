package seedu.address.model.book;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Book}'s number matches any of the keywords given.
 */
public class NumberContainsKeywordPredicate implements Predicate<Book> {
    private final List<String> keywords;

    public NumberContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Book book) {
        return keywords.stream()
                .anyMatch((number) -> book.getIsbn().value.toUpperCase().contains(number.toUpperCase()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof NumberContainsKeywordPredicate
                    && this.keywords.equals(((NumberContainsKeywordPredicate) other).keywords));
    }
}
