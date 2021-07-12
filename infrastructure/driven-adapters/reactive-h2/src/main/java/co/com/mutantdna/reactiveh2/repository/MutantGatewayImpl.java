package co.com.mutantdna.reactiveh2.repository;

import co.com.mutantdna.reactiveh2.mapper.MutantMapper;
import co.com.mutantdna.model.Mutant;
import co.com.mutantdna.model.Stats;
import co.com.mutantdna.model.exceptions.TechnicalException;
import co.com.mutantdna.model.gateway.MutantGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MutantGatewayImpl implements MutantGateway {

    private final MutantMapper mutantMapper;
    private final MutantRepository mutantRepository;

    @Override
    public Mono<Mutant> save(Mutant mutant) {

        return mutantRepository.save(mutantMapper.entityToData(mutant))
                .map(mutantMapper::dataToEntity);
    }

    @Override
    public Mono<Stats> getStats() {
        return Mono.just(Stats.builder().build())
                .flatMap( stats -> mutantRepository.countByIsMutant(true).map(stats::countMutantDna))
                .flatMap( stats -> mutantRepository.countByIsMutant(false).map(stats::countHumanDna))
                .map(stats -> stats.ratio())
                .onErrorMap(Exception.class, TechnicalException::new);
    }
}
