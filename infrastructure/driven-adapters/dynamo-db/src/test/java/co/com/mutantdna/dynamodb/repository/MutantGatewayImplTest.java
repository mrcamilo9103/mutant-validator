package co.com.mutantdna.dynamodb.repository;

import co.com.mutantdna.dynamodb.mapper.MutantMapper;
import co.com.mutantdna.dynamodb.model.MutantData;
import co.com.mutantdna.model.Mutant;
import co.com.mutantdna.model.Stats;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MutantGatewayImplTest {

    @InjectMocks
    private MutantRepositoryImpl mutantrepositoryImpl;
    @Mock
    private MutantMapper mutantMapper;
    @Mock
    private DynamoRepository dynamoRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveOK(){
        when(mutantMapper.toData(any())).thenReturn(buildMutantData());
        when(dynamoRepository.save(any())).thenReturn(buildMutantData());
        when(mutantMapper.toEntity(any())).thenReturn(buildMutantRequest());
        mutantrepositoryImpl.save(buildMutantRequest())
                .as(StepVerifier::create)
                .assertNext(mutant -> assertThat(mutant.isMutant()))
                .verifyComplete();
    }

    @Test
    public void getStatsOK(){
        when(dynamoRepository.countByIsMutant(any())).thenReturn(20);
        mutantrepositoryImpl.getStats()
                .as(StepVerifier::create)
                .assertNext(stats -> assertThat(stats).isExactlyInstanceOf(Stats.class))
                .verifyComplete();
    }

    private Mutant buildMutantRequest(){
        return Mutant.builder()
                .id("[ABC, CFT]")
                .dna(new String[]{"ABC","CFT"})
                .isMutant(true)
                .build();
    }

    private MutantData buildMutantData(){
        return MutantData.builder()
                .id("[ATGCGA, CAGTGC, TTATGT, AGAAGG, CCCCTA, TCACTG]")
                .isMutant(true)
                .build();
    }
}
