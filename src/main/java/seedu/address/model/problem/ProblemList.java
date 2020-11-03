package seedu.address.model.problem;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.book.exceptions.BookNotFoundException;
import seedu.address.model.book.exceptions.DuplicateBookException;


public class ProblemList implements Iterable<Problem> {
    private final ObservableList<Problem> list = FXCollections.observableArrayList();
    private final ObservableList<Problem> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(list);

    /**
     * Returns true if the list contains an equivalent book as the given argument.
     */
    public boolean contains(Problem toCheck) {
        requireNonNull(toCheck);
        return list.stream().anyMatch(toCheck::isSameProblem);
    }

    /**
     * Replaces the problem {@code target} in the list with {@code editedBook}.
     * {@code target} must exist in the list.
     * The book identity of {@code editedBook} must not be the same as another existing book in the list.
     */
    public void setProblem(Problem target, Problem editedProblem) {
        requireAllNonNull(target, editedProblem);

        int index = list.indexOf(target);
        if (index == -1) {
            throw new BookNotFoundException();
        }

        if (!target.isSameProblem(editedProblem) && contains(editedProblem)) {
            throw new DuplicateBookException();
        }

        list.set(index, editedProblem);
    }

    /**
     * Adds a problem to the list.
     */
    public void add(Problem toAdd) {
        requireNonNull(toAdd);
        list.add(toAdd);
    }

    /**
     * Deletes a problem to the list.
     */
    public void delete(Problem toDelete) {
        requireNonNull(toDelete);
        list.remove(toDelete);
    }

    public List<Problem> getList() {
        return list;
    }

    public void setProblems(ProblemList replacement) {
        requireNonNull(replacement);
        list.setAll(replacement.list);
    }

    public void setProblems(List<Problem> problems) {
        requireAllNonNull(problems);
        list.setAll(problems);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Problem> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Problem> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        int i = 0; // i is to count the number of problems
        String noProblem = "There is currently no problem.";
        String res = "";
        for (Problem problem: list) {
            i++;
            res += problem.toString();
            res += "\n";
        }

        if (i == 0) {
            return noProblem;
        } else if (i == 1) {
            String problemString = "There is only 1 problem: \n" + res;
            return problemString;
        } else {
            String problemString = "There are " + Integer.toString(i)
                + " problems: \n" + res;
            return problemString;
        }
    }
}
