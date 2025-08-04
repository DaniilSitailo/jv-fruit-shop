package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportGenerator;

public class ReportGeneratorImpl implements ReportGenerator {
    private static final String HEADER = "fruit,quantity";
    private static final String COMMA = ",";
    private static final String NEW_LINE = System.lineSeparator();

    @Override
    public String getReport(Storage storage) {
        StringBuilder report = new StringBuilder();
        report.append(HEADER).append(NEW_LINE);

        storage.getInventory().forEach((fruit, quantity) ->
                report.append(fruit)
                        .append(COMMA)
                        .append(quantity)
                        .append(NEW_LINE)
        );

        return report.toString();
    }
}
