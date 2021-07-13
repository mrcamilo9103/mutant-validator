package co.com.mutantdna.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@DynamoDBTable(tableName = "MutantData")
public class MutantData {

    @DynamoDBHashKey
    private String id;

    @DynamoDBAttribute(attributeName = "mutant")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "mutant-index")
    private boolean isMutant;
}
