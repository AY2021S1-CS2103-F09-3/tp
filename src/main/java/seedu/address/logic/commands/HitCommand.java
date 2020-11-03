package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Hidden command to handle number exceeds max int value.
 */
public class HitCommand extends Command {

    public static final String COMMAND_WORD = "hit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Informs user of hitting limit value in times.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HIT_MESSAGE = "Wow! Max borrowed times are hit! What a popular library!"
            + "\n\nYou may want to use 'times' command to reset some borrowed times values";

    /**
     * Returns command result with hit message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return new CommandResult
     */
    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HIT_MESSAGE);
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof HitCommand);
    }
}
