package co.com.mutantdna.usecase.mutantvalidator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class    ValidMutantTest {

    @InjectMocks
    private ValidMutant validMutant;

    private String[][] dnaMutant;
    private String[][] dnaHuman;
    private String[][] dnaMutantInDiagonal;
    private String[][] dnaMutantInOtherDiagonal;
    private String[][] dnaMutantInColumn;

    @Before
    public void init(){
         dnaMutant = Arrays.stream(Data.REQUEST_MUTANT_ARRAY).map(value -> value.split(""))
                .toArray(String[][]::new);

         dnaHuman = Arrays.stream(Data.REQUEST_HUMAN_ARRAY).map(value -> value.split(""))
                 .toArray(String[][]::new);

        dnaMutantInDiagonal = Arrays.stream(Data.DNA_MUTANT_IN_DIAGONAL).map(value -> value.split(""))
                .toArray(String[][]::new);

        dnaMutantInOtherDiagonal = Arrays.stream(Data.DNA_MUTANT_IN_DIAGONAL).map(value -> value.split(""))
                .toArray(String[][]::new);

        dnaMutantInColumn = Arrays.stream(Data.DNA_MUTANT_IN_COLUMN).map(value -> value.split(""))
                .toArray(String[][]::new);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenIsMutantThenReturnTrue(){
        assertTrue(validMutant.isMutant(dnaMutant));
    }

    @Test
    public void whenIsNotMutantThenReturnFalse(){
        assertFalse(validMutant.isMutant(dnaHuman));
    }

    @Test
    public void whenIsMutantInDiagonal(){
        assertTrue(validMutant.isMutant(dnaMutantInDiagonal));
    }

    @Test
    public void whenIsMutantInOtherDiagonal(){
        assertTrue(validMutant.isMutant(ValidMutant.reverseRow(dnaMutantInOtherDiagonal)));
    }

    @Test
    public void whenIsMutantInColumn(){
        assertTrue(validMutant.isMutant((dnaMutantInColumn)));
    }

}
