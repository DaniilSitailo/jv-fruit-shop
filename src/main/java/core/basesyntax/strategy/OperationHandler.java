package core.basesyntax.strategy;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.db.Storage;

public interface OperationHandler {
    void handle(FruitTransaction transaction, Storage storage);
}
