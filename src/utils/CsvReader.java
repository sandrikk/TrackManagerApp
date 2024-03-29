package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvReader {
    private final String filePath;
    private final Pattern pattern;
    private int counter = 1;

    public CsvReader(String filePath, String regex) {
        this.filePath = filePath;
        this.pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public List<String[]> readCsv() {
        List<String[]> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            String headings = scanner.nextLine();


            while (scanner.hasNextLine()) {
                counter++;
                String line = scanner.nextLine();


                // Remove double quotes from the entire line
                line = line.replace("\"", "");

                // Use the regex pattern to check if the line matches
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) { // If a match is found, add it to the result
                    String[] fields = line.split(",");
                    data.add(fields);
                } else {
                    throw new IllegalArgumentException("Invalid data on line " + counter + ": " + line);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }
}
