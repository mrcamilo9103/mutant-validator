package co.com.mutantdna.dynamodb.repository;

import co.com.mutantdna.dynamodb.mapper.MutantMapper;
import co.com.mutantdna.dynamodb.model.MutantData;
import co.com.mutantdna.model.Mutant;
import co.com.mutantdna.model.Stats;
import co.com.mutantdna.model.gateway.MutantGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MutantRepositoryImpl implements MutantGateway {

    private final DynamoRepository dynamoRepository;
    private final MutantMapper mutantMapper;

    @Override
    public Mono<Mutant> save(Mutant mutant) {
        MutantData mutantSaved =  dynamoRepository.save(mutantMapper.toData(mutant));
        return Mono.just(mutantSaved)
                .map(mutantMapper::toEntity);
    }

    @Override
    public Mono<Stats> getStats() {

        return Mono.just(
                        Stats.builder()
                        .countHumanDna(dynamoRepository.countByIsMutant(false))
                        .countMutantDna(dynamoRepository.countByIsMutant(true))
                        .build())
                .map(stats -> stats.ratio());

    }
}
