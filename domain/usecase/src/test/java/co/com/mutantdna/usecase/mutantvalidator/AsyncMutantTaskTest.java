package co.com.mutantdna.usecase.mutantvalidator;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AsyncMutantTaskTest {

//   @Test
    void whenAtLeastOneMutantSequenceIsFoundThenReturnTrue() throws InterruptedException, ExecutionException {
        AsyncMutantTask asyncMutantTask = AsyncMutantTask.builder()
                .searchColumns(CompletableFuture.supplyAsync(()->true))
                .searchLeftDiagonal(CompletableFuture.supplyAsync(()->true))
                .searchRightDiagonal(CompletableFuture.supplyAsync(()->true))
                .searchRows(CompletableFuture.supplyAsync(()->true))
                .build();

        asyncMutantTask.getSearchColumns().complete(true);
        asyncMutantTask.getSearchLeftDiagonal().complete(false);
        asyncMutantTask.getSearchRightDiagonal().complete(false);
        asyncMutantTask.getSearchRows().complete(false);
        assertTrue(asyncMutantTask.completeExecutions());
    }

//    @Test
    void whenNoSequenceIsFoundThenReturnFalse() throws InterruptedException, ExecutionException {
        AsyncMutantTask asyncMutantTask = AsyncMutantTask.builder()
                .searchColumns(CompletableFuture.supplyAsync(()->false))
                .searchLeftDiagonal(CompletableFuture.supplyAsync(()->false))
                .searchRightDiagonal(CompletableFuture.supplyAsync(()->false))
                .searchRows(CompletableFuture.supplyAsync(()->false))
                .build();

        assertFalse(asyncMutantTask.completeExecutions());
    }
}
