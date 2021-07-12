package co.com.mutantdna.model.exceptions;

import co.com.mutantdna.model.enums.TechnicalExceptionEnum;
import lombok.Getter;

@Getter
public class TechnicalException extends RuntimeException {

    private final TechnicalExceptionEnum exception;

    public TechnicalException(Throwable error) {
        super(error);
        this.exception = TechnicalExceptionEnum.TECHNICAL_REQUEST_ERROR;
    }

    public TechnicalException(TechnicalExceptionEnum technicalExceptionEnum){
        super(technicalExceptionEnum.getTitle());
        this.exception = technicalExceptionEnum;
    }

    public TechnicalException(Throwable throwable, TechnicalExceptionEnum technicalExceptionEnum){
        super(throwable);
        this.exception = technicalExceptionEnum;
    }
}
