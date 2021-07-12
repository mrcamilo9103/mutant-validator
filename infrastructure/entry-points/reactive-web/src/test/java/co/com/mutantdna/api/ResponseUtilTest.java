package co.com.mutantdna.api;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class ResponseUtilTest {

    @Test
    public void responseOk() {
        ResponseUtil.buildResponse(HttpStatus.OK,"OK")
                .as(StepVerifier::create)
                .assertNext(test -> assertThat(test).isInstanceOf(ServerResponse.class))
                .verifyComplete();
    }

    @Test
    public void responseFail() {
        ResponseUtil.responseFail("error")
                .as(StepVerifier::create)
                .assertNext(test -> assertThat(test.statusCode()).isEqualTo(INTERNAL_SERVER_ERROR))
                .verifyComplete();
    }

    @Test
    public void buildResponse() {
        ResponseUtil.buildResponse(BAD_REQUEST,"error")
                .as(StepVerifier::create)
                .assertNext(test -> assertThat(test.statusCode()).isEqualTo(BAD_REQUEST))
                .verifyComplete();
    }
}
