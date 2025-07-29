package core.basesyntax;

import java.util.ArrayList;
import java.util.List;

public class DataConverterImpl implements DataConverter {
    @Override
    public List<FruitTransaction> convertToTransaction(List<String> inputReport) {
        List<FruitTransaction> transactions = new ArrayList<>();
        for (String line : inputReport) {
            String[] parts = line.split(",");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid line format: " + line);
            }
            String operationCode = parts[0].trim();
            String fruit = parts[1].trim();
            int quantity = Integer.parseInt(parts[2].trim());

            Operation operation = Operation.fromCode(operationCode);
            transactions.add(new FruitTransaction(operation, fruit, quantity));
        }
        return transactions;
    }
}

interface DataConverter {
    List<FruitTransaction> convertToTransaction(List<String> inputReport);
}
