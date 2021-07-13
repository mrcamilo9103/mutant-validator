package co.com.mutantdna.dynamodb.mapper;

import co.com.mutantdna.dynamodb.model.MutantData;
import co.com.mutantdna.model.Mutant;

public interface MutantMapper {


    MutantData toData(Mutant mutant);

    Mutant toEntity(MutantData mutantEntity);
}
