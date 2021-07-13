package co.com.mutantdna.dynamodb.repository;

import co.com.mutantdna.dynamodb.model.MutantData;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface DynamoRepository extends CrudRepository<MutantData, String> {

    @EnableScanCount
    Integer countByIsMutant(Boolean value);
}
