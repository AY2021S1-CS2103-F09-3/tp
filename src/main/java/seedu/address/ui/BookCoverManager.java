package seedu.address.ui;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.scene.image.Image;
import seedu.address.model.category.Category;

/**
 * An UI component that returns the book cover of the {@code Book}.
 */
public class BookCoverManager {

    public static final String GENERAL = "General";
    public static final String SCIENCE = "Science";
    public static final String HISTORY = "History";
    public static final String NOVEL = "Novels";
    public static final String PHYSICS = "Physics";
    public static final String CHEMISTRY = "Chemistry";
    public static final String MATHEMATICS = "Math";
    public static final String MODERN_WAR = "ModernWar";
    public static final String ANCIENT_WAR = "AncientWar";
    public static final String MODERN_HISTORY = "ModernHistory";
    public static final String ANCIENT_HISTORY = "AncientHistory";
    public static final HashMap<String, String[]> CATEGORY_MAP = new HashMap<>();

    private static final String[] GENERAL_COVER = new String[]{"/images/general.jpg", "/images/general2.jpg"};
    private static final String[] SCIENCE_COVER = new String[]{
        "/images/science.jpg",
        "/images/science2.jpg",
        "/images/science3.jpg",
        "/images/science4.jpg"
    };
    private static final String[] HISTORY_COVER = new String[]{"/images/history.jpg", "/images/history1.jpg"};
    private static final String[] NOVEL_COVER = new String[]{
        "/images/novel.jpg",
        "/images/novel2.jpg",
        "/images/novel3.jpg"
    };
    private static final String[] PHYSICS_COVER = new String[]{
        "/images/physics.jpg",
        "/images/physics2.jpg",
        "/images/physics3.jpg",
        "/images/physics4.jpg"
    };
    private static final String[] CHEMISTRY_COVER = new String[]{"/images/chemistry.jpg", "/images/chemistry2.jpg"};
    private static final String[] MATHEMATICS_COVER = new String[]{"/images/mathematics.jpg"};
    private static final String[] MODERN_WAR_COVER = new String[]{"/images/modernWar.jpg"};
    private static final String[] ANCIENT_WAR_COVER = new String[]{"/images/ancientWar.jpg"};
    private static final String[] MODERN_HISTORY_COVER = new String[]{"/images/modernHistory.jpg"};
    private static final String[] ANCIENT_HISTORY_COVER = new String[]{"/images/ancientHistory.jpg"};

    /**
     * Creates a new {@code BookCoverManager}.
     */
    public BookCoverManager() {
        assert CATEGORY_MAP != null;
        assert GENERAL_COVER != null;
        assert SCIENCE_COVER != null;
        assert HISTORY_COVER != null;
        assert NOVEL_COVER != null;
        assert PHYSICS_COVER != null;
        assert CHEMISTRY_COVER != null;
        assert MATHEMATICS_COVER != null;
        assert MODERN_WAR_COVER != null;
        assert ANCIENT_WAR_COVER != null;
        assert MODERN_HISTORY_COVER != null;
        assert ANCIENT_HISTORY_COVER != null;

        CATEGORY_MAP.putIfAbsent(GENERAL, GENERAL_COVER);
        CATEGORY_MAP.putIfAbsent(SCIENCE, SCIENCE_COVER);
        CATEGORY_MAP.putIfAbsent(HISTORY, HISTORY_COVER);
        CATEGORY_MAP.putIfAbsent(NOVEL, NOVEL_COVER);
        CATEGORY_MAP.putIfAbsent(PHYSICS, PHYSICS_COVER);
        CATEGORY_MAP.putIfAbsent(CHEMISTRY, CHEMISTRY_COVER);
        CATEGORY_MAP.putIfAbsent(MATHEMATICS, MATHEMATICS_COVER);
        CATEGORY_MAP.putIfAbsent(MODERN_WAR, MODERN_WAR_COVER);
        CATEGORY_MAP.putIfAbsent(ANCIENT_WAR, ANCIENT_WAR_COVER);
        CATEGORY_MAP.putIfAbsent(MODERN_HISTORY, MODERN_HISTORY_COVER);
        CATEGORY_MAP.putIfAbsent(ANCIENT_HISTORY, ANCIENT_HISTORY_COVER);
    }

    /**
     * Returns a corresponding book cover image according to the set of categories of the book and
     * the name of the book.
     *
     * @param bookName The name of the book.
     * @param categorySet The set of categories of the book.
     * @return The corresponding book cover of the book.
     */
    public Image getCategoryBookCover(String bookName, Set<Category> categorySet) {
        assert bookName != null;
        if (categorySet == null || categorySet.size() == 0) {
            int number = (bookName.hashCode() % GENERAL_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(GENERAL))[number]));
        }

        Set<String> categoryNames = categorySet.stream()
                .map(category -> category.categoryName.toUpperCase())
                .collect(Collectors.toSet());
        if (categoryNames.contains(PHYSICS.toUpperCase())) {
            int number = Math.abs(bookName.hashCode() % PHYSICS_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(PHYSICS))[number]));
        } else if (categoryNames.contains(CHEMISTRY.toUpperCase())) {
            int number = Math.abs(bookName.hashCode() % CHEMISTRY_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(CHEMISTRY))[number]));
        } else if (categoryNames.contains(MATHEMATICS.toUpperCase())) {
            int number = Math.abs(bookName.hashCode() % MATHEMATICS_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(MATHEMATICS))[number]));
        } else if (categoryNames.contains(MODERN_WAR.toUpperCase())) {
            int number = Math.abs(bookName.hashCode() % MODERN_WAR_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(MODERN_WAR))[number]));
        } else if (categoryNames.contains(ANCIENT_WAR.toUpperCase())) {
            int number = Math.abs(bookName.hashCode() % ANCIENT_WAR_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(ANCIENT_WAR))[number]));
        } else if (categoryNames.contains(MODERN_HISTORY.toUpperCase())) {
            int number = Math.abs(bookName.hashCode() % MODERN_HISTORY_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(MODERN_HISTORY))[number]));
        } else if (categoryNames.contains(ANCIENT_HISTORY.toUpperCase())) {
            int number = Math.abs(bookName.hashCode() % ANCIENT_HISTORY_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(ANCIENT_HISTORY))[number]));
        } else if (categoryNames.contains(SCIENCE.toUpperCase())) {
            int number = Math.abs(bookName.hashCode() % SCIENCE_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(SCIENCE))[number]));
        } else if (categoryNames.contains(HISTORY.toUpperCase())) {
            int number = Math.abs(bookName.hashCode() % HISTORY_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(HISTORY))[number]));
        } else if (categoryNames.contains(NOVEL.toUpperCase())) {
            int number = Math.abs(bookName.hashCode() % NOVEL_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(NOVEL))[number]));
        } else {
            int number = Math.abs(bookName.hashCode() % GENERAL_COVER.length);
            return new Image(this.getClass().getResourceAsStream((CATEGORY_MAP.get(GENERAL))[number]));
        }
    }
}
