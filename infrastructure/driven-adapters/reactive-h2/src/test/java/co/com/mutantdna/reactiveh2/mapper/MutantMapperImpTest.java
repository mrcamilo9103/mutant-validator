package co.com.mutantdna.reactiveh2.mapper;

import co.com.mutantdna.model.Mutant;
import co.com.mutantdna.reactiveh2.model.MutantData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class MutantMapperImpTest {

    @InjectMocks
    private MutantMapperImpl mutantMapperImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void entityToData() {
        assertThat(mutantMapperImpl.entityToData(buildMutant())).isExactlyInstanceOf(MutantData.class);
    }

    @Test
    public void DataToEntity(){
        assertThat(mutantMapperImpl.dataToEntity(buildMutantData())).isExactlyInstanceOf(Mutant.class);
    }

    private MutantData buildMutantData(){
        return MutantData.builder()
                .dna(new String[]{"ABC","CFT"})
                .build();
    }

    private Mutant buildMutant(){
        return Mutant.builder()
                .dna(new String[]{"ABC","CFT"})
                .build();
    }
}
