package co.com.mutantdna.usecase.mutantvalidator;

import lombok.Builder;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Getter
@Builder(toBuilder = true)
public class AsyncMutantTask {

    private CompletableFuture<Boolean> searchColumns;
    private CompletableFuture<Boolean> searchRows;
    private CompletableFuture<Boolean> searchLeftDiagonal;
    private CompletableFuture<Boolean> searchRightDiagonal;

    public boolean completeExecutions() throws ExecutionException, InterruptedException {
        this.searchColumns.whenComplete((isMutant, exception) -> {
            if(Boolean.TRUE.equals(isMutant)){
                searchRows.complete(false);
                searchLeftDiagonal.complete(false);
                searchRightDiagonal.complete(false);
            }
        });

        this.searchRows.whenComplete((isMutant, exception) -> {
            if(Boolean.TRUE.equals(isMutant)){
                searchColumns.complete(false);
                searchLeftDiagonal.complete(false);
                searchRightDiagonal.complete(false);
            }
        });

        this.searchLeftDiagonal.whenComplete((isMutant, exception) -> {
            if(Boolean.TRUE.equals(isMutant)){
                searchRows.complete(false);
                searchColumns.complete(false);
                searchRightDiagonal.complete(false);
            }
        });

        this.searchRightDiagonal.whenComplete((isMutant, exception) -> {
            if(Boolean.TRUE.equals(isMutant)){
                searchRows.complete(false);
                searchColumns.complete(false);
                searchLeftDiagonal.complete(false);
            }
        });


        return validateIsMutant();
    }



    /**
     * Valida si almenos uno de los 4 procesos async tuvo como respuesta (true)
     */
    private boolean validateIsMutant() throws ExecutionException, InterruptedException {
        CompletableFuture.allOf(
                searchColumns,
                searchRows,
                searchLeftDiagonal,
                searchRightDiagonal
        ).join();
        return 	searchColumns.get() ||
                searchRows.get() ||
                searchLeftDiagonal.get() ||
                searchRightDiagonal.get();
    }
}
