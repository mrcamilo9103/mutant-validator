package co.com.mutantdna.usecase.mutantvalidator;

import co.com.mutantdna.model.Mutant;
import co.com.mutantdna.model.MutantRequest;
import co.com.mutantdna.model.Stats;
import co.com.mutantdna.model.constants.Constants;
import co.com.mutantdna.model.enums.TechnicalExceptionEnum;
import co.com.mutantdna.model.exceptions.TechnicalException;
import co.com.mutantdna.model.gateway.MutantGateway;
import co.com.mutantdna.model.utils.Utils;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static co.com.mutantdna.model.enums.TechnicalExceptionEnum.NOT_ALLOWED_DNA_CHARS;

@RequiredArgsConstructor
public class MutantValidatorUseCase {

    private final ValidMutant validMutant;
    private final MutantGateway mutantRepository;

    public Mono<Void> findMutant(MutantRequest mutantRequest){
        String[][] dna = Arrays.stream(mutantRequest.getDna()).map(value -> value.split(""))
                .toArray(String[][]::new);
    return Mono.fromCallable(() -> {
                    if(!Utils.convertArrayToSimpleText(mutantRequest.getDna())
                            .matches(Constants.RGX_ALLOWED_DNA_CHARS)){
                      throw  new TechnicalException(NOT_ALLOWED_DNA_CHARS);
                    }
                    return validMutant.isMutant(dna);
                })
            .map(valid -> Mutant.builder().isMutant(valid)
                    .dna(mutantRequest.getDna())
                    .id(Arrays.toString(mutantRequest.getDna()))
                    .build())
            .flatMap(mutantRepository::save)
            .filter(dnaTest -> !dnaTest.isMutant())
            .flatMap(m-> Mono.error(new TechnicalException(TechnicalExceptionEnum.BUSINESS_NOT_IS_A_MUTANT)))
            .then();
    }

    public Mono<Stats> stats(){
        return mutantRepository.getStats();
    }

}
