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
                .searchColumns(CompletableFuture.supplyAsync(()->2))
                .searchLeftDiagonal(CompletableFuture.supplyAsync(()->2))
                .searchRightDiagonal(CompletableFuture.supplyAsync(()->2))
                .searchRows(CompletableFuture.supplyAsync(()->2))
                .build();

        asyncMutantTask.getSearchColumns().complete(2);
        asyncMutantTask.getSearchLeftDiagonal().complete(0);
        asyncMutantTask.getSearchRightDiagonal().complete(0);
        asyncMutantTask.getSearchRows().complete(0);
        assertTrue(asyncMutantTask.completeExecutions());
    }

//    @Test
    void whenNoSequenceIsFoundThenReturnFalse() throws InterruptedException, ExecutionException {
        AsyncMutantTask asyncMutantTask = AsyncMutantTask.builder()
                .searchColumns(CompletableFuture.supplyAsync(()->0))
                .searchLeftDiagonal(CompletableFuture.supplyAsync(()->0))
                .searchRightDiagonal(CompletableFuture.supplyAsync(()->0))
                .searchRows(CompletableFuture.supplyAsync(()->0))
                .build();

        assertFalse(asyncMutantTask.completeExecutions());
    }
}
