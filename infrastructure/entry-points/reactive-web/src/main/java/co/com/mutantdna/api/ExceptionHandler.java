package co.com.mutantdna.api;

import co.com.mutantdna.model.Error;
import co.com.mutantdna.model.exceptions.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static co.com.mutantdna.model.enums.TechnicalExceptionEnum.BUSINESS_NOT_IS_A_MUTANT;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@Component
@Order(-2)
public class ExceptionHandler extends AbstractErrorWebExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ExceptionHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
                            ApplicationContext applicationContext,
                            ServerCodecConfigurer configurer) {
        super(errorAttributes, resourceProperties, applicationContext);
        this.setMessageWriters(configurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request){
        Throwable throwable = getError(request);
        if(throwable instanceof TechnicalException){
            TechnicalException exception = (TechnicalException)throwable;
            if(exception.getException().equals(BUSINESS_NOT_IS_A_MUTANT)){
                return ResponseUtil.buildResponse(FORBIDDEN,buildError((TechnicalException)throwable, request));
            }
            log.error(throwable.getMessage());
            return ResponseUtil.responseFail(buildError((TechnicalException)throwable, request));
        }else{
            log.error(throwable.getMessage());
            return ResponseUtil.responseFail(Error.builder().message(throwable.getMessage()).build());
        }
    }

    private Error buildError(TechnicalException exception, ServerRequest request) {
        return Error.builder()
                .reason(exception.getMessage())
                .domain(request.uri().toString())
                .code(exception.getException().getCode())
                .message(exception.getException().getTitle())
                .build();
    }
}
