package co.com.mutantdna.dynamodb.mapper;

import co.com.mutantdna.dynamodb.model.MutantData;
import co.com.mutantdna.model.Mutant;
import org.springframework.stereotype.Component;

@Component
public class MutMapperImpl implements MutantMapper {

    @Override
    public MutantData toData(Mutant mutant) {
        return MutantData.builder()
                .dna(mutant.getId())
                .isMutant(mutant.isMutant())
                .build();
    }

    @Override
    public Mutant toEntity(MutantData mutantEntity) {
        return Mutant.builder()
                .id(mutantEntity.getDna())
                .isMutant(mutantEntity.isMutant())
                .build();
    }
}
