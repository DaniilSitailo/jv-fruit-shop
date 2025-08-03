package core.basesyntax;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import core.basesyntax.service.FileReaderService;
import core.basesyntax.service.FileWriterService;
import core.basesyntax.service.OperationStrategy;
import core.basesyntax.service.ReportGenerator;
import core.basesyntax.service.ShopService;
import core.basesyntax.service.impl.DataConverterImpl;
import core.basesyntax.service.impl.FileReaderServiceImpl;
import core.basesyntax.service.impl.FileWriterServiceImpl;
import core.basesyntax.service.impl.ReportGeneratorImpl;
import core.basesyntax.service.impl.ShopServiceImpl;
import core.basesyntax.strategy.BalanceOperation;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.OperationStrategyImpl;
import core.basesyntax.strategy.PurchaseOperation;
import core.basesyntax.strategy.ReturnOperation;
import core.basesyntax.strategy.SupplyOperation;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Read the data from the input CSV file
            FileReaderService fileReader = new FileReaderServiceImpl();
            List<String> inputReport = fileReader.read("reportToRead.csv");

            // 2. Convert the incoming data into FruitTransactions list
            DataConverter dataConverter = new DataConverterImpl();
            final List<FruitTransaction> transactions =
                    dataConverter.convertToTransaction(inputReport);

            // 3. Create and populate the map with all OperationHandler implementations
            Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
            operationHandlers.put(FruitTransaction.Operation.BALANCE, new BalanceOperation());
            operationHandlers.put(FruitTransaction.Operation.SUPPLY, new SupplyOperation());
            operationHandlers.put(FruitTransaction.Operation.PURCHASE, new PurchaseOperation());
            operationHandlers.put(FruitTransaction.Operation.RETURN, new ReturnOperation());

            OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);

            // 4. Process the incoming transactions with applicable OperationHandler implementations
            Storage storage = new Storage();
            ShopService shopService = new ShopServiceImpl(operationStrategy);
            shopService.process(transactions, storage);

            // 5. Generate report based on the current Storage state
            ReportGenerator reportGenerator = new ReportGeneratorImpl();
            String resultingReport = reportGenerator.getReport(storage);

            // 6. Write the received report into the destination file
            FileWriterService fileWriter = new FileWriterServiceImpl();
            fileWriter.write(resultingReport, "finalReport.csv");

            System.out.println("Processing completed successfully. "
                    + "Report saved to finalReport.csv");
        } catch (IOException e) {
            System.err.println("Error processing file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid data: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
