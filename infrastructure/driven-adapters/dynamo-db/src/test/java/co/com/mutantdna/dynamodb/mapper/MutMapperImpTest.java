package co.com.mutantdna.dynamodb.mapper;

import co.com.mutantdna.dynamodb.model.MutantData;
import co.com.mutantdna.model.Mutant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class MutMapperImpTest {

    @InjectMocks
    private MutMapperImpl mutantMapperImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void entityToData() {
        assertThat(mutantMapperImpl.toData(buildMutant())).isExactlyInstanceOf(MutantData.class);
    }

    @Test
    public void DataToEntity(){
        assertThat(mutantMapperImpl.toEntity(buildMutantData())).isExactlyInstanceOf(Mutant.class);
    }

    private MutantData buildMutantData(){
        return MutantData.builder()
                .id("[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]")
                .build();
    }

    private Mutant buildMutant(){
        return Mutant.builder()
                .dna(new String[]{"ABC","CFT"})
                .build();
    }
}
