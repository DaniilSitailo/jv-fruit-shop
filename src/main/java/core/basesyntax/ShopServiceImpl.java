package core.basesyntax;

public class ShopServiceImpl implements ShopService {
    private final OperationStrategy operationStrategy;

    public ShopServiceImpl(OperationStrategy operationStrategy) {
        this.operationStrategy = operationStrategy;
    }

    @Override
    public void process(List<FruitTransaction> transactions, Storage storage) {
        for (FruitTransaction transaction : transactions) {
            operationStrategy.process(transaction, storage);
        }
    }
}

interface ShopService {
    void process(List<FruitTransaction> transactions, Storage storage);
}
