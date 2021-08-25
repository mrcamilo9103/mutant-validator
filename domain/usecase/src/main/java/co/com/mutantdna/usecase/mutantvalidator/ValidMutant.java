package co.com.mutantdna.usecase.mutantvalidator;

import co.com.mutantdna.model.MutantSequence;
import co.com.mutantdna.model.enums.TechnicalExceptionEnum;
import co.com.mutantdna.model.exceptions.TechnicalException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
public class ValidMutant {

    private static final int MIN_MUTANT_DNA_OCCURRENCES = 4;

    public boolean isMutant(String[][] dna) {

        try {
            AsyncMutantTask asyncMutantTask =AsyncMutantTask.builder()
                    .searchRows(CompletableFuture.supplyAsync(()-> searchRows(dna)))
                    .searchColumns(CompletableFuture.supplyAsync(() -> searchColumns(dna)))
                    .searchLeftDiagonal(CompletableFuture.supplyAsync(() -> searchDiagonals(dna)))
                    .searchRightDiagonal(CompletableFuture.supplyAsync(() -> searchDiagonals(reverseRow(dna))))
                    .build();
            return asyncMutantTask.completeExecutions();
        } catch (ExecutionException e) {
            throw  new TechnicalException(TechnicalExceptionEnum.TECHNICAL_SERVER_ERROR);
        } catch (InterruptedException e) {
            throw  new TechnicalException(TechnicalExceptionEnum.TECHNICAL_SERVER_ERROR);
        }
    }

    public static Integer searchRows(String[][] dna){
        int countOccurrences = 0;
        for(int i = 0; i < dna.length; i ++){
           MutantSequence mutantSequence = MutantSequence.builder().before("").build();

            for (int j = 0; j < dna.length; j ++){

                mutantSequence.setCurrent(dna[i][j]);

                if(validateSequence(mutantSequence)) {
                    countOccurrences +=  1;
                }
            }
        }
        return countOccurrences;
    }

    public static Integer searchColumns(String[][] dna){
        int countOccurrences = 0;
        for(int i = 0; i < dna.length; i ++){
            MutantSequence mutantSequence = MutantSequence.builder().before("").build();
            for (int j = 0; j < dna.length; j ++){

                mutantSequence.setCurrent(dna[j][i]);

                if(validateSequence(mutantSequence)){
                    countOccurrences += 1;
                }
            }
        }
        return countOccurrences;
    }

    public static Integer searchDiagonals(String[][] dna) {
        int countOccurrences = 0;
        int n = dna.length;

        for ( int diagonal = 1 - n; diagonal <= n - 1; diagonal += 1 ) {
            MutantSequence mutantSequence = MutantSequence.builder().before("").build();

            for ( int vertical = Math.max(0, diagonal), horizontal = -Math.min(0, diagonal);
                  vertical < n && horizontal < n;
                  vertical += 1, horizontal += 1
            ) {
                mutantSequence.setCurrent(dna[vertical][horizontal]);

                if(validateSequence(mutantSequence)) {
                    countOccurrences += 1;
                }
            }
        }
        return countOccurrences;
    }

    public static String[][] reverseRow(String[][]dna){
         String[][] cloneDna = new String[dna.length][dna.length];

        for(int i = 0; i < dna.length; i ++){

            for (int j = 0; j < dna.length; j ++){
               cloneDna[i][j] = dna[i][j];
            }
        }

        for(int i = 0; i < cloneDna.length; i ++){
            Collections.reverse(Arrays.asList(cloneDna[i]));
        }
        return cloneDna;
    }

    private static boolean validateSequence(MutantSequence mutantSequence){

        if(mutantSequence.getBefore().equals(mutantSequence.getCurrent())){
            mutantSequence.plussCount();

            if(mutantSequence.getCountCoincidences() == MIN_MUTANT_DNA_OCCURRENCES){
                return true;
            }
        }else{
            mutantSequence.resetCount();
        }

        mutantSequence.setBefore(mutantSequence.getCurrent());

        return false;
    }

}
