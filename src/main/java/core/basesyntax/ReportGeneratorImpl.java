package core.basesyntax;

public class ReportGeneratorImpl implements ReportGenerator {
    private final Storage storage;

    public ReportGeneratorImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public String getReport() {
        StringBuilder report = new StringBuilder();
        report.append("fruit,quantity\n");
        storage.getInventory().forEach((fruit, quantity) -> {
            report.append(fruit).append(",").append(quantity).append("\n");
        });
        return report.toString();
    }
}

interface ReportGenerator {
    String getReport();
}
