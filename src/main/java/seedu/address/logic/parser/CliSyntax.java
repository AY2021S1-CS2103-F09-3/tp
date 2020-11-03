package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {
    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_ISBN = new Prefix("i/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("ad/");
    public static final Prefix PREFIX_TIMES = new Prefix("t/");
    public static final Prefix PREFIX_CATEGORY = new Prefix("c/");
    public static final Prefix PREFIX_STOCKING = new Prefix("s/");
    public static final Prefix PREFIX_AUTHOR = new Prefix("a/");
    public static final Prefix PREFIX_PUBLISHER = new Prefix("p/");
    public static final Prefix PREFIX_SEVERITY = new Prefix("severity/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("problem/");
    public static final Prefix PREFIX_VIEW_PROBLEMS = new Prefix("view/");
    public static final Prefix PREFIX_RATING = new Prefix("ra/");
    public static final Prefix PREFIX_REVIEW = new Prefix("re/");
    public static final Prefix PREFIX_REVIEWNUMBER = new Prefix("rn/");
}
