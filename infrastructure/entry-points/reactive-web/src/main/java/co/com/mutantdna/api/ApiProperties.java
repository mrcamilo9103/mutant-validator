package co.com.mutantdna.api;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="reactive-endpoints")
public class ApiProperties {
    private String pathBase;
    private String mutant;
    private String stats;

}
