package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.problem.Problem;

/**
 * Panel containing the list of books.
 */
public class ProblemReportListPanel extends UiPart<Region> {
    private static final String FXML = "ProblemReportListPanel.fxml";
    private static Mode mode = Mode.NORMAL;
    private final Logger logger = LogsCenter.getLogger(ProblemReportListPanel.class);

    @javafx.fxml.FXML
    private ListView<Problem> problemReportListView;

    /**
     * Creates a {@code BookListPanel} with the given {@code ObservableList}.
     */
    public ProblemReportListPanel(ObservableList<Problem> problemList) {
        super(FXML);

        problemReportListView.setItems(problemList);
        problemReportListView.setCellFactory(listView -> new ProblemReportListViewCell());
    }

    public static void setMode(Mode mode) {
        ProblemReportListPanel.mode = mode;
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Book} using a {@code BookCard}.
     */
    class ProblemReportListViewCell extends ListCell<Problem> {
        @Override
        protected void updateItem(Problem problem , boolean empty) {
            super.updateItem(problem, empty);

            if (empty || problem == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (mode.equals(Mode.NORMAL)) {
                    setGraphic(new ProblemReportListCard(problem, getIndex() + 1).getRoot());
                }
            }
        }
    }
}
