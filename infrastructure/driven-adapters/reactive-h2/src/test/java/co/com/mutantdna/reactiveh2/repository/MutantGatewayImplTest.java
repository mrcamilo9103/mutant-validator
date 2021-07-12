package co.com.mutantdna.reactiveh2.repository;

import co.com.mutantdna.model.Mutant;
import co.com.mutantdna.model.Stats;
import co.com.mutantdna.reactiveh2.mapper.MutantMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MutantGatewayImplTest {

    @InjectMocks
    private MutantGatewayImpl mutantGatewayImpl;
    @Mock
    private MutantMapper mutantMapper;
    @Mock
    private MutantRepository mutantRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveOK(){
       when(mutantRepository.save(any())).thenReturn(Mono.empty());
        mutantGatewayImpl.save(buildMutantRequest())
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
    public void getStatsOK(){
        when(mutantRepository.countByIsMutant(any())).thenReturn(Mono.just(20));
        mutantGatewayImpl.getStats()
                .as(StepVerifier::create)
                .assertNext(stats -> assertThat(stats).isExactlyInstanceOf(Stats.class))
                .verifyComplete();
    }

    private Mutant buildMutantRequest(){
        return Mutant.builder()
                .dna(new String[]{"ABC","CFT"})
                .build();
    }
}
