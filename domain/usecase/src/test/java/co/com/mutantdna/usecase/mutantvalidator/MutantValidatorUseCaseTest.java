package co.com.mutantdna.usecase.mutantvalidator;

import co.com.mutantdna.model.MutantRequest;
import co.com.mutantdna.model.Stats;
import co.com.mutantdna.model.gateway.MutantGateway;
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

public class MutantValidatorUseCaseTest {

    @InjectMocks
    private MutantValidatorUseCase mutantValidatorUseCase;
    @Mock
    private ValidMutant validMutant;
    @Mock
    private MutantGateway mutantRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findMutantOK(){
        when(validMutant.isMutant(any())).thenReturn(true);
        when(mutantRepository.save(any())).thenReturn(Mono.empty());
        mutantValidatorUseCase.findMutant(buildMutantRequest())
                .as(StepVerifier::create)
                .verifyComplete();
    }

    @Test
    public void statsOK(){
        when(mutantRepository.getStats()).thenReturn(Mono.just(buildStats()));
        mutantValidatorUseCase.stats()
                .as(StepVerifier::create)
                .assertNext(test -> assertThat(test).isExactlyInstanceOf(Stats.class))
                .verifyComplete();
    }

    private MutantRequest buildMutantRequest(){
        return MutantRequest.builder()
                .dna(new String[]{"ABC","CFT"})
                .build();
    }

    private Stats buildStats(){
        return Stats.builder()
                .countHumanDna(10)
                .countMutantDna(5)
                .build();
    }

}
