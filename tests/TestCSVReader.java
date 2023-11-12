

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import utils.CsvReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestCSVReader {

    @TempDir
    Path tempDir;

    @Test
    void readCsv_ValidPattern_Success() throws IOException {
        // Create a temporary CSV file
        File tempFile = createTempCsvFile("A,B,C\n1,2,3\n4,5,6");

        // Instantiate CsvReader with regex that matches the data
        CsvReader reader = new CsvReader(tempFile.getAbsolutePath(), "^(\\d+),(\\d+),(\\d+)$");

        // Read CSV and verify content
        List<String[]> data = reader.readCsv();
        assertAll("Valid CSV content",
                () -> assertEquals(2, data.size()),
                () -> assertArrayEquals(new String[]{"1", "2", "3"}, data.get(0)),
                () -> assertArrayEquals(new String[]{"4", "5", "6"}, data.get(1))
        );
    }

    @Test
    void readCsv_InvalidPattern_ThrowsException() throws IOException {
        // Create a temporary CSV file
        File tempFile = createTempCsvFile("A,B,C\n1,2,3\ninvalid,line");

        // Instantiate CsvReader with regex that matches numbers only
        CsvReader reader = new CsvReader(tempFile.getAbsolutePath(), "^(\\d+),(\\d+),(\\d+)$");

        // Attempt to read CSV and expect an exception
        Exception exception = assertThrows(IllegalArgumentException.class, reader::readCsv);
        assertTrue(exception.getMessage().contains("Invalid data on line"));
    }

    private File createTempCsvFile(String content) throws IOException {
        File tempFile = tempDir.resolve("test.csv").toFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(content);
        }
        return tempFile;
    }
}

