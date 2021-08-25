package co.com.mutantdna.usecase.mutantvalidator;

import lombok.Builder;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Getter
@Builder(toBuilder = true)
public class AsyncMutantTask {

    private CompletableFuture<Integer> searchColumns;
    private CompletableFuture<Integer> searchRows;
    private CompletableFuture<Integer> searchLeftDiagonal;
    private CompletableFuture<Integer> searchRightDiagonal;
    private static final Integer MIN_COUNT_OCCURRENCE = 2;

    public boolean completeExecutions() throws ExecutionException, InterruptedException {

        this.searchColumns.whenComplete((countOccurrences, exception) -> {
            if(MIN_COUNT_OCCURRENCE.equals(countOccurrences)){
                searchRows.complete(0);
                searchLeftDiagonal.complete(0);
                searchRightDiagonal.complete(0);
            }
        });

        this.searchRows.whenComplete((countOccurrences, exception) -> {
            if(MIN_COUNT_OCCURRENCE.equals(countOccurrences)){
                searchColumns.complete(0);
                searchLeftDiagonal.complete(0);
                searchRightDiagonal.complete(0);
            }
        });

        this.searchLeftDiagonal.whenComplete((countOccurrences, exception) -> {
            if(MIN_COUNT_OCCURRENCE.equals(countOccurrences)){
                searchRows.complete(0);
                searchColumns.complete(0);
                searchRightDiagonal.complete(0);
            }
        });

        this.searchRightDiagonal.whenComplete((countOccurrences, exception) -> {
            if(MIN_COUNT_OCCURRENCE.equals(countOccurrences)){
                searchRows.complete(0);
                searchColumns.complete(0);
                searchLeftDiagonal.complete(0);
            }
        });

        return validateIsMutant();
    }


    private boolean validateIsMutant() throws ExecutionException, InterruptedException {
        CompletableFuture.allOf(
                searchColumns,
                searchRows,
                searchLeftDiagonal,
                searchRightDiagonal
        ).join();
        return 	(searchColumns.get() +
                searchRows.get() +
                searchLeftDiagonal.get() +
                searchRightDiagonal.get()) > 1;
    }
}
