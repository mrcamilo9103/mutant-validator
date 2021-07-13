package co.com.mutantdna.config;

import co.com.mutantdna.dynamodb.repository.DynamoRepository;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = DynamoRepository.class)
public class DynamoDBConfig {

//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//        return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(
//            new AwsClientBuilder.
//                EndpointConfiguration("http://localhost:8000", Regions.US_EAST_1.getName()))
//               .build();
//
//    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
    }
}
