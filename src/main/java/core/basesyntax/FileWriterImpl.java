package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterImpl implements FileWriter {
    @Override
    public void write(String report, String filePath) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(filePath))) {
            bw.write(report);
        }
    }
}
