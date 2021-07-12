package co.com.mutantdna.api;

import co.com.mutantdna.model.MutantRequest;
import co.com.mutantdna.model.exceptions.TechnicalException;
import co.com.mutantdna.usecase.mutantvalidator.MutantValidatorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static co.com.mutantdna.model.enums.TechnicalExceptionEnum.TECHNICAL_REQUEST_ERROR;

@Component
@RequiredArgsConstructor
public class Handler {

    private final MutantValidatorUseCase mutantValidatorUseCase;

    protected Mono<ServerResponse> findMutant(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(MutantRequest.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TECHNICAL_REQUEST_ERROR)))
                .flatMap(mutantValidatorUseCase::findMutant)
                .flatMap(ResponseUtil::responseOk);
    }

    protected Mono<ServerResponse> getStats(ServerRequest serverRequest) {
        return mutantValidatorUseCase.stats()
                .flatMap(ResponseUtil::responseOk);
    }
}
