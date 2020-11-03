package seedu.address.model.book;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Book}'s {@code Name} matches any of the keywords given.
 */
public class NameMatchesKeywordPredicate implements Predicate<Book> {
    private final List<String> keywords;

    public NameMatchesKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Book book) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(book.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameMatchesKeywordPredicate // instanceof handles nulls
                && keywords.equals(((NameMatchesKeywordPredicate) other).keywords)); // state check
    }

}
