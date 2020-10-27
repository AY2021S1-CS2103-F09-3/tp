package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROBLEMS;

import seedu.address.model.Model;
import seedu.address.ui.Mode;

public class ViewProblemCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "View problems";

    public ViewProblemCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredProblemList((problem -> false), Mode.NORMAL);
        model.updateFilteredProblemList(PREDICATE_SHOW_ALL_PROBLEMS, Mode.NORMAL);
        //String problems_string = model.getFilteredProblemList().toString();
        String problems_string = model.getProblemString();
        return new CommandResult(problems_string);
    }
}
