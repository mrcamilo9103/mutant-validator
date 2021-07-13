package co.com.mutantdna.config;

import co.com.mutantdna.model.gateway.MutantGateway;
import co.com.mutantdna.usecase.mutantvalidator.MutantValidatorUseCase;
import co.com.mutantdna.usecase.mutantvalidator.ValidMutant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

        @Bean
        public ValidMutant validMutant(){ return new ValidMutant(); }

        @Bean
        public MutantValidatorUseCase mutantValidatorUseCase(ValidMutant validMutant, MutantGateway mutantRepository){
                return new MutantValidatorUseCase(validMutant, mutantRepository);
        }

}
