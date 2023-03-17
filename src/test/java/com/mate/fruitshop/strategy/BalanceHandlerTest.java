package com.mate.fruitshop.strategy;

import static org.junit.Assert.assertEquals;

import com.mate.fruitshop.db.Storage;
import com.mate.fruitshop.model.FruitEntry;
import com.mate.fruitshop.model.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BalanceHandlerTest {
    private static final FruitEntry STORAGE_ENTRY =
            new FruitEntry("banana", 5);
    private static final int FIRST_INDEX = 0;
    private static OperationHandler balanceHandler;
    private static Transaction balanceTransaction;

    @Before
    public void setUp() {
        balanceHandler = new BalanceHandler();
        balanceTransaction = new Transaction(Transaction.Operation.BALANCE,
                "banana", 10);
    }

    @After
    public void tearDown() {
        Storage.fruits.clear();
    }

    @Test
    public void process_NewFruit_Ok() {
        balanceHandler.process(balanceTransaction);
        assertEquals(balanceTransaction.getFruitName(),
                Storage.fruits.get(FIRST_INDEX).getFruitName());
        assertEquals(balanceTransaction.getQuantity(),
                Storage.fruits.get(FIRST_INDEX).getQuantity());
    }

    @Test
    public void process_ExistingFruit_Ok() {
        Storage.fruits.add(FIRST_INDEX, STORAGE_ENTRY);
        balanceHandler.process(balanceTransaction);
        assertEquals(balanceTransaction.getQuantity(),
                Storage.fruits.get(FIRST_INDEX).getQuantity());
    }
}
