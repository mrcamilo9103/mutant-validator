package co.com.mutantdna.config;

import co.com.mutantdna.model.gateway.MutantGateway;
import co.com.mutantdna.usecase.mutantvalidator.ValidMutant;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UseCasesConfigTest {

    @InjectMocks
    private UseCasesConfig useCasesConfig;

    @Mock
    private ValidMutant validMutant;

    @Mock
    private MutantGateway mutantRepository;

//    @Mock
//    private ConnectionFactory connectionFactory;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mutantValidatorUseCase(){
        assertNotNull(useCasesConfig.mutantValidatorUseCase(validMutant, mutantRepository));
    }

    @Test
    public void validMutantTest(){
        assertNotNull(useCasesConfig.validMutant());
    }

//    @Test
//    public void connectionFactoryTest(){
//        assertNotNull(useCasesConfig.initializer(connectionFactory));
//    }

}
