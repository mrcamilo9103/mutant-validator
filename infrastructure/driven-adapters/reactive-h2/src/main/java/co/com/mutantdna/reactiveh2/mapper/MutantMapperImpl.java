package co.com.mutantdna.reactiveh2.mapper;

import co.com.mutantdna.reactiveh2.model.MutantData;
import co.com.mutantdna.model.Mutant;
import org.springframework.stereotype.Component;

@Component
public class MutantMapperImpl implements MutantMapper{
    @Override
    public MutantData entityToData(Mutant mutant) {
        return MutantData.builder()
                .dna(mutant.getDna())
                .isMutant(mutant.isMutant())
                .build();
    }

    @Override
    public Mutant dataToEntity(MutantData mutant) {
        return Mutant.builder()
                .dna(mutant.getDna())
                .isMutant(mutant.isMutant())
                .build();
    }
}
