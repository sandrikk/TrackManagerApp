package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
    private final String filePath;

    public CsvReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> readCsv() {
        List<String[]> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            //Skip the first line (headings)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                data.add(fields);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }

}


