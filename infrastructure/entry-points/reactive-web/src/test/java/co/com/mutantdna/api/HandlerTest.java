package co.com.mutantdna.api;

import co.com.mutantdna.model.MutantRequest;
import co.com.mutantdna.model.Stats;
import co.com.mutantdna.usecase.mutantvalidator.MutantValidatorUseCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class HandlerTest {

    @InjectMocks
    private Handler handler;

    @Mock
    private ServerRequest serverRequest;

    @Mock
    private MutantValidatorUseCase mutantValidatorUseCase;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findMutant(){
        when(serverRequest.bodyToMono(eq(MutantRequest.class))).thenReturn(Mono.just(buildMutantRequest()));
        when(mutantValidatorUseCase.findMutant(any())).thenReturn(Mono.empty());
        handler.findMutant(serverRequest)
                .as(StepVerifier::create)
                .verifyComplete();

    }

    @Test
    public void getStatsTest(){
        when(serverRequest.bodyToMono(eq(MutantRequest.class))).thenReturn(Mono.just(buildMutantRequest()));
        when(mutantValidatorUseCase.stats()).thenReturn(Mono.just(buildStats()));
        handler.getStats(serverRequest)
                .as(StepVerifier::create)
                .assertNext(test -> assertThat(test).isInstanceOf(ServerResponse.class))
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
