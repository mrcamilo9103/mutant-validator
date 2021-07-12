package co.com.mutantdna.model.gateway;

import co.com.mutantdna.model.Mutant;
import co.com.mutantdna.model.Stats;
import reactor.core.publisher.Mono;

public interface MutantGateway {
    Mono<Mutant> save(Mutant mutant);
    Mono<Stats> getStats();
}
