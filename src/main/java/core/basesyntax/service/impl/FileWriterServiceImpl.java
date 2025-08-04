package core.basesyntax.service.impl;

import core.basesyntax.service.FileWriterService;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileWriterServiceImpl implements FileWriterService {
    @Override
    public void write(String report, String filePath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Error writing report to file: " + filePath, e);
        }
    }
}
