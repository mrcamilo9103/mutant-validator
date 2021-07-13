package co.com.mutantdna.config;

import co.com.mutantdna.model.gateway.MutantGateway;
import co.com.mutantdna.usecase.mutantvalidator.MutantValidatorUseCase;
import co.com.mutantdna.usecase.mutantvalidator.ValidMutant;
//import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
//import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;

@Configuration
public class UseCasesConfig {

        @Bean
        public ValidMutant validMutant(){ return new ValidMutant(); }

        @Bean
        public MutantValidatorUseCase mutantValidatorUseCase(ValidMutant validMutant, MutantGateway mutantRepository){
                return new MutantValidatorUseCase(validMutant, mutantRepository);
        }

//        @Bean
//        public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
//                ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
//                initializer.setConnectionFactory(connectionFactory);
//                initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
//                return initializer;
//        }


}
