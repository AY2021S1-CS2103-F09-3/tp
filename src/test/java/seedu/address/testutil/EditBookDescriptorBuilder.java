package seedu.address.testutil;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditBookDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.book.Address;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Name;
import seedu.address.model.book.Publisher;
import seedu.address.model.book.Stocking;
import seedu.address.model.book.Times;
import seedu.address.model.category.Category;

/**
 * A utility class to help with building EditBookDescriptor objects.
 */
public class EditBookDescriptorBuilder {

    private EditBookDescriptor descriptor;

    public EditBookDescriptorBuilder() {
        descriptor = new EditBookDescriptor();
    }

    public EditBookDescriptorBuilder(EditBookDescriptor descriptor) {
        this.descriptor = new EditBookDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBookDescriptor} with fields containing {@code book}'s details
     */
    public EditBookDescriptorBuilder(Book book) {
        descriptor = new EditBookDescriptor();
        descriptor.setName(book.getName());
        descriptor.setTimes(book.getTimes());
        descriptor.setIsbn(book.getIsbn());
        descriptor.setEmail(book.getEmail());
        descriptor.setAddress(book.getAddress());
        descriptor.setCategories(book.getCategories());
        descriptor.setTimes(book.getTimes());
        descriptor.setStocking(book.getStocking());
        descriptor.setAuthor(book.getAuthor());
        descriptor.setPublisher(book.getPublisher());
    }

    /**
     * Sets the {@code Name} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Isbn} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withIsbn(String isbn) {
        descriptor.setIsbn(new Isbn(isbn));
        return this;
    }

    /**
     * Sets the {@code Times} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withTimes(String times) {
        descriptor.setTimes(new Times(times));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code categories} into a {@code Set<Category>} and set it to the {@code EditBookDescriptor}
     * that we are building.
     */
    public EditBookDescriptorBuilder withCategories(String... categories) {
        Set<Category> categorySet = Stream.of(categories).map(Category::new).collect(Collectors.toSet());
        descriptor.setCategories(categorySet);
        return this;
    }

    /**
     * Sets the {@code Author} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withAuthor(String author) {
        descriptor.setAuthor(new Author(author));
        return this;
    }

    /**
     * Sets the {@code Publisher} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withPublisher(String publisher) {
        descriptor.setPublisher(new Publisher(publisher));
        return this;
    }

    /**
     * Sets the {@code Stocking} of the {@code EditBookDescriptor} that we are building.
     */
    public EditBookDescriptorBuilder withStockings(String storage) {
        try {
            Stocking stocking = ParserUtil.parseStocking(storage);
            descriptor.setStocking(stocking);
        } catch (Exception exception) {
            descriptor.setStocking(new Stocking(new HashMap<>()));
        }
        return this;
    }

    public EditBookDescriptor build() {
        return descriptor;
    }
}
