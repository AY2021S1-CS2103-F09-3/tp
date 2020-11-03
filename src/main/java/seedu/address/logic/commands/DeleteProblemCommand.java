package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.problem.Problem;




public class DeleteProblemCommand extends Command {

    public static final String COMMAND_WORD = "deletepr";
    public static final String SUGGESTION = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the problems identified by the index number used in the displayed problem list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BOOK_SUCCESS = "Deleted problem: %1$s";

    private final Index targetIndex;

    public DeleteProblemCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Problem> lastShownList = model.getFilteredProblemList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Problem problemToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteProblem(problemToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOK_SUCCESS, problemToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteProblemCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteProblemCommand) other).targetIndex)); // state check
    }
}
