package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEVERITY;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddProblemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.problem.Description;
import seedu.address.model.problem.Problem;
import seedu.address.model.problem.Severity;


/**
 * Parses input arguments and creates a new AddProblemCommand object
 */
public class AddProblemCommandParser implements Parser<AddProblemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProblemCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SEVERITY, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_SEVERITY, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProblemCommand.MESSAGE_USAGE));
        }

        Severity severity = ParserUtil.parseSeverity(argMultimap.getValue(PREFIX_SEVERITY).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Problem problem = new Problem(severity, description);

        return new AddProblemCommand(problem);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
