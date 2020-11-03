package seedu.address.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.address.model.problem.Description;
import seedu.address.model.problem.Problem;
import seedu.address.model.problem.Severity;

public class StorageForProblem {
    private static String filePath;
    private static File f;

    /**
     * Constructs a storage for problems.
     * @param filePath
     * @throws IOException
     */
    public StorageForProblem(String filePath) throws IOException {
        this.filePath = filePath;
        this.f = new File(filePath);
        this.f.createNewFile();
    }

    /**
     * Loads the information written on File.txt.
     *
     * @return A list of problems loaded from the file.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Problem> load() throws FileNotFoundException {
        ArrayList<Problem> temp = new ArrayList<>();
        Scanner scFile = new Scanner(f);
        while (scFile.hasNextLine()) {
            String data = scFile.nextLine();
            Problem problem = parse(data);
            temp.add(problem);
        }
        return temp;
    }

    private static Problem parse(String data) {
        char severity = data.charAt(0);
        if (severity == 'H') {
            return parseHigh(data);
        } else if (severity == 'M') {
            return parseMedium(data);
        } else {
            return parseLow(data);
        }
    }

    private static Problem parseHigh(String data) {
        Description description = new Description(data.substring(1));
        Severity severity = new Severity("HIGH");
        return new Problem(severity, description);
    }

    private static Problem parseMedium(String data) {
        Description description = new Description(data.substring(1));
        Severity severity = new Severity("MEDIUM");
        return new Problem(severity, description);
    }

    private static Problem parseLow(String data) {
        Description description = new Description(data.substring(1));
        Severity severity = new Severity("LOW");
        return new Problem(severity, description);
    }

    /**
     * Writes problems in the ProblemList onto the file.
     *
     * @param list The ProblemList temporarily storing all problems.
     * @throws IOException
     */
    public static void writeData(List<Problem> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Problem problem: list) {
            String temp = unparse(problem); // convert Task into String
            fw.write(temp + System.lineSeparator());
        }
        fw.close();
    }

    private static String unparse(Problem problem) {
        String severity = problem.getSeverity().toString();
        if (severity.equals("HIGH")) {
            return "H" + problem.getDescription().toString();
        } else if (severity.equals("MEDIUM")) {
            return "M" + problem.getDescription().toString();
        } else {
            return "L" + problem.getDescription().toString();
        }
    }
}
