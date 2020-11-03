package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.problem.Problem;

public class ProblemReportListCard extends UiPart<Region> {

    private static final String FXML = "ProblemReportListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Problem problem;

    @FXML
    private HBox cardPane;
    @FXML
    private Label severity;
    @FXML
    private Label id;
    @FXML
    private Label description;


    /**
     * Creates a {@code BookCode} with the given {@code Book} and index to display.
     */
    public ProblemReportListCard(Problem problem, int displayedIndex) {
        super(FXML);
        this.problem = problem;
        id.setText(displayedIndex + ". ");
        severity.setText(problem.getSeverity().severity);
        description.setText(problem.getDescription().description);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProblemReportListCard)) {
            return false;
        }

        // state check
        ProblemReportListCard card = (ProblemReportListCard) other;
        return id.getText().equals(card.id.getText())
                && problem.equals(card.problem);
    }
}
