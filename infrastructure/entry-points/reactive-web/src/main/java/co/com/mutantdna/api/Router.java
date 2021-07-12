package co.com.mutantdna.api;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
@RequiredArgsConstructor
public class Router {

    private final ApiProperties apiProperties;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler){
        return nest(path(apiProperties.getPathBase()),
                route(POST(apiProperties.getMutant()), handler::findMutant)
                .andRoute(GET(apiProperties.getStats()), handler::getStats));
    }


}

