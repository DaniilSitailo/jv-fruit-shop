package core.basesyntax;

import java.io.IOException;

public interface FileWriter {
    void write(String report, String filePath) throws IOException;
}
