package core.basesyntax.service;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.db.Storage;

public interface OperationStrategy {
    void process(FruitTransaction transaction, Storage storage);
}
