package co.com.mutantdna.reactiveh2.repository;

import co.com.mutantdna.reactiveh2.model.MutantData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface MutantRepository extends ReactiveCrudRepository<MutantData, Long> {

    Mono<Integer> countByIsMutant(Boolean value);
}
