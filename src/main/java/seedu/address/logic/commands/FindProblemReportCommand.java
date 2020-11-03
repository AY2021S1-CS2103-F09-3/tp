package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.problem.DescriptionContainsKeywordsPredicate;
import seedu.address.ui.Mode;


public class FindProblemReportCommand extends Command {

    public static final String COMMAND_WORD = "findpr";
    public static final String SUGGESTION = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all problem reports whose description contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " table";

    private final DescriptionContainsKeywordsPredicate predicate;

    public FindProblemReportCommand(DescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredProblemList(predicate, Mode.NORMAL);
        return new CommandResult(
                String.format(Messages.MESSAGE_REPORT_LISTED_OVERVIEW, model.getFilteredProblemList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindProblemReportCommand // instanceof handles nulls
                && predicate.equals(((FindProblemReportCommand) other).predicate)); // state check
    }
}
