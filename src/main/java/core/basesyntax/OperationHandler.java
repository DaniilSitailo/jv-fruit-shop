package core.basesyntax;

public interface OperationHandler {
    void handle(FruitTransaction transaction, Storage storage);
}

class BalanceOperation implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction, Storage storage) {
        storage.setBalance(transaction.getFruit(), transaction.getQuantity());
    }
}

class SupplyOperation implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction, Storage storage) {
        storage.supply(transaction.getFruit(), transaction.getQuantity());
    }
}

class PurchaseOperation implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction, Storage storage) {
        storage.purchase(transaction.getFruit(), transaction.getQuantity());
    }
}

class ReturnOperation implements OperationHandler {
    @Override
    public void handle(FruitTransaction transaction, Storage storage) {
        storage.returnFruits(transaction.getFruit(), transaction.getQuantity());
    }
}
