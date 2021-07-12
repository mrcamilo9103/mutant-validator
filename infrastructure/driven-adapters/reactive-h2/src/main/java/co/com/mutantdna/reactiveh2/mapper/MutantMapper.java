package co.com.mutantdna.reactiveh2.mapper;

import co.com.mutantdna.reactiveh2.model.MutantData;
import co.com.mutantdna.model.Mutant;

public interface MutantMapper {

    MutantData entityToData(Mutant mutant);

    Mutant dataToEntity(MutantData mutant);
}
